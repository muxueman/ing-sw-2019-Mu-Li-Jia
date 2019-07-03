package it.polimi.ingsw.se2019.Adrenaline.server.model.action;
import it.polimi.ingsw.se2019.Adrenaline.server.model.AmmoColor;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.PowerupCard;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.WeaponCard;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Cell;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.CommonCell;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.GenerationCell;
import it.polimi.ingsw.se2019.Adrenaline.utils.exceptions.InvalidGrabException;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * interface ActionStrategy
 * @author Xueman Mu
 */

public class ActionGrab implements Serializable {

    //pick 之前 应由controller先判断player所在位置， 应该捡武器卡 或其他， 如果捡武器卡 应决定捡哪一张

    public void pickAmmoTile(Player player) {
        //player.setCurrentCell((CommonCell)player.getCurrentCell()) ;
        int[] ammoOnCard =((CommonCell) player.getCurrentCell()).getAmmoColor();
        int i = 0;
        while(i < 3) {
            i++;
            switch (ammoOnCard[i-1]){
                case 0: if(player.getPowerupsOwned().size() == 3) break; else player.addPowerupCard();
                case 1: player.fillAmmo(AmmoColor.RED); break;
                case 2: player.fillAmmo(AmmoColor.BLUE); break;
                case 3: player.fillAmmo(AmmoColor.YELLOW); break;
            }
        }
        ((CommonCell) player.getCurrentCell()).removeAmmotileCard();
        player.getPlayBoard().getPickedCell().add(player.getCurrentCell());
    }
    //捡武器卡前应判断是否有足够的ammo
    public boolean checkPlayerAmmoAvailable(WeaponCard weaponCard, Player player){
        int[] ammoCost = weaponCard.getBasicammoCost();
        int i = 1;
        while(i<3){
            i++;
            switch (ammoCost[i-1]){
                case 0: continue;
                case 1:
                    if(player.getAmmoOwned()[0] > 0) continue;
                    else return false;
                case 2:
                    if(player.getAmmoOwned()[1] > 0) continue;
                    else return false;
                case 3:
                    if(player.getAmmoOwned()[2] > 0) continue;
                    else return false;
            }
        }
        return true;
    }

    public boolean pickWeaponCrad(Player player, int position){
        WeaponCard weaponCard = ((GenerationCell)player.getCurrentCell()).getWeaponCard().get(position);
        if(checkPlayerAmmoAvailable(weaponCard, player)){
            int[] ammoCost = weaponCard.getBasicammoCost();
            int i = 1;
            while(i < 3){
                i++;
                switch(ammoCost[i-1]){
                    case 0: continue;
                    case 1: player.consumeAmmo(AmmoColor.RED); break;
                    case 2: player.consumeAmmo(AmmoColor.BLUE); break;
                    case 3: player.consumeAmmo(AmmoColor.YELLOW); break;
                }
            }
            try {
                player.addWeaponCard(weaponCard);
            }
            catch (InvalidGrabException e){
                System.out.println(e);
                return false;
            }
            ((GenerationCell) player.getCurrentCell()).weaponTaken(position);
            player.getPlayBoard().getPickedCell().add(player.getCurrentCell());
            return true;
        }
        else return false;
    }

    /*
     * check if he has enough ammo to pick the weapon
     * start from ammocost[1], when you pick a new weapon, you dont have to pay the first ammo
     */

}