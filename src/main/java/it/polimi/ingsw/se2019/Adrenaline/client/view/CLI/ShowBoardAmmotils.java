package it.polimi.ingsw.se2019.Adrenaline.client.view.CLI;

import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.BoardStatus;

/**
 *
 * to show the Ammotilecards in the board
 *collect in the showtotal
 * @author li xuejing
 *
 */
public class ShowBoardAmmotils {

    private BoardStatus boardStatus;

    /**
     *
     * show the ammotilecards in the board
     *
     * @param boardStatus from immutable
     */
    public ShowBoardAmmotils(BoardStatus boardStatus){
        this.boardStatus = boardStatus;
        printA();
    }
    private void printA(){
        for (int cellID : boardStatus.getAmmotilesInCell().keySet()){
            System.out.println("\u001b[1;38m" + cellID + ":  " +boardStatus.getAmmotilesInCell().get(cellID).getContent());
        }
    }

}
