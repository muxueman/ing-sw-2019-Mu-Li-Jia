package it.polimi.ingsw.se2019.Adrenaline.utils.immutables;


import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.AmmotileCard;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.WeaponCard;

import org.fusesource.jansi.Ansi;
import java.util.ArrayList;
import static org.fusesource.jansi.Ansi.ansi;

public class CellStatus implements Status {

    private int cellID;
    private ArrayList<PlayerStatus> cellPlayers;
    private AmmotileCard ammotileCard;
    private WeaponCard[] weaponCards;
    //to identify the cell is which kind;( instance of )/ id = 3,5,12 are generationCell

    public CellStatus(int cellID) {
        this.cellID = cellID;
        this.cellPlayers = new ArrayList<PlayerStatus>();
    }

    public ArrayList<PlayerStatus> getCellPlayers(){
        return this.cellPlayers;
    }

    public void setCellPlayers(ArrayList<PlayerStatus> cellPlayers) { this.cellPlayers = cellPlayers;}
    public void setAmmotileCard(AmmotileCard ammotileCard) { this.ammotileCard = ammotileCard;}
    public void setWeaponCards(WeaponCard[] weaponCards) { this.weaponCards = weaponCards; }

    public AmmotileCard getAmmotileCard(){
        return ammotileCard;
    }
    public WeaponCard[] getWeaponCard(){
        return weaponCards;
    }
    public void addPlayer(PlayerStatus player){
        this.cellPlayers.add(player);
    }
    public void removePlayer(PlayerStatus player){
        this.cellPlayers.remove(player);
    }

    @Override
    public Ansi toAnsi(){
        return ansi().a("cell players:" + cellPlayers.toString());
    }
    @Override
    public String toString(){
        return "cell players:" + cellPlayers.toString() ;
    }
}
