package it.polimi.ingsw.se2019.Adrenaline.utils;

public interface Observer<T> {
    void update(T message);
}