/*
 * For a programme (such as, BCSE), each Instructor has name and phone number. Each textbook
has a title, author name and publisher. Each course (that is, subject) has a course name, instructor and
text book.
• One can set the data for a textbook and view the same.
• One can view instructor information and set the information.
• One can set the course data and view the same.
Design and implement the classes.
 */


class TextBook {
    public String _title;
    public String _author;
    public String _publisher;

    public TextBook(String title, String author, String publisher) {
        _title = title;
        _author = author;
        _publisher = publisher;
    }

    public void get() {
        System.out.println("Title: " + _title);
        System.out.println("Author: " + _author);
        System.out.println("Publisher: " + _publisher);
    }

    public void set(String title, String author, String publisher) {
        _title = title;
        _author = author;
        _publisher = publisher;
    }
}


class Instructor {
    public String _name;
    public String _phone;

    public Instructor(String name, String phone) {
        _name = name;
        _phone = phone;
    }

    public void get() {
        System.out.println("Name: " + _name);
        System.out.println("Phone: " + _phone);
    }

    public void set(String name, String phone) {
        _name = name;
        _phone = phone;
    }
}


class Course {
    public String _name;
    public Instructor _instructor;
    public TextBook _textbook;

    public Course(String name, Instructor instructor, TextBook textbook) {
        _name = name;
        _instructor = instructor;
        _textbook = textbook;
    }

    public void get() {
        System.out.println("Course Name: " + _name);
        System.out.println("Instructor: ");
        _instructor.get();
        System.out.println("Textbook: ");
        _textbook.get();
    }

    public void set(String name, Instructor instructor, TextBook textbook) {
        _name = name;
        _instructor = instructor;
        _textbook = textbook;
    }
}


public class P8 {
    public static void main(String[] args) {
        
    }
}
