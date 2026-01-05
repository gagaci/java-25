package org.example;

public class JEP513 {
    static void main() {

    }
}


class Employee extends Person {

    private String officeID;

    public Employee(Integer age, String officeID) {
        super(age); // couldn't validate age here ❌

        if (age < 18)
            throw new IllegalArgumentException("Invalid age");
        this.officeID = officeID;
    }

    public Employee(String name, Integer age, String officeID) {
      if(age < 18)
          throw new IllegalArgumentException("Invalid age");

        // ✅ Initialize fields before calling super!
        this.officeID = officeID;

        super(age);
    }

    public String getOfficeID() {
        return officeID;
    }

    public void setOfficeID(String officeID) {
        this.officeID = officeID;
    }
}

class Person {
    private String name;
    private Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Person(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

