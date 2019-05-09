package it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards;

import it.polimi.ingsw.se2019.Adrenaline.server.model.Color;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class WeaponCardTest {

    WeaponCard weaponCard = new WeaponCard();

    @Before
    public void setUp() throws Exception {

        System.out.println("test weaponcard:");
    }

    @After
    public void tearDown() throws Exception {

        System.out.println("test end.");
    }

    @Test
    public void testCardName() {

        weaponCard.setCardName("VORTEX CANNON");
        Assert.assertEquals("VORTEX CANNON", weaponCard.getCardName());
    }


    @Test
    public void testBasicammoCost() {

        ArrayList<Integer> basicammoCost = new ArrayList<>();
        //for the first ammocost 1 for red , 2 for blue , 3 for yellow , 0 for null
        basicammoCost.add(1);
        //for the second ammocost 1 for red , 2 for blue , 3 for yellow , 0 for null
        basicammoCost.add(2);
        //for the third ammocost 1 for red , 2 for blue , 3 for yellow , 0 for null
        basicammoCost.add(0);
        //for specialEffect 1 for the card has 1 specialeffect , 2 for the card has 2 specialeffects , 0 for null
        basicammoCost.add(1);

        Map<Color,Integer> testColor = new HashMap<>();
        testColor.put(Color.RED,1);
        testColor.put(Color.BLUE,2);
        testColor.put(Color.YELLOW,3);

        weaponCard.setBasicammoCost(basicammoCost);
        Assert.assertEquals(basicammoCost, weaponCard.getBasicammoCost());
    }

    @Test
    public void testDamageVision() {

        weaponCard.setDamageVision(12);
        Assert.assertEquals(12, weaponCard.getDamageVision().intValue());
    }

    @Test
    public void testDamageDeal() {

        ArrayList<Integer> damageDeal = new ArrayList<>();
        //this mean deal damage to how many target (0 for everyone) 1 for 1 target, 2 for 2 targets...
        damageDeal.add(0);
        //this mean the person who accept damage that real damage number ,2 for 2 real damage,0 for null
        damageDeal.add(2);
        //this mean will give the target how many marked damage,1 for 1 marked damage,2 for 2 marked damage, 0 for null
        damageDeal.add(1);

        weaponCard.setDamageDeal(damageDeal);
        Assert.assertEquals(damageDeal, weaponCard.getDamageDeal());
    }

    @Test
    public void testBasicEffect() {

        weaponCard.setBasicEffect("Choose a cell you can see but not your cell and choose a target on the cell or move away from it.move it onto the cell and give it 2 damage");

        Assert.assertEquals("Choose a cell you can see but not your cell and choose a target on the cell or move away from it.move it onto the cell and give it 2 damage",
                weaponCard.getBasicEffect());

    }

    @Test
    public void testSpecialEffectName() {

        ArrayList<String> specialEffectName = new ArrayList<>();

        specialEffectName.add("with black hole");

        weaponCard.setSpecialEffectName(specialEffectName);
        System.out.println("specialEffectName: " + weaponCard.getSpecialEffectName());

        Assert.assertEquals(specialEffectName, weaponCard.getSpecialEffectName());

    }

    @Test
    public void getSpecialAmmoCost() {
    }

    @Test
    public void getSpecialEffect() {
    }

    @Test
    public void getNotes() {
    }

    @Test
    public void setCardName() {
        String name = "Card";

        weaponCard.setCardName(name);

        Assert.assertEquals("Card", weaponCard.getCardName());
    }
}