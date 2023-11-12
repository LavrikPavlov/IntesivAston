package ru.aston.myArrayListTest;

import org.junit.*;
import ru.aston.myArrayList.MyArrayList;

import java.util.List;


public class MyArrayListTest {

    private MyArrayList<String> stringArrayList;
    private MyArrayList<Integer> arrayList;


    @Before
    public void setUpValues(){
        arrayList = new MyArrayList<>();
        arrayList.addAll(List.of(10,20,30,40,50));

        stringArrayList = new MyArrayList<>();
        stringArrayList.addAll(List.of("Привет","как","дела","мой","друг"));
    }

    @Test
    public void getCheck() {
        Assert.assertEquals("друг", stringArrayList.get(4));
    }

    @Test
    public void sizeCheck() {
        Assert.assertEquals(5, arrayList.size());
    }

    @Test
    public void addObject() {
        arrayList.add(10);
        Assert.assertEquals(10, (int) arrayList.get(arrayList.size() - 1));

    }

    @Test
    public void addByIndex(){
        arrayList.add(0, 0);
        Assert.assertEquals((Integer) 0, arrayList.get(0));
    }

    @Test
    public void addAll(){
        arrayList.clear();
        arrayList.addAll(List.of(1,2,3,4,5));
        for (int i = 0; i <= arrayList.size() - 1; i++)
            Assert.assertEquals((Integer) (i + 1), arrayList.get(i));
    }

    @Test
    public void clear(){
        Assert.assertNotNull(arrayList);
        arrayList.clear();
        Assert.assertEquals(0, arrayList.size());
    }

    @Test
    public void removeByIndex(){
        stringArrayList.remove(0);
        Assert.assertEquals("как", stringArrayList.get(0));
    }

    @Test
    public void removeByObject(){
        stringArrayList.remove("друг");
        Assert.assertEquals("мой", stringArrayList.get(stringArrayList.size()-1));
    }
}
