package it.polimi.ingsw.se2019.Adrenaline.server.model;
/**
 the color of ammos and powerupCards
 */
public enum AmmoColor {
    RED("RED"),
    YELLOW("YELLOW"),
    BLUE("BLUE");

    private String ammoColor;
    private AmmoColor(String ammoColor){
		this.ammoColor = ammoColor;
    }
    public String getAmmoColor(){
		return this.ammoColor;
	}
}