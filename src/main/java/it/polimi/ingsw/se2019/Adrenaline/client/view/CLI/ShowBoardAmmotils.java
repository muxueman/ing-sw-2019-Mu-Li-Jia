package it.polimi.ingsw.se2019.Adrenaline.client.view.CLI;

import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.BoardStatus;

import java.util.ArrayList;

public class ShowBoardAmmotils {

    BoardStatus boardStatus;

    public ShowBoardAmmotils(BoardStatus boardStatus){
        this.boardStatus = boardStatus;
        printA();
    }
    protected void printA(){
        for (int cellID : boardStatus.getAmmotilesInCell().keySet()){
            System.out.println("\u001b[1;38m" + cellID + ":  " +boardStatus.getAmmotilesInCell().get(cellID).getContent());
        }
    }

}
