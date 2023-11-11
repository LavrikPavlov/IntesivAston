package ru.aston;


import ru.aston.myArrayList.MyArrayList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class App
{
    public static void main( String[] args ) {
        System.out.println("Hello Aston");
        MyArrayList<Integer> myArrayList = new MyArrayList<>();
        System.out.println(myArrayList.size() + " | " + myArrayList);

        for(int i = 0; i < 20; i++){
            myArrayList.add(i);
        }
        System.out.println(myArrayList.size() + " | " + myArrayList);

        myArrayList.clear();

        System.out.println(myArrayList.size() + " | " + myArrayList);
        if(!myArrayList.isEmpty())
            System.out.println(myArrayList.get(1));
        else {
            System.out.println(myArrayList.isEmpty());
            for(int i = 0; i < 5; i++){
                myArrayList.add(i);
            }
            System.out.println(myArrayList.get(0));
        }
        System.out.println(myArrayList.size() + " | " + myArrayList);
        myArrayList.remove(0);
        System.out.println(myArrayList.size() + " | " + myArrayList);
        System.out.println();


        MyArrayList<String> arrayList = new MyArrayList<>();
        arrayList.clear();

        arrayList.add("Привет");
        arrayList.add("как");
        arrayList.add("дела");
        arrayList.add("мой");
        arrayList.add("друг");


        System.out.println(arrayList);

        arrayList.remove("Привет");

        System.out.println(arrayList);




    }
}
