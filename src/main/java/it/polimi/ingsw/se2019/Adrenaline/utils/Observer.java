package it.polimi.ingsw.se2019.Adrenaline.utils;

/**
 *the interface Observer
 *@author Xueman Mu
 */

public interface Observer<T> {
    void update(T message);
}