package it.polimi.ingsw.se2019.Adrenaline.server.model.action;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.WeaponCard;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Cell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * interface ActionStrategy
 * @author Xueman Mu
 */

public class ActionShoot{
    //controller 先判断应用的是哪张武器卡， 再决定调用哪个类型的shoot， 先不考虑side effect
    //this is the most basic mode, gives you all the players you can shoot, without second choice,

    public ArrayList<Player> getTargetsfromArray(Player shooter, WeaponCard weaponCard){
        ArrayList<Player> targetsInVision = new ArrayList<>();
        ArrayList<Cell> cellOfTargets = new ArrayList<>();
        int cellIndex = 0;
        int playerIndex = 0;
        switch(weaponCard.getCardName()){
            //all cell you can see but not your cell
            case "VORTEX CANNON":
                cellOfTargets = shooter.getPlayBoard().getMap().getVisibleCellsWithoutYourCell(shooter.getCurrentCell());
                break;
            //all cell you can see
            case "LOCK RIFLE":
                cellOfTargets = shooter.getPlayBoard().getMap().getAllVisibleCells(shooter.getCurrentCell());
                break;
            //your cell
            case "ELECTROSCYTHE":
                cellOfTargets.add(shooter.getCurrentCell());
                break;
            //all cell you can see
            case "MACHINE GUN":
                cellOfTargets = shooter.getPlayBoard().getMap().getAllVisibleCells(shooter.getCurrentCell());
                break;
            //all cell on map
            case "TRACTOR BEAM":
                cellOfTargets = shooter.getPlayBoard().getMap().getAllCells();
                break;
            //all cell you can see
            case "T.H.O.R.":
                cellOfTargets = shooter.getPlayBoard().getMap().getAllVisibleCells(shooter.getCurrentCell());
                break;
            // visible room but not your room
            case "FURNACE":
                //先选出所有可以看到的cell，加到celloftargets
                while(cellIndex < shooter.getPlayBoard().getMap().getAllVisibleCells(shooter.getCurrentCell()).size()){
                    cellOfTargets.add(shooter.getPlayBoard().getMap().getAllVisibleCells(shooter.getCurrentCell()).get(cellIndex));
                    cellIndex++;
                }
                cellIndex = 0;
                //从所有的可见的中再删除和自己在同一room的cell
                while(cellIndex < shooter.getPlayBoard().getMap().getCellsWithinRoom(shooter.getCurrentCell()).size()){
                    cellOfTargets.remove(shooter.getPlayBoard().getMap().getCellsWithinRoom(shooter.getCurrentCell()));
                    cellIndex++;
                }
                cellIndex = 0;
                break;
            //all cell you can see
            case "PLASMA GUN":
                cellOfTargets = shooter.getPlayBoard().getMap().getAllVisibleCells(shooter.getCurrentCell());
                break;
            //all cell you can not see
            case "HEATSEEKER":
                //先选出所有cell，
                while(cellIndex < shooter.getPlayBoard().getMap().getAllCells().size()){
                    cellOfTargets.add(shooter.getPlayBoard().getMap().getAllCells().get(cellIndex));
                    cellIndex++;
                }
                cellIndex = 0;
                //删除所有可见的cell
                while(cellIndex < shooter.getPlayBoard().getMap().getAllVisibleCells(shooter.getCurrentCell()).size()){
                    cellOfTargets.remove(shooter.getPlayBoard().getMap().getAllVisibleCells(shooter.getCurrentCell()).get(cellIndex));
                    cellIndex++;
                }
                cellIndex = 0;
                break;

            // cells you can see but at least two cells away from yours
            case "WHISPER":
                cellOfTargets = shooter.getPlayBoard().getMap().getVisibleTwoAwayCells(shooter.getCurrentCell());
                break;
            // cells you can see but at least one cell away from yours
            case "HELLION":
                //firstly select all visible cells
                while(cellIndex < shooter.getPlayBoard().getMap().getVisibleCellsWithoutYourCell(shooter.getCurrentCell()).size()){
                    cellOfTargets.add(shooter.getPlayBoard().getMap().getVisibleCellsWithoutYourCell(shooter.getCurrentCell()).get(cellIndex));
                    cellIndex++;
                }
                //then delet those one walk away cells
                cellIndex = 0;
                while(cellIndex < shooter.getPlayBoard().getMap().getAvailableOneWalkCell(shooter.getCurrentCell()).size()){
                    cellOfTargets.remove(shooter.getPlayBoard().getMap().getAvailableOneWalkCell(shooter.getCurrentCell()).get(cellIndex));
                    cellIndex++;
                }
                break;
            // all cell you can see
            case "ZX-2":
                cellOfTargets =  cellOfTargets = shooter.getPlayBoard().getMap().getAllVisibleCells(shooter.getCurrentCell());
                break;
            //all cell you can see
            case "GRENADE LAUNCHER":
                cellOfTargets =  cellOfTargets = shooter.getPlayBoard().getMap().getAllVisibleCells(shooter.getCurrentCell());
                break;
            //all cell you can see
            case "SHOTGUN":
                cellOfTargets =  cellOfTargets = shooter.getPlayBoard().getMap().getAllVisibleCells(shooter.getCurrentCell());
                break;
            //all cell you can see but not your cell
            case "ROCKET LAUNCHER":
                cellOfTargets = shooter.getPlayBoard().getMap().getVisibleCellsWithoutYourCell(shooter.getCurrentCell());
                break;
            //exactly one move away cell
            case "POWER GLOVE":
                cellOfTargets = shooter.getPlayBoard().getMap().getAvailableOneWalkCell(shooter.getCurrentCell());

            //choose a cardinal direction
            case "RAILGUN":
                while(cellIndex < shooter.getPlayBoard().getMap().getAllCardinalCells(shooter.getCurrentCell(), 0).size()){
                    cellOfTargets.add(shooter.getPlayBoard().getMap().getAllCardinalCells(shooter.getCurrentCell(), 0).get(cellIndex));
                    cellIndex++;
                }














        }
        while(cellIndex < cellOfTargets.size()) {
            while(playerIndex  < cellOfTargets.get(cellIndex).getCellPlayers().size()){
                targetsInVision.add(cellOfTargets.get(cellIndex).getCellPlayers().get(playerIndex));
                playerIndex++;
            }
            cellIndex++;
        }
        return  targetsInVision;
    }
    //special case for "FLAMETHROWER", you can choose one player from each case
    public Map<Player,Integer> getTargetsfromMap(Player shooter, WeaponCard weaponCard){
        Map<Player, Integer> targetsInVision = new HashMap<>();
        Map<Cell, Integer> cellOfTargets = new HashMap<>();
        int cellIndex = 0;
        int playerIndex = 0;
        switch(weaponCard.getCardName()){
            // first target from your one move away cell, second target in the same direction
            case "FLAMTHROWER":

        }
        return targetsInVision;
    }
    //special case for",""""", you can choose player in first case, or in another.




    //
}
