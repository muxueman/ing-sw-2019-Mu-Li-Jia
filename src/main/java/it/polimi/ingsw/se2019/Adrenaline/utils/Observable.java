package it.polimi.ingsw.se2019.Adrenaline.utils;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable<T>{

    private List<Observer<T>> observers;
    private final Object lock = new Object();

    //constructor
    public Observable(){
        this.observers = new ArrayList<>();
    }

    public void addObserver(Observer<T> observer){
        synchronized (lock) {
            observers.add(observer);
        }
    }

    public void removeObserver(Observer<T> observer){
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