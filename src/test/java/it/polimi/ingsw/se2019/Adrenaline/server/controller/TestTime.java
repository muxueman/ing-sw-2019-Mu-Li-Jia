package it.polimi.ingsw.se2019.Adrenaline.server.controller;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Test;

import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Logger;

public class TestTime {

    String matchID = UUID.randomUUID().toString();
    MatchController match = new MatchController(matchID);
    @Test
    public void testTimer(){
        match.startTimer() ;
    }


}
