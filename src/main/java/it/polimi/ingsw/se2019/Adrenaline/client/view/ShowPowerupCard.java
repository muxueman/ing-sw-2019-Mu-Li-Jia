package it.polimi.ingsw.se2019.Adrenaline.client.view;

import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.PowerupCard;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class ShowPowerupCard {

    private ArrayList<PowerupCard> powerupCards;
    private int length;

    public ShowPowerupCard(ArrayList<PowerupCard> powerupCards) {
        this.powerupCards = powerupCards;
        length = powerupCards.size();
        print();
    }

    protected void print(){

        CommandLineTable st = new CommandLineTable();
        //st.setRightAlign(true);//if true then cell text is right aligned
        switch (length){
            case 0: System.out.println("you have no powerupCards!");break;
            case 1:
                st.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
                st.setHeaders("name",powerupCards.get(0).getCardName());//optional - if not used then there will be no header and horizontal lines
                st.addRow("color", powerupCards.get(0).getColor());
                st.addRow("effect", powerupCards.get(0).getEffect());
                st.addRow("note", powerupCards.get(0).getNote());
                st.print();
                break;
            case 2:
                st.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
                st.setHeaders("name",powerupCards.get(0).getCardName(),powerupCards.get(1).getCardName());//optional - if not used then there will be no header and horizontal lines
                st.addRow("color", powerupCards.get(0).getColor(), powerupCards.get(1).getColor());
                st.addRow("effect", powerupCards.get(0).getEffect(), powerupCards.get(1).getCardName());
                st.addRow("note", powerupCards.get(0).getNote(), powerupCards.get(1).getCardName());
                st.print();
                break;
            case 3:
                st.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
                st.setHeaders("name",powerupCards.get(0).getCardName(),powerupCards.get(1).getCardName(),powerupCards.get(2).getCardName());//optional - if not used then there will be no header and horizontal lines
                st.addRow("color", powerupCards.get(0).getColor(), powerupCards.get(1).getColor(),powerupCards.get(2).getColor());
                st.addRow("effect", powerupCards.get(0).getEffect(), powerupCards.get(1).getCardName(), powerupCards.get(2).getCardName());
                st.addRow("note", powerupCards.get(0).getNote(), powerupCards.get(1).getCardName(), powerupCards.get(2).getCardName());
                st.print();
                break;
            case 4:
                st.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
                st.setHeaders("name",powerupCards.get(0).getCardName(),powerupCards.get(1).getCardName(),powerupCards.get(2).getCardName(), powerupCards.get(3).getCardName());//optional - if not used then there will be no header and horizontal lines
                st.addRow("color", powerupCards.get(0).getColor(), powerupCards.get(1).getColor(),powerupCards.get(2).getColor(), powerupCards.get(3).getColor());
                st.addRow("effect", powerupCards.get(0).getEffect(), powerupCards.get(1).getCardName(), powerupCards.get(2).getCardName(), powerupCards.get(2).getCardName());
                st.addRow("note", powerupCards.get(0).getNote(), powerupCards.get(1).getCardName(), powerupCards.get(2).getCardName(), powerupCards.get(2).getCardName());
                st.print();
                break;
        }

    }
}
