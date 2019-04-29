package it.polimi.ingsw.se2019.Adrenaline.Model;
/**
 the color of ammos and powerupCards
 */
public enum AmmoColor {
    RED,
    YELLOW,
    BLUE;

    private String ammoColor;
    private AmmoColor(String ammoColor){
		this.ammoColor = ammoColor;
    }
    
    public String getAmmoColor(){
		return this.ammoColor;
	}
}