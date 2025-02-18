package Assignment_2;

/*
 * Print the sum of all even numbers stored in a circular linked list. Represent Node as a static inner
class. Collection classes are NOT allowed.
 */

import java.util.Scanner;

class CircularLinkedList {
    static class Node {
        int data;
        Node next;
        
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node last;

    public CircularLinkedList() {
         this.last = null;
    }

    public void insert(int data) {
        Node newNode = new Node(data);
        if (last == null) {
            last = newNode;
            last.next = last;  // Circular link to itself
        } else {
            newNode.next = last.next;
            last.next = newNode;
            last = newNode;
        }
    }

    public int sumEvenNumbers() {
        if (last == null) return 0;

        int sum = 0;
        Node temp = last.next;
        do {
            if (temp.data % 2 == 0) {
                sum += temp.data;
            }
            temp = temp.next;
        } while (temp != last.next);

        return sum;
    }

    public void display() {
        if (last == null) {
            System.out.println("List is empty.");
            return;
        }

        Node temp = last.next;
        do {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        } while (temp != last.next);
        System.out.println("(back to head)");
    }

    public static void main(String[] args) {
        CircularLinkedList list = new CircularLinkedList();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of elements:");
        int n = scanner.nextInt();

        System.out.println("Enter " + n + " elements:");
        for (int i = 0; i < n; i++) {
            list.insert(scanner.nextInt());
        }

        list.display();

        int evenSum = list.sumEvenNumbers();
        System.out.println("Sum of even numbers in the circular linked list: " + evenSum);
    }
}
 