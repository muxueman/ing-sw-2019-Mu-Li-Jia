package it.polimi.ingsw.se2019.Adrenaline.network.massage;

import it.polimi.ingsw.se2019.Adrenaline.server.controller.Controller;

import java.io.Serializable;

public interface VCEvent extends Serializable{

    public void accept(Controller controller);
}
