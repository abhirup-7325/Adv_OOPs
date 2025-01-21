/*
 * Create a program for ordering Pizza. The user should mention the size of the Pizza and the
toppings he/she wants. A user may ask for any toppings. Implement this using (i) variable arguments
concept and (ii) command line arguments.
 */

import java.util.*;


class PizzaOrdering {
    public void orderVA(int size, String... toppings) {
        System.out.println("Pizza ordered with size " + size + " and toppings: ");
        for (String topping : toppings) {
            System.out.println(topping);
        }
    }

    public void orderCLA(int size, String[] toppings) {
        System.out.println("Pizza ordered with size " + size + " and toppings: ");
        for (String topping : toppings) {
            System.out.println(topping);
        }
    } 
}


public class P6 {
    public static void main(String[] args) {
        PizzaOrdering pizzaOrdering = new PizzaOrdering();
        pizzaOrdering.orderVA(12, "Cheese", "Tomato", "Onion");
        pizzaOrdering.orderCLA(12, args);
    }
}
