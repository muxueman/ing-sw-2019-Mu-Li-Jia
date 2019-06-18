package it.polimi.ingsw.se2019.Adrenaline.utils.immutables;
import java.io.Serializable;
import org.fusesource.jansi.Ansi;

public interface Status extends Serializable {
    String toString();
    Ansi toAnsi();
}
