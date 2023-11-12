package ru.aston.mySort;

import java.util.Comparator;

public class MergeSort<E> {

    public void sort(E[] array, int left, int right, Comparator<? super E> comparator) {
        if (left < right) {
            int middle = (left + right) / 2;
            sort(array, left, middle, comparator);
            sort(array, (middle + 1), right, comparator);
            merge(array, left, middle, right, comparator);
        }
    }

    private void merge(E[] array, int left, int middle, int right,Comparator<? super E> comparator) {
        int sizeFirst = middle - left + 1;
        int sizeSecond = right - middle;

        Object[] leftArray = new Object[sizeFirst];
        Object[] rightArray = new Object[sizeSecond];

        for (int i = 0; i < sizeFirst; i++) {
            leftArray[i] = array[left + i];
        }
        for (int j = 0; j < sizeSecond; j++) {
            rightArray[j] = array[middle + 1 + j];
        }

        int i = 0, j = 0, k = left;
        while (i < sizeFirst && j < sizeSecond) {
            if (comparator.compare((E) leftArray[i], (E) rightArray[j]) <= 0) {
                array[k] = (E) leftArray[i];
                i++;
            } else {
                array[k] = (E) rightArray[j];
                j++;
            }
            k++;
        }

        while (i < sizeFirst) {
            array[k] = (E) leftArray[i];
            i++;
            k++;
        }

        while (j < sizeSecond) {
            array[k] = (E) rightArray[j];
            j++;
            k++;
        }

    }
}

