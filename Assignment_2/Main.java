package Assignment_2;

import java.io.*;
import java.util.*;

class Student implements Serializable {
    int roll;
    String name;
    int marks;

    public Student(int roll, String name, int marks) {
        this.roll = roll;
        this.name = name;
        this.marks = marks;
    }

    public String toString() {
        return "Roll: " + roll + ", Name: " + name + ", Marks: " + marks;
    }
}

public class Main {
    public static void main(String[] args) {
        List<String> arr = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File("C:\\Users\\ABHIRUP\\Desktop\\code_playground\\Sem4\\Adv_OOPs\\Assignment_2", "input.txt"));

            while (scanner.hasNext()) {
                arr.add(scanner.next());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Map<String, Integer> map = new TreeMap<>();

        for (int i = 0; i < arr.size(); i++) {
            String word = arr.get(i);
            char[] wordArr = word.toCharArray();

            Arrays.sort(wordArr);
            String sortedWord = new String(wordArr);

            if (map.containsKey(sortedWord)) {
                map.put(sortedWord, map.get(sortedWord) + 1);
            } else {
                map.put(sortedWord, 1);
            }
        }

        for (String key: map.keySet()) {
            System.out.println(key + ": " + map.get(key));
        }
    }
}