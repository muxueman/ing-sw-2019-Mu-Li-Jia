package it.polimi.ingsw.se2019.Adrenaline.utils;

/**
 *the abstract class super Observable
 *@author Xueman Mu
 */

import java.util.ArrayList;
import java.util.List;

public abstract class Observable<T>{


    private final Object lock = new Object();
    private List<Observer<T>> observers = new ArrayList<>();

    public void register(Observer<T> observer){
        synchronized (lock) {
            observers.add(observer);
        }
    }

    public void deregister(Observer<T> observer){
        synchronized (lock) {
            observers.remove(observer);
        }
    }

    protected void notify(T message){
        synchronized (lock) {
            for(Observer<T> observer : observers){
                observer.update(message);
            }
        }
    }


}