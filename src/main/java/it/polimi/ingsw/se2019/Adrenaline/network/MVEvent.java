package it.polimi.ingsw.se2019.Adrenaline.network;

import it.polimi.ingsw.se2019.Adrenaline.client.view.View;

import java.io.Serializable;

public interface MVEvent extends Serializable{

    void accept(View view);

}