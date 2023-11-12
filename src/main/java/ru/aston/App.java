package ru.aston;

import ru.aston.myArrayList.MyArrayList;

import java.util.Comparator;
import java.util.Random;


public class App
{
    public static void main( String[] args ) {
        System.out.println("Hello Aston");

        Random random = new Random();
        MyArrayList<Integer> list = new MyArrayList<>();


        for (int i = 0; i < 5; i++){
            list.add(random.nextInt(100));
        }

        System.out.println(list);
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1<=o2) {
                    if (o1.equals(o2))
                        return 0;
                    return -1;
                }
                else
                    return 1;
            }
        });

        System.out.println(list);

    }
}
