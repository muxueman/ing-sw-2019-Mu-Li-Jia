package it.polimi.ingsw.se2019.Adrenaline.client.view.CLI;

import it.polimi.ingsw.se2019.Adrenaline.client.controller.ClientController;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Color;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.BoardStatus;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.PlayerStatus;

import java.util.ArrayList;
import java.util.List;

public class ShowTotal {

    private BoardStatus boardStatus;
    private String messageMap;
    private ClientController client;

    public ShowTotal(){};
    public ShowTotal(BoardStatus boardStatus,ClientController client){
        this.boardStatus = boardStatus;
        this.client = client;
        printMap(boardStatus.getMapID());
        new ShowBoardWeapons(boardStatus);
        printPlayers(boardStatus.getAllPlayers(),boardStatus);
        printDamage(boardStatus.getAllPlayers());
        new ShowWeaponCard(boardStatus.getPlayer(client.getPlayerID()).getWeaponsOwned());
        new ShowPowerupCard(boardStatus.getPlayer(client.getPlayerID()).getPowerupsOwned());
    }

    public void printMap(int mapID){
        switch (mapID) {
            case 1:
                messageMap = ("A:\n" + "\u001b[1;46m 1 " + "\u001b[1;46m 2 " + "\u001b[1;46m 3 " + "\u001b[1;48m   "
                        + "\u001b[1;48m\n" + "\u001b[1;41m 5 " + "\u001b[1;41m 6 " + "\u001b[1;41m 7 " + "\u001b[1;43m 8 " + "\u001b[1;48m\n" + "\u001b[1;48m   "
                        + "\u001b[1;47m 10" + "\u001b[1;47m 11" + "\u001b[1;43m 12" + "\u001b[1;48m\n");
                break;
            case 2:
                messageMap = ("B:\n" + "\u001b[1;41m 1 " + "\u001b[1;46m 2 " + "\u001b[1;46m 3 " + "\u001b[1;48m   "
                        + "\u001b[1;48m\n" + "\u001b[1;41m 5 " + "\u001b[1;45m 6 " + "\u001b[1;45m 7 " + "\u001b[1;43m 8 " + "\u001b[1;48m\n" + "\u001b[1;47m 9 "
                        + "\u001b[1;47m 10" + "\u001b[1;47m 11" + "\u001b[1;43m 12" + "\u001b[1;48m\n");
                break;
            case 3:
                messageMap = "C:\n" + "\u001b[1;46m 1 " + "\u001b[1;46m 2 " + "\u001b[1;46m 3 " + "\u001b[1;42m 4 "
                        + "\u001b[1;48m\n" + "\u001b[1;41m 5 " + "\u001b[1;41m 6 " + "\u001b[1;43m 7 " + "\u001b[1;43m 8 " + "\u001b[1;48m\n" + "\u001b[1;48m   "
                        + "\u001b[1;47m 10" + "\u001b[1;43m 11" + "\u001b[1;43m 12" + "\u001b[1;48m\n";
                break;
            case 4:
                messageMap = "D:\n" + "\u001b[1;41m 1 " + "\u001b[1;46m 2 " + "\u001b[1;46m 3 " + "\u001b[1;42m 4 "
                    + "\u001b[1;48m\n" + "\u001b[1;41m 5 " + "\u001b[1;45m 6 " + "\u001b[1;43m 7 " + "\u001b[1;43m 8 " + "\u001b[1;48m\n" + "\u001b[1;47m 9 "
                    + "\u001b[1;47m 10" + "\u001b[1;43m 11" + "\u001b[1;43m 12"+ "\u001b[1;48m\n";
                break;
            default:
                break;
        }
        System.out.println(messageMap);
    }

    public void printPlayers(List<PlayerStatus> players, BoardStatus boardStatus){
        System.out.println("All players in this match:");
        int length = players.size();
        ArrayList<PlayerStatus> pA = new ArrayList<>();
        for (PlayerStatus p: players){
            pA.add(p);
        }
        CommandLineTable st = new CommandLineTable("\u001b[1;31m","\u001b[1;35m");
        st.setShowVerticalLines(true);
        switch (length){
            case 2:
                st.setHeaders("player name",pA.get(0).getUsername(),pA.get(1).getUsername());
                st.addRow("color", pA.get(0).getPlayerColor().toString(),pA.get(1).getPlayerColor().toString());
                st.addRow("current score",String.valueOf(pA.get(0).getMyScore()),String.valueOf(pA.get(1).getMyScore()));
                st.addRow("action mode", String.valueOf(pA.get(0).getActionMode()),String.valueOf(pA.get(1).getActionMode()));
                st.addRow("killed times", String.valueOf(pA.get(0).getNumKilled()),String.valueOf(pA.get(1).getNumKilled()));
                //st.addRow("cell location", String.valueOf(boardStatus.getPositions().get(pA.get(0).getPlayerID())),String.valueOf(boardStatus.getPositions().get(pA.get(1).getPlayerID())));
                break;
            case 3:break;
            case 4:break;
            case 5:break;
            default:break;
        }
        st.print();
    }

    public void printDamage(List<PlayerStatus> players) {
        for (PlayerStatus p : players) {
            System.out.println(p.getUsername() + ":   "+ messageD(p));
        }
    }

    protected String messageD(PlayerStatus p){
        ArrayList<Color> damageColorOnTrack = p.getDamageColorOnTrack();
        ArrayList<Color> damageMarkOnTrack = p.getMarkColorOnTrack();
        String messageD = "damage:   ";
        for (Color c: damageColorOnTrack) {
            switch (c) {
                case YELLOW:
                    messageD += "\u001b[1;43m   ";
                    break;
                case GREEN:
                    messageD += "\u001b[1;42m   ";
                    break;
                case BLUE:
                    messageD += "\u001b[1;46m   ";
                    break;
                case WHITE:
                    messageD += "\u001b[1;47m   ";
                    break;
                case PINK:
                    messageD += "\u001b[1;45m   ";
                    break;
                    default:break;
            }
        }
        messageD += "\u001b[1;48m   marked:  ";
        for (Color c: damageMarkOnTrack) {
            switch (c) {
                case YELLOW:
                    messageD += "\u001b[1;43m   ";
                    break;
                case GREEN:
                    messageD += "\u001b[1;42m   ";
                    break;
                case BLUE:
                    messageD += "\u001b[1;46m   ";
                    break;
                case WHITE:
                    messageD += "\u001b[1;47m   ";
                    break;
                case PINK:
                    messageD += "\u001b[1;45m   ";
                    break;
                    default:break;
            }
        }
        return messageD += "\u001b[1;48m   ";
    }
}
