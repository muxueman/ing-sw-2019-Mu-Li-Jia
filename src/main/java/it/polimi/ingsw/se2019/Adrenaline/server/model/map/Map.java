package it.polimi.ingsw.se2019.Adrenaline.server.model.map;
import java.util.ArrayList;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Color;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.Status;

/**
 *4 types of maps, a map contains several rooms, a room contains several cells
 *@author Xueman Mu
 */

public abstract class Map implements Status {

    protected ArrayList<Cell> allCells;
    protected String mapInfo;
    protected int totalCellNum;
    protected int[][] notDoor;
    protected ArrayList<Cell> YELLOWCells;
    protected ArrayList<Cell> REDCells;
    protected ArrayList<Cell> GREENCells;
    protected ArrayList<Cell> BLUECells;
    protected ArrayList<Cell> WHITECells;
    protected ArrayList<Cell> PINKCells;
    protected final static Cell cellO = new CommonCell(0);

    //constructor
    public Map(){
        YELLOWCells = new ArrayList<>();
        REDCells = new ArrayList<>();
        GREENCells = new ArrayList<>();
        BLUECells = new ArrayList<>();
        WHITECells = new ArrayList<>();
        PINKCells = new ArrayList<>();
        initialMap();
    }
    //abstract method to initial different kind of map
    public abstract void initialMap();
    public String getMapInfo(){
        return this.mapInfo;
    }
    public ArrayList<Cell> getAllCells(){
        return this.allCells;
    }
    //overload
    //set adjacent cells for a specific cell
    public void setAdjacentCells(int up, int right, int down, int left, Cell cell){
        cell.setAdjacentCells(allCells.get(up), allCells.get(right), allCells.get(down), allCells.get(left));
    }

    //comparison of cells, same room/color
    public Boolean checkWithinRoom(Cell cellA, Cell cellB){
        return (cellA.getCellColor() == cellB.getCellColor());
    }
    //@requires not the same color
    public Boolean checkDoor(Cell cellA, Cell cellB){
        int i =0;
        while( i < 2 ) {
            if(((cellA.getCellID() ==  notDoor[i][0]) && (cellB.getCellID() == notDoor[i][1]))||((cellB.getCellID() ==  notDoor[i][0]) && (cellA.getCellID() == notDoor[i][1])))
                return false;
            i++;
        }
        return true;
    }

    //********* the next part of code is about to search available cells when a card is used********

    //@Overload
    //get cells within rooms, which means all cells from same color
    public ArrayList<Cell> getCellsWithinRoom(Cell cellx){
        switch(cellx.getCellColor()){
            case YELLOW:
                return YELLOWCells;
            case GREEN:
                return GREENCells;
            case BLUE:
                return BLUECells;
            case PINK: 
                return PINKCells;
            case RED:
                return REDCells;
            case WHITE:
                return WHITECells;
            default:
                return null; // better solution??
        }
    }

    //@Overload
    //get cells within rooms, which means all cells from same color
    public ArrayList<Cell> getCellsWithinRoom(Color color){
        switch(color){
            case YELLOW:
                return YELLOWCells;
            case GREEN:
                return GREENCells;
            case BLUE:
                return BLUECells;
            case PINK:
                return PINKCells;
            case RED:
                return REDCells;
            case WHITE:
                return WHITECells;
            default:
                return null; // better solution??
        }
    }
   //get all visible cells from one cell, general vision method
   public ArrayList<Cell> getAllVisibleCells(Cell cellX){
       ArrayList<Cell> allVisibleCells = new ArrayList<>();
       allVisibleCells.addAll(getCellsWithinRoom(cellX));
       int i =0;
       while(i < 4 ){
           if(cellX.getAdjacentCells()[i].getCellID() != 0 && !checkWithinRoom(cellX, cellX.getAdjacentCells()[i]) && checkDoor(cellX, cellX.getAdjacentCells()[i])){
               allVisibleCells.add(cellX.getAdjacentCells()[i]);
               allVisibleCells.addAll(getCellsWithinRoom(cellX.getAdjacentCells()[i]));
           }
           i++;
       }
       return allVisibleCells;
   }
   public boolean checkAllVisibleCells(Cell X, Cell Y){
        return getAllVisibleCells(Y).contains(X);
   }

   //get all visible cells except in your cell
    public ArrayList<Cell> getVisibleCellsWithoutYourCell(Cell cellX){
        ArrayList<Cell> allVisibleCellsWithoutYourCell = getAllVisibleCells(cellX);
        allVisibleCellsWithoutYourCell.remove(cellX);
        return allVisibleCellsWithoutYourCell;
    }
    public boolean checkVisibleCellsWithoutYourCell(Cell X, Cell Y){
        return getVisibleCellsWithoutYourCell(Y).contains(X);
    }

    //get all visible cells two steps away, which means not your cell and also for adjacent cells
    public ArrayList<Cell> getVisibleTwoAwayCells(Cell cellX){
        ArrayList<Cell> visibleTwoAwayCells = getVisibleCellsWithoutYourCell(cellX);
        int i =0;
        while(i < 4 ){
            if(cellX.getAdjacentCells()[i].getCellID() != 0)
                visibleTwoAwayCells.remove(cellX.getAdjacentCells()[i]);
            i++;
        }
        return visibleTwoAwayCells;
    }
    public boolean checkVisibleTwoAwayCells(Cell X, Cell Y){
        return getVisibleTwoAwayCells(Y).contains(X);
    }

    //get cells from a room you can see but not your room, return all the visible color of the room
    public ArrayList<Color> getVisibleRoomWithoutYourRoom(Cell cellX){
        ArrayList<Color> visibleRoomWithoutYourRoom = new ArrayList<Color>();
        int i = 0;
        while(i < 4){
            if(cellX.getCellColor() != cellX.getAdjacentCells()[1].getCellColor())
                visibleRoomWithoutYourRoom.add(cellX.getAdjacentCells()[1].getCellColor());
            i++;
        }
        return visibleRoomWithoutYourRoom;
    }

    //get at most two cells in one direction, with a sequence
    public ArrayList<Cell> getCardinalTwoCells(Cell cellX, int direction){
        ArrayList<Cell> cardinalTwoCells = new ArrayList<Cell>();
        if(cellX.getNextCell(direction) != null){
            cardinalTwoCells.add(cellX.getNextCell(direction));
            if(cellX.getNextCell(direction).getNextCell(direction) != null)
                cardinalTwoCells.add(cellX.getNextCell(direction).getNextCell(direction));
        }
        return cardinalTwoCells;
    }
    public boolean checkCardinalTwoCells(Cell X, Cell Y, int direction){
        return getAllCardinalCells(Y, direction).contains(X);
    }

    //get all cells in one direction
    public ArrayList<Cell> getAllCardinalCells(Cell cellX, int direction){
        ArrayList<Cell> allCardinalCells = new ArrayList<Cell>();
        allCardinalCells.add(cellX);
        Cell cellI = cellX;
        while(cellI.getNextCell(direction) != null){
            allCardinalCells.add(cellI.getNextCell(direction));
            cellI = cellI.getNextCell(direction);
        }
        return allCardinalCells;
    }
    public boolean checkAllCardinalCells(Cell X, Cell Y, int direction){
        return getAllCardinalCells(Y, direction).contains(X);
    }

    //*************** the next part of code is about the run action**************************

    //get all available next run cells
    public ArrayList<Cell> getAvailableOneWalkCell(Cell cellX){
        ArrayList<Cell> availableOneWalkCell = new ArrayList<Cell>();
        int i = 0;
        while(i < 4){
            if(cellX.getAdjacentCells()[i].getCellID() != 0 && checkDoor(cellX, cellX.getAdjacentCells()[i]))
                availableOneWalkCell.add(cellX.getAdjacentCells()[i]);
            i++;
        }
        return availableOneWalkCell;
    }
    public int getDirectionFromCellToCell(Cell sor, Cell dest){
        int direction = 0;
        while(direction < 4){
            if(sor.getNextCell(direction) == dest) break;
            direction++;
        }
        return direction;
    }
    public boolean checkAvailableOneWalkCell(Cell X, Cell Y){
        return getAvailableOneWalkCell(Y).contains(X);
    }

    public Cell getGenerationCellByColor(Color cellColor){
        switch (cellColor){
            case YELLOW: {
                for(Cell c : YELLOWCells){
                    if(c.getType() == 0) continue;
                    else return c;
                } break;
            }
            case BLUE:{
                for(Cell c : BLUECells){
                    if(c.getType() == 0) continue;
                    else return c;
                } break;
            }
            case RED:{
                for(Cell c : REDCells){
                    if(c.getType() == 0) continue;
                    else return c;
                } break;
            }
            case WHITE: {
                for(Cell c : WHITECells){
                    if(c.getType() == 0) continue;
                    else return c;
                } break;
            }
            case PINK:{
                for(Cell c : PINKCells){
                    if(c.getType() == 0) continue;
                    else return c;
                } break;
            }
            case GREEN:{
                for(Cell c : GREENCells){
                    if(c.getType() == 0) continue;
                    else return c;
                } break;
            }
        }
        return null;
    }
}
