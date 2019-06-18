package it.polimi.ingsw.se2019.Adrenaline.server.model.map;

import it.polimi.ingsw.se2019.Adrenaline.server.model.Color;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestCell {

    Cell cella = new CommonCell(1);
    Cell cellb = new CommonCell(4);
    Cell cellc = new CommonCell(5);
    Cell celld = new CommonCell(6);
    Player playerA = new Player("jia");

    @Test
    public void testGenerationCell(){
        Cell wallet = new GenerationCell(2);
        wallet.setAdjacentCells(cella, cellb, cellc, celld);
        Assert.assertEquals(wallet.getNextCell(0),cella);
        Assert.assertEquals(wallet.getNextCell(1),cellb);
        Assert.assertEquals(wallet.getNextCell(2),cellc);
        Assert.assertEquals(wallet.getNextCell(3),celld);
        wallet.setCellColor(Color.PINK);
        Assert.assertEquals(wallet.getCellColor(),Color.PINK);
        Assert.assertEquals(wallet.getCellID(),2);
        wallet.addPlayer(playerA);
        wallet.removePlayer(playerA);
    }

    @Test
    public void testCommonCell(){
        Cell wallet = new CommonCell(3);
        wallet.setAdjacentCells(cella, cellb, cellc, celld);
        Assert.assertEquals(wallet.getNextCell("up"),cella);
        Assert.assertEquals(wallet.getNextCell("right"),cellb);
        Assert.assertEquals(wallet.getNextCell("down"),cellc);
        Assert.assertEquals(wallet.getNextCell("left"),celld);
        wallet.setCellColor(Color.RED);
        Assert.assertEquals(wallet.getCellColor(),Color.RED);

    }


}

