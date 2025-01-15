/*
Write a program that accepts radius of a circle and displays area of the circle. Overload the
constructor to accept radius as input and another circle object as input. Then show the effect of
shallow vs deep copy of objects. Declare a constant pi equals to 3.14 using OOP concept.
*/

class Circle {
    final static double PI = 3.14159;
    double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    Circle(Circle other) {
        this.radius = other.radius;
    }

    void setRadius(double radius) {
        this.radius = radius;
    }

    void printArea() {
        System.out.printf("Area of circle = %.2f\n", PI*radius*radius);
    }
}


class P2 {
    public static void main(String[] args) {
        Circle c1 = new Circle(10);
        Circle c2 = new Circle(c1);
        Circle c3 = c1;

        c1.setRadius(100);

        c1.printArea();
        c2.printArea();
        c3.printArea();
    }
}