package it.polimi.ingsw.se2019.Adrenaline.client.view.CLI;

import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.PowerupCard;

import java.util.ArrayList;

/**
 *
 * The ShowPowerupCard is to show the powerupcard you have
 *
 * @author  li xuejing
 */
public class ShowPowerupCard {

    private ArrayList<PowerupCard> powerupCards;
    private int length;

    public ShowPowerupCard(ArrayList<PowerupCard> powerupCards) {
        this.powerupCards = powerupCards;
        length = powerupCards.size();
        print();
    }

    /**
     *
     * print the powerupcard you have
     */
    protected void print(){

        CommandLineTable st = new CommandLineTable("\u001b[1;33m","\u001b[1;31m");
        //st.setRightAlign(true);//if true then cell text is right aligned
        switch (length){
            case 1:
                st.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
                st.setHeaders("powerup",powerupCards.get(0).getCardName());//optional - if not used then there will be no header and horizontal lines
                st.addRow("color", powerupCards.get(0).getColor());
                st.print();
                break;
            case 2:
                st.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
                st.setHeaders("powerup",powerupCards.get(0).getCardName(),powerupCards.get(1).getCardName());//optional - if not used then there will be no header and horizontal lines
                st.addRow("color", powerupCards.get(0).getColor(), powerupCards.get(1).getColor());
                st.print();
                break;
            case 3:
                st.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
                st.setHeaders("powerup",powerupCards.get(0).getCardName(),powerupCards.get(1).getCardName(),powerupCards.get(2).getCardName());//optional - if not used then there will be no header and horizontal lines
                st.addRow("color",  powerupCards.get(0).getColor(), powerupCards.get(1).getColor(),powerupCards.get(2).getColor());
                st.print();
                break;
            case 4:
                st.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
                st.setHeaders("powerup",powerupCards.get(0).getCardName(),powerupCards.get(1).getCardName(),powerupCards.get(2).getCardName(), powerupCards.get(3).getCardName());//optional - if not used then there will be no header and horizontal lines
                st.addRow("color", powerupCards.get(0).getColor(), powerupCards.get(1).getColor(),powerupCards.get(2).getColor(), powerupCards.get(3).getColor());
                st.print();
                break;
            case 0:
            default:
                System.out.println("you have no powerupCards!");break;
        }
    }
}
