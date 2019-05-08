package it.polimi.ingsw.se2019.Adrenaline.utils;

public interface Observer<T> {
    public void update(T message);
}