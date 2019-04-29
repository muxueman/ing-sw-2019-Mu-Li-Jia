package it.polimi.ingsw.se2019.Adrenaline.Model;
/**
 *the color of players and rooms
 *@author Xueman Mu
 */
public enum Color {
    YELLOW("yellow"),
    GREEN("green"),
    BLUE("blue"),
    WHITE("white"),
    PINK("pink"),
    RED("red");
    
    private String color;
    private Color(String color){
		this.color = color;
    }
    
    public String getColor(){
      return this.color;
	}
}


