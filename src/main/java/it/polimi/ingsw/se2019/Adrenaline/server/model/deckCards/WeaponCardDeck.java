package it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards;

import com.google.gson.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;


public class WeaponCardDeck implements Serializable {


    private ArrayList<WeaponCard> weaponCards;
    private ArrayList<WeaponCard> redWeapons;
    private ArrayList<WeaponCard> yellowWeapons;
    private ArrayList<WeaponCard> blueWeapons;
    private transient BufferedReader reader;

    public WeaponCardDeck() {
        weaponCards = new ArrayList<>();
        redWeapons = new ArrayList<>();
        yellowWeapons = new ArrayList<>();
        blueWeapons = new ArrayList<>();

        try {
            Gson gson = new Gson();

            reader = new BufferedReader(new FileReader("src/main/resource/json/WeaponCard.json"));
            JsonParser parser = new JsonParser();
            JsonObject json = parser.parse(reader).getAsJsonObject();
            JsonArray weaponCardArray = json.getAsJsonArray("weaponCardDeck");
            for (JsonElement weaponCardElement : weaponCardArray) {
                WeaponCard weaponCardReaded = gson.fromJson(weaponCardElement.toString(), WeaponCard.class);
                weaponCards.add(weaponCardReaded);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            {
                if(null != reader ){
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        Collections.shuffle(weaponCards);
        colorDistributed();
    }
    public void colorDistributed(){
        for(WeaponCard w : weaponCards){
            switch (w.getColor()){
                case "yellow": this.yellowWeapons.add(w); break;
                case "red": this.redWeapons.add(w); break;
                case "blue": this.blueWeapons.add(w); break;
                default: break;
            }
        }
    }

    public ArrayList<WeaponCard> getWeaponCards() {
        return weaponCards;
    }

    public void setWeaponCards(ArrayList<WeaponCard> weaponCards) {
        this.weaponCards = weaponCards;
    }

    public ArrayList<WeaponCard> getRedWeapons() {
        return redWeapons;
    }

    public void setRedWeapons(ArrayList<WeaponCard> redWeapons) {
        this.redWeapons = redWeapons;
    }

    public ArrayList<WeaponCard> getYellowWeapons() {
        return yellowWeapons;
    }

    public void setYellowWeapons(ArrayList<WeaponCard> yellowWeapons) {
        this.yellowWeapons = yellowWeapons;
    }

    public ArrayList<WeaponCard> getBlueWeapons() {
        return blueWeapons;
    }

    public void setBlueWeapons(ArrayList<WeaponCard> blueWeapons) {
        this.blueWeapons = blueWeapons;
    }

    @Override
    public String toString() {
        return "WeaponCardDeck{" +
                "weaponCards=" + weaponCards +
                '}';
    }
}