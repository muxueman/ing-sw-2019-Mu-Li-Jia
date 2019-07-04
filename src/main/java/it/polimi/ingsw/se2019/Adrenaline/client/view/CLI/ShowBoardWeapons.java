package it.polimi.ingsw.se2019.Adrenaline.client.view.CLI;

import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.WeaponCard;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.BoardStatus;

import java.util.ArrayList;

public class ShowBoardWeapons {

    private ArrayList<WeaponCard> weaponCardsB;
    private ArrayList<WeaponCard> weaponCardsR;
    private ArrayList<WeaponCard> weaponCardsY;

    public ShowBoardWeapons(BoardStatus boardStatus) {
        this.weaponCardsB = boardStatus.getWeaponsInCell().get(3);
        this.weaponCardsR = boardStatus.getWeaponsInCell().get(5);
        this.weaponCardsY = boardStatus.getWeaponsInCell().get(12);
        printB();
        printR();
        printY();
    }

    public void printB(){

        CommandLineTable st = new CommandLineTable("\u001b[1;36m","\u001b[1;31m");
        int length = weaponCardsB.size();
        switch(length){
            case 1:
                st.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
                st.addRow("Weapon Cards in Cell 3 ",weaponCardsB.get(0).getCardName());
                st.print();
                break;
            case 2:
                st.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
                st.addRow("Weapon Cards in Cell 3 ",weaponCardsB.get(0).getCardName(),weaponCardsB.get(1).getCardName());//optional - if not used then there will be no header and horizontal lines
                st.print();
                break;
            case 3:
                //st.setRightAlign(true);//if true then cell text is right aligned
                st.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
                st.addRow("Weapon Cards in Cell 3 ",weaponCardsB.get(0).getCardName(),weaponCardsB.get(1).getCardName(),weaponCardsB.get(2).getCardName());//optional - if not used then there will be no header and horizontal lines
                st.print();
                break;
            case 0:
            default:System.out.println("Weapon cards in Cell 3 : null");break;
        }

    }


    public void printR(){

        CommandLineTable st = new CommandLineTable("\u001b[1;31m","\u001b[1;31m");
        int length = weaponCardsR.size();
        switch(length){
            case 1:
                st.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
                st.addRow("Weapon Cards in Cell 5 ",weaponCardsR.get(0).getCardName());
                st.print();
                break;
            case 2:
                st.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
                st.addRow("Weapon Cards in Cell 5 ",weaponCardsR.get(0).getCardName(),weaponCardsR.get(1).getCardName());//optional - if not used then there will be no header and horizontal lines
                st.print();
                break;
            case 3:
                //st.setRightAlign(true);//if true then cell text is right aligned
                st.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
                st.addRow("Weapon Cards in Cell 5 ",weaponCardsR.get(0).getCardName(),weaponCardsR.get(1).getCardName(),weaponCardsR.get(2).getCardName());//optional - if not used then there will be no header and horizontal lines
                st.print();
                break;
            case 0:
            default:System.out.println("Weapon cards in Cell 5 : null");break;
        }
    }

    public void printY(){

        CommandLineTable st = new CommandLineTable("\u001b[1;33m","\u001b[1;31m");
        int length = weaponCardsY.size();
        switch(length){
            case 1:
                st.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
                st.addRow("Weapon Cards in Cell 12",weaponCardsY.get(0).getCardName());
                st.print();
                break;
            case 2:
                st.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
                st.addRow("Weapon Cards in Cell 12",weaponCardsY.get(0).getCardName(),weaponCardsY.get(1).getCardName());//optional - if not used then there will be no header and horizontal lines
                st.print();
                break;
            case 3:
                //st.setRightAlign(true);//if true then cell text is right aligned
                st.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
                st.addRow("Weapon Cards in Cell 12",weaponCardsY.get(0).getCardName(),weaponCardsY.get(1).getCardName(),weaponCardsY.get(2).getCardName());//optional - if not used then there will be no header and horizontal lines
                st.print();
                break;
            case 0:
            default:System.out.println("Weapon cards in Cell 12: null");break;
        }
    }
}


