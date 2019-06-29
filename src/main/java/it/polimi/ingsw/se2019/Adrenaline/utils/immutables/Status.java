package it.polimi.ingsw.se2019.Adrenaline.utils.immutables;

import java.io.Serializable;
import org.fusesource.jansi.Ansi;

//The Status interface groups every different type of immutable status used by the client-side model.
public interface Status extends Serializable {
    String toString();
    Ansi toAnsi();
}
