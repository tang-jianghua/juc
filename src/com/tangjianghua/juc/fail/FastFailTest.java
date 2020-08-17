package com.tangjianghua.juc.fail;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @auth tangjianghua
 * @date 2020/8/2
 */
public class FastFailTest {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        //fori 测试
       /* for (int i = 0; i < list.size(); i++) {
            if(list.get(i).equals("a")){
                list.remove(i);
            }
            System.out.println(list.get(i));
        }*/
        for (int i = 0; i < list.size(); i++) {
            Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()){
                String next = iterator.next();
                if(next.equals("a")){
                    iterator.remove();
                }
            }
            //System.out.println(list.get(i));
        }
      /*  for (int i = 0; i < list.size(); i++) {
            if(list.get(i).equals("a")){
                list.remove("a");
            }
        }*/
     /*   for (int i = 0; i < 3; i++) {
            if(list.get(i).equals("a")){
                list.remove(i);
                //list.remove("a");
            }
        }*/
      /*  Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            if(iterator.next().equals("a")){
                iterator.remove();
            }
        }*/
    /*    Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            if(next.equals("a")){
                list.remove(next);
            }
        }*/

   /*     list.forEach(s -> {
            System.out.println(s);
            if(s.equals("a")) {
                list.remove("a");
            }
        });*/
      /*  list.forEach(s -> {
            System.out.println(s);
            if(s.equals("a")) {
                list.remove("a");
            }
        });*/
    }
}
