package ru.aston.myArrayList;

import ru.aston.mySort.MergeSort;

import java.util.*;

public class MyArrayList<E> implements CustomArrayList<E> {

    private int size;

    private Object[] elementOfList;

    private static final int DEFAULT_CAPACITY = 10;

    private static final Object[] EMPTY_ARRAY = {};

    private MergeSort<E> mergeSort = new MergeSort<>();


    public MyArrayList() {
        this.size = 0;
        this.elementOfList = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int size) {
        if (size > 0) {
            this.size = size;
            this.elementOfList = new Object[size];
        } else if (size == 0) {
            this.size = 0;
            this.elementOfList = EMPTY_ARRAY;
        } else
            throw new IllegalArgumentException("Ошибка в инициализации обьекта. \n "
                    + size + " - размер массива невозможен");
    }

    @Override
    public void add(int index, E element) {
        indexOutOfArray(index);
        if (elementOfList[index] == null) {
            elementOfList[index] = element;
            size++;
        } else
            elementOfList[index] = element;
    }

    @Override
    public void add(E element) {
        if (elementOfList.length == size) {
            elementOfList = Arrays.copyOf(elementOfList, size() + DEFAULT_CAPACITY);
        }
        elementOfList[size] = element;
        size++;
    }

    @Override
    public void addAll(Collection<? extends E> c) {
//        size = c.size();
//        elementOfList = Arrays.copyOf(c.toArray(), size);

        ensureCapacity(size + c.size());
        System.arraycopy(c.toArray(), 0, elementOfList, size, c.size());

        size += c.size();
    }

    @Override
    public void clear() {
        elementOfList = EMPTY_ARRAY;
        size = 0;
    }

    @Override
    public void sort(Comparator<? super E> c) {
        mergeSort.sort((E[]) elementOfList, 0, size() - 1, c);
    }

    @Override
    public E get(int index) {
        indexOutOfArray(index);
        return (E) elementOfList[index];

    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E remove(int index) {
        indexOutOfArray(index);
        return (E) removeFromList(index);
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i <= size - 1; i++) {
            if (elementOfList[i].equals(o)) {
                removeFromList(i);
                return true;
            }
        }
        return false;
    }


    @Override
    public String toString() {
        return Arrays.toString(Arrays.stream(elementOfList).filter(Objects::nonNull).toArray());
//        System.out.print("[ " + elementOfList.length + " ] - ");
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    private boolean indexOutOfArray(int index) {
        if (index <= size - 1 || index >= 0)
            return true;
        else
            throw new IllegalArgumentException("Выход за рамки листа. \n"
                    + index + " - за рамками листа");
    }

    public int size() {
        return size;
    }

    private Object removeFromList(int index) {
        elementOfList[index] = null;
        for (int i = index; i <= size; i++) {
            if (i == size - 1) {
                elementOfList[i] = null;
                break;
            } else
                elementOfList[i] = elementOfList[i + 1];
        }
        elementOfList = Arrays.copyOf(elementOfList, size() - 1);
        size--;
        return elementOfList;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elementOfList.length) {
            int newCapacity = Math.max(elementOfList.length * 2, minCapacity);
            elementOfList = Arrays.copyOf(elementOfList, newCapacity);
        }
    }
}
