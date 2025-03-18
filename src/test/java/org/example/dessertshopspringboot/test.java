package org.example.dessertshopspringboot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test {
    public static void main(String[] args) {
        Map<Integer, List<Integer>>map=new HashMap<>();
        for(int i=1;i<=10;i++)
        map.computeIfAbsent(5, k -> new ArrayList<>()).add(3);
        System.out.println(map.get(5));
    }
}
