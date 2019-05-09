package it.polimi.ingsw.se2019.Adrenaline.server;

import it.polimi.ingsw.se2019.Adrenaline.network.massage.MVEvent;
import it.polimi.ingsw.se2019.Adrenaline.network.massage.VCEvent;
import it.polimi.ingsw.se2019.Adrenaline.utils.Observable;
import it.polimi.ingsw.se2019.Adrenaline.utils.Observer;

/**
 *  It behaves like a real View with respect to Model and controller:
 *  It is an observer with respect to the Model
 *  It is an observable towards the controller
 */
public abstract class VirtualView extends Observable<VCEvent> implements Observer<MVEvent> {

}
