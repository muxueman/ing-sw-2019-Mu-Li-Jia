package it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


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
        System.out.println("cardName:" + weaponCard.getCardName());
        Assert.assertEquals("VORTEX CANNON", weaponCard.getCardName());

    }


    @Test
    public void testBasicammoCost() {

        int[] basicammoCost;
        basicammoCost = new int[]{1,2,0,1};
        WeaponCard weaponCard = new WeaponCard();
        weaponCard.setBasicammoCost(basicammoCost);
        System.out.println(basicammoCost.toString());

        //for the third ammocost 1 for red , 2 for blue , 3 for yellow , 0 for null
        //for specialEffect 1 for the card has 1 specialeffect , 2 for the card has 2 specialeffects , 0 for null

        Assert.assertEquals(basicammoCost, weaponCard.getBasicammoCost());
    }

    @Test
    public void testDamageVision() {

        weaponCard.setDamageVision(12);
        System.out.println("damageVision:" + weaponCard.getDamageVision());
        Assert.assertEquals(12, weaponCard.getDamageVision());
    }

    @Test
    public void testDamageDeal() {

        int[] damageDeal;
        damageDeal = new int[] {0,2,1};
        weaponCard.setDamageDeal(damageDeal);
        System.out.println(damageDeal.toString());
        Assert.assertEquals(damageDeal, weaponCard.getDamageDeal());
    }

    @Test
    public void testBasicEffect() {

        weaponCard.setBasicEffect("Choose a cell you can see but not your cell and choose a target on the cell or move away from it.move it onto the cell and give it 2 damage");
        System.out.println("basicEffect:" + weaponCard.getBasicEffect());
        Assert.assertEquals("Choose a cell you can see but not your cell and choose a target on the cell or move away from it.move it onto the cell and give it 2 damage",weaponCard.getBasicEffect());

    }

    @Test
    public void testSpecialEffectName() {

        String[] specialEffectName;
        specialEffectName = new String[]{"with black hole"};
        weaponCard.setSpecialEffectName(specialEffectName);
        System.out.println(specialEffectName.toString());
        Assert.assertEquals(specialEffectName, weaponCard.getSpecialEffectName());

    }

    @Test
    public void testSpecialAmmoCost() {

        int[] specialAmmoCost;
        specialAmmoCost = new int[]{1};
        weaponCard.setSpecialAmmoCost(specialAmmoCost);
        System.out.println(specialAmmoCost.toString());
        Assert.assertEquals(specialAmmoCost,weaponCard.getSpecialAmmoCost());
    }

    @Test
    public void testSpecialEffect() {

        String[] specialEffect;
        specialEffect = new String[]{"Choose up to 2 other targets on the cell or 1 move away from it.Move them onto the cell and give them each 1 damage"};
        weaponCard.setSpecialEffect(specialEffect);
        System.out.println(specialEffect.toString());
        Assert.assertEquals(specialEffect,weaponCard.getSpecialEffect());

    }

    @Test
    public void testNotes() {

        weaponCard.setNotes("The 3 target must be different,but some might start on the same cell.It is legal to choose targets on your cell or even on cells you can't see.They all end up on that cell");
        System.out.println("notes:" +weaponCard.getNotes());
        Assert.assertEquals("The 3 target must be different,but some might start on the same cell.It is legal to choose targets on your cell or even on cells you can't see.They all end up on that cell",weaponCard.getNotes());

    }


    @Test
    public void readWeaponCardTest() {

        WeaponCardDeck weaponCardDeck = new WeaponCardDeck();
        System.out.println(weaponCardDeck.toString());

    }
}