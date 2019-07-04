package it.polimi.ingsw.se2019.Adrenaline.server.model;

import java.io.Serializable;

/**
 *the color of ammos and powerupCards
 *
 * @author Jia moxin
 */
public enum AmmoColor implements Serializable {
    RED("RED"),
    YELLOW("YELLOW"),
    BLUE("BLUE");

    private String ammoColor;

    /**
     *
     * The AmmoColor get the ammocolor
     *
     * @param ammoColor
     */
    private AmmoColor(String ammoColor){
		this.ammoColor = ammoColor;
    }


    public String getAmmoColor(){
		return this.ammoColor;
	}
}