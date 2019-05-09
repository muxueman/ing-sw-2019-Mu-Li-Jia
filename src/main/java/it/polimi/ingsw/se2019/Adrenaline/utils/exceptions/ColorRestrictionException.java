package it.polimi.ingsw.se2019.Adrenaline.utils.exceptions;

/**.
 * @author Xueman Mu
 */

public class ColorRestrictionException extends Exception {

    public ColorRestrictionException() {
        super("this die doesn't have the same color of the restriction on the pattern!");
    }
}
