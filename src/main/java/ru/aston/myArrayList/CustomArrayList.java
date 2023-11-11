package ru.aston.myArrayList;

import java.util.*;

public interface CustomArrayList<E> {

    void add(int index, E element);

    void add(E element);

    void addAll(Collection<? extends E> c);

    void clear();

    void sort(Comparator<? super E> c);

    E get(int index);

    boolean isEmpty();

    E remove(int index);

    boolean remove(Object o);


}
