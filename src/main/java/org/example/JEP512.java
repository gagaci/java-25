package org.example;

public class JEP512 {

    /*
    * Main method used to look like this
    *
    * Before
    *
    *  public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
    *
    * After
    *
    * void main() {
        System.out.println("Hello, World!");
    }
    *
    * */

    String greeting() {
        return "Hello, World!";
    }

    // you can do such shortcuts as well
    void main() {

        // use IO's methods, they look shorter, thus give clean code impression
        String name = IO.readln("Please enter your name: ");
        IO.print("Pleased to meet you, ");
        IO.println(name);

        // no need to write System.out, use IO
        IO.println(greeting());
    }


}
