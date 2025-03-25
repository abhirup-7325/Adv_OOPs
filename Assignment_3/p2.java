package Assignment_3;

import java.io.File;
import java.util.Comparator;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

class Compare implements Comparator<String> {
    @Override
    public int compare(String s1, String s2) {
        return s2.compareTo(s1);
    }
}

class p2 {
    public static void main(String[] args) {
        SortedMap<String, Integer> map = new TreeMap<>(new Compare());

        try {
            Scanner scanner = new Scanner(new File("C:\\Users\\ABHIRUP\\Desktop\\code_playground\\Sem4\\Adv_OOPs\\Assignment_3", "p2_file.txt"));

            while (scanner.hasNext()) {
                String word = scanner.next();
                word = word.toLowerCase();

                if (map.containsKey(word)) {
                    map.put(word, map.get(word) + 1);
                } else {
                    map.put(word, 1);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        for (String key : map.keySet()) {
            System.out.println(key + ": " + map.get(key));
        }
    }
}
