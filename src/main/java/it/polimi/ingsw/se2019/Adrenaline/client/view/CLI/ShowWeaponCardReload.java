package it.polimi.ingsw.se2019.Adrenaline.client.view.CLI;

import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.WeaponCard;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 *The ShowWeaponCardReload is to show the weaponcard wait for reload
 * for now cant use
 *
 * @author li xuejing
 */
public class ShowWeaponCardReload{

    private Map<WeaponCard, Boolean> weaponsOwned;
    private ArrayList<WeaponCard> weaponCards;
    private int length;

    /**
     * this methods is to
     * @param weaponsOwned
     */
    public ShowWeaponCardReload(Map<WeaponCard, Boolean> weaponsOwned) {
        this.weaponsOwned = weaponsOwned;
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
     * @return
     */
    public ArrayList<WeaponCard> getWeaponCards(){return weaponCards;}
    protected void print(){

        System.out.println("the weapon cards you need to reload:");
        CommandLineTable st = new CommandLineTable("\u001b[1;37m","\u001b[1;35m");
        //st.setRightAlign(true);//if true then cell text is right aligned
        switch (length){
            case 0: System.out.println("you have no Weapon Cards!");break;
            case 1:
                st.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
                st.setHeaders("weapon",weaponCards.get(0).getCardName());//optional - if not used then there will be no header and horizontal lines
                //  st.addRow("ammo cost [red,blue,yellow]", String.valueOf(weaponCards.get(0).getBasicammoCost()[0])+ String.valueOf(weaponCards.get(0).getBasicammoCost()[1])+ String.valueOf(weaponCards.get(0).getBasicammoCost()[2]));
                //st.addRow("basic effect", weaponCards.get(0).getBasicEffect());
                //  st.addRow("special effect", weaponCards.get(0).getSpecialEffectName().toString());
                st.print();
                break;
            case 2:
                st.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
                st.setHeaders("weapon",weaponCards.get(0).getCardName(),weaponCards.get(1).getCardName());//optional - if not used then there will be no header and horizontal lines
                // st.addRow("ammo cost [red,blue,yellow]", weaponCards.get(0).getBasicammoCost().toString(), weaponCards.get(1).getBasicammoCost().toString());
                //st.addRow("basic effect", weaponCards.get(0).getBasicEffect(), weaponCards.get(1).getBasicEffect());
                //  st.addRow("special effect", weaponCards.get(0).getSpecialEffectName().toString(),weaponCards.get(1).getSpecialEffectName().toString());
                st.print();
                break;
            case 3:
                st.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
                st.setHeaders("weapon",weaponCards.get(0).getCardName());//optional - if not used then there will be no header and horizontal lines
                // st.addRow("ammo cost [red,blue,yellow]", weaponCards.get(0).getBasicammoCost().toString(), weaponCards.get(1).getBasicammoCost().toString(),weaponCards.get(2).getCardName());
                //st.addRow("basic effect", weaponCards.get(0).getBasicEffect(), weaponCards.get(1).getBasicEffect(), weaponCards.get(2).getBasicEffect());
                //  st.addRow("special effect", weaponCards.get(0).getSpecialEffectName().toString(),weaponCards.get(1).getSpecialEffectName().toString(),weaponCards.get(2).getSpecialEffectName().toString());
                st.print();
                break;
        }
    }
}