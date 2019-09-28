package intuit.Intuit2020;

import java.util.*;

public class fdsa {
        // If you need extra classes, you can define them privately here within class Solution

        // Prints to standard output.
        static void findStartAndEndLocations(String[][] pairs) {
            // Your code here.
            TreeMap<String, Deque<String>> fatherSonsPair = new TreeMap<>();
            Set<String> sons = new HashSet<>();

            //fill the map and set
            for (String[] pair : pairs) {
                String father = pair[0];
                String son = pair[1];
                if (!fatherSonsPair.containsKey(father)) {
                    fatherSonsPair.put(father, new LinkedList<>());
                }
                fatherSonsPair.get(father).add(son);
                sons.add(son);
            }

            //print
            for (String key : fatherSonsPair.keySet()) {
                if (!sons.contains(key)) {
                    List<String> destinations = new ArrayList<>();
                    Deque<String> queue = fatherSonsPair.get(key);

                    while (!queue.isEmpty()) {
                        String value = queue.pollFirst();
                        if (!fatherSonsPair.containsKey(value)) {
                            destinations.add(value);
                        } else {
                            queue.addAll(fatherSonsPair.get(value));
                        }
                    }

                    //sort destinations
                    Collections.sort(destinations);

                    //print
                    System.out.print(key + ":");
                    for (String value : destinations) {
                        System.out.print(" " + value);
                    }
                    System.out.println();
                }
            }
        }

}
