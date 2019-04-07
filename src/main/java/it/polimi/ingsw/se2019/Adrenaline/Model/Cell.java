package it.polimi.ingsw.se2019.Adrenaline.Model;


import it.polimi.ingsw.se2019.Adrenaline.Model.PlayBoard;

/**
 * 
 */
public class Cell {

    /**
     * Default constructor
     */
    public Cell() {
    }

    /**
     * 
     */
    private int cellID;

    /**
     * 
     */
    private Color cellColor;

    /**
     * 
     */
    private String cellType;

    /**
     * 
     */
    private ArrayList<Player> cellPlayers;

    /**
     * 
     */
    private ArrayList<Cell> nextcell;



    public void initialCell() {
        // TODO implement here

    }


    public Color getCellColor() {
        // TODO implement here
        return null;
    }


    public ArrayList<Player> getPlayer() {
        // TODO implement here
        return null;
    }


    public ArrayList<Cell> availableShootCell() {
        // TODO implement here
        return null;
    }


    public ArrayList<Cell> availableWalkCell() {
        // TODO implement here
        return null;
    }


    public ArrayList<Cell> getAllNextCell() {
        // TODO implement here
        return null;
    }


    public Cell getNextCell(String direction) {
        // TODO implement here
        return null;
    }


    public ArrayList<Cell> getCellOfRoom() {
        // TODO implement here
        return null;
    }

}