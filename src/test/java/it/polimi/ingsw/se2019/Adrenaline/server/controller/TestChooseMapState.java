package it.polimi.ingsw.se2019.Adrenaline.server.controller;

import it.polimi.ingsw.se2019.Adrenaline.server.model.map.MapA;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.MapB;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.MapC;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.MapD;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;


public class TestChooseMapState {

    private HashMap<Integer, Integer> selectedMaps = new HashMap<>();
    private it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map selectedMap;
    private int selectedMapInt;

    @Test
    public void TestSelectedMaps(){
        int selectMap1 = 1;

        if (selectedMaps.containsKey(selectMap1)) {
            System.out.println("put + 1");
            selectedMaps.put(selectMap1, selectedMaps.get(selectMap1) + 1);
        } else {
            System.out.println("put");
            selectedMaps.put(selectMap1, 1);
        }

        int selectMap2 = 1;

        if (selectedMaps.containsKey(selectMap2)) {
            System.out.println("put + 1");
            selectedMaps.put(selectMap2, selectedMaps.get(selectMap2) + 1);
        } else {
            System.out.println("put");
            selectedMaps.put(selectMap2, 1);
        }

        int selectMap3 = 2;

        if (selectedMaps.containsKey(selectMap3)) {
            System.out.println("put + 1");
            selectedMaps.put(selectMap3, selectedMaps.get(selectMap3) + 1);
        } else {
            System.out.println("put");
            selectedMaps.put(selectMap3, 1);
        }

        int num = 0;
        for(int m : selectedMaps.keySet()){
            num += selectedMaps.get(m);
        }
        System.out.println(num);

        Integer maxCount = Collections.max(selectedMaps.values());
        for (Integer i : selectedMaps.keySet()) {
            if (selectedMaps.get(i) == maxCount) {
                selectedMapInt = i;
                System.out.println(i);
                switch (i) {
                    case 1:
                        selectedMap = new MapA();
                        break;
                    case 2:
                        selectedMap = new MapB();
                        break;
                    case 3:
                        selectedMap = new MapC();
                        break;
                    case 4:
                        selectedMap = new MapD();
                        break;
                    default:
                        selectedMap = new MapB();
                }
            }
        }


    }

}
