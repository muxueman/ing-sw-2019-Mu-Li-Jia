package it.polimi.ingsw.se2019.Adrenaline.server.model;

/**
 *the color of players and rooms
 *@author Xueman Mu
 */

public enum Color {
    YELLOW("YELLOW"),
    GREEN("GREEN"),
    BLUE("BLUE"),
    WHITE("WHITE"),
    PINK("PINK"),
    RED("RED");
    
    private String color;
    private Color(String color){
		this.color = color;
    }
    
    public String getColor(){
      return this.color;
	}
}


