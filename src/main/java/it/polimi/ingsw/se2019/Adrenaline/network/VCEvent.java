package it.polimi.ingsw.se2019.Adrenaline.network;

import it.polimi.ingsw.se2019.Adrenaline.server.controller.Controller;

import java.io.Serializable;

public interface VCEvent extends Serializable{

    void accept(Controller controller);
}
