package it.polimi.ingsw.se2019.Adrenaline.server.model;

import java.io.Serializable;

/**
 *the color of players and rooms
 *@author Xueman Mu
 */

public enum Color implements Serializable {
    YELLOW("YELLOW"),
    GREEN("GREEN"),
    BLUE("BLUE"),
    WHITE("WHITE"),
    PINK("PINK"),
    RED("RED");
    
    private String color;

    /**
     *
     * The Color is to get the string color
     *
     * @param color
     */
    private Color(String color){
		this.color = color;
    }
    
    public String getColor(){
      return this.color;
	}
}


