package util;

import java.util.Collection;
import java.util.Iterator;

public class Cycle<T> implements Iterator<T> {
    Collection<T> object;
    Iterator<T> cycle;
    public Cycle(Collection<T> iterable){
        object = iterable;
        cycle = object.iterator();
    }
    @Override
    public boolean hasNext() {
        if(!cycle.hasNext()){
            cycle = object.iterator();
            return false;
        }
        return true;
    }

    @Override
    public T next() {
        this.hasNext();
        return cycle.next();
    }
}