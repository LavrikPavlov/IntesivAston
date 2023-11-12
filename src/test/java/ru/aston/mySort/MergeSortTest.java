package ru.aston.mySort;

import org.junit.Assert;
import org.junit.Test;


import java.util.Comparator;

public class MergeSortTest {

    MergeSort<Integer> mergeSort = new MergeSort<>();

    @Test
    public void sort(){
        Integer[] array = {75, 39, 73, 14, 72};
        Integer[] arraySort = {14, 39, 72, 73, 75};
        mergeSort.sort(array, 0, array.length - 1, Comparator.naturalOrder());

        Assert.assertEquals(arraySort, array);
    }
}
