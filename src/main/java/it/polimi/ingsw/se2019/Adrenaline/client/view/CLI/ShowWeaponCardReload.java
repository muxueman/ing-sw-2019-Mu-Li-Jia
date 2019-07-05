package it.polimi.ingsw.se2019.Adrenaline.client.view.CLI;

import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.WeaponCard;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 *The ShowWeaponCardReload is to show the weapon card wait for reload
 * for now cant use
 *
 * @author li xuejing
 */
public class ShowWeaponCardReload{

    private ArrayList<WeaponCard> weaponCards;
    private int length;

    /**
     * this methods is to
     * @param weaponsOwned of a player
     */
    public ShowWeaponCardReload(Map<WeaponCard, Boolean> weaponsOwned) {
        weaponCards = new ArrayList<>();
        for (WeaponCard w : weaponsOwned.keySet()){
            if (!weaponsOwned.get(w)){
                weaponCards.add(w);
            }
        }
        length = weaponCards.size();
        print();
    }
    /**
     *
     * print the cards
     *
     * @return weapons player have
     */
    public ArrayList<WeaponCard> getWeaponCards(){return weaponCards;}
    protected void print(){

        System.out.println("the weapon cards you need to reload:");
        CommandLineTable st = new CommandLineTable("\u001b[1;37m","\u001b[1;35m");
        switch (length){
            case 1:
                st.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
                st.setHeaders("weapon",weaponCards.get(0).getCardName());//optional - if not used then there will be no header and horizontal lines
                st.print();
                break;
            case 2:
                st.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
                st.setHeaders("weapon",weaponCards.get(0).getCardName(),weaponCards.get(1).getCardName());//optional - if not used then there will be no header and horizontal lines
                st.print();
                break;
            case 3:
                st.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
                st.setHeaders("weapon",weaponCards.get(0).getCardName(), "weapon",weaponCards.get(1).getCardName(), "weapon",weaponCards.get(2).getCardName());//optional - if not used then there will be no header and horizontal lines
                st.print();
                break;
            case 0:
                default:
                    System.out.println("you have no Weapon Cards!");break;
        }
    }
}