package org.tak.commons;

/**
 * User: Tommy
 * 6/2/13
 */
public interface Filter<E> {
    public boolean accept(E e);
}
