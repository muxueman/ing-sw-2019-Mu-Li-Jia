package it.polimi.ingsw.se2019.Adrenaline.client.view.CLI;

import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.WeaponCard;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 * The ShowWeaponCard is to show the weapon card you have
 *
 * @author li xuejing
 */
public class ShowWeaponCard {

    private ArrayList<WeaponCard> weaponCards;
    private int length;

    public ShowWeaponCard(Map<WeaponCard, Boolean> weaponsOwned) {

        weaponCards = new ArrayList<>();
        for (WeaponCard w : weaponsOwned.keySet()){
            if (weaponsOwned.get(w)){
                weaponCards.add(w);
            }
        }
        length = weaponCards.size();
        print();
    }

    /**
     *
     * print the cards
     */
    protected void print(){

        System.out.println("the weapon cards you have:");
        CommandLineTable st = new CommandLineTable("\u001b[1;37m","\u001b[1;35m");
        //st.setRightAlign(true);//if true then cell text is right aligned
        switch (length){
            case 1:
                st.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
                st.addRow("weapon",weaponCards.get(0).getCardName());//optional - if not used then there will be no header and horizontal lines
                st.print();
                break;
            case 2:
                st.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
                st.addRow("weapon",weaponCards.get(0).getCardName(),weaponCards.get(1).getCardName());//optional - if not used then there will be no header and horizontal lines
                st.print();
                break;
            case 3:
                st.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
                st.addRow("weapon",weaponCards.get(0).getCardName(), weaponCards.get(1).getCardName(), weaponCards.get(2).getCardName());//optional - if not used then there will be no header and horizontal lines
                st.print();
                break;
            case 0:
                default:
                    System.out.println("you have no Weapon Cards!");break;

        }
    }
}