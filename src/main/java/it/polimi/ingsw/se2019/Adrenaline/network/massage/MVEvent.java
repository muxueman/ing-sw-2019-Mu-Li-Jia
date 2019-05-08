package it.polimi.ingsw.se2019.Adrenaline.network.massage;

import it.polimi.ingsw.se2019.Adrenaline.client.view.View;

import java.io.Serializable;

public interface MVEvent extends Serializable{

    public void accept(View view);

}
