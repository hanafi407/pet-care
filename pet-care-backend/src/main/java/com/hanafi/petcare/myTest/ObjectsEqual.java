package com.hanafi.petcare.myTest;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
//@Data
class Person{
    private String name;
    private int age;
    private String address;

}

class Job extends Person{
    private String name;

    public Job(String name, int age, String address) {
        super(name, age, address);
    }
}

public class ObjectsEqual {
    public static void main(String[] args) {
        Person person = new Person("Hanafi", 32, "Pasangan");
        Person person1 = new Person("Hanafi", 32, "Pasangan");

        Set<Person> persons = new HashSet<>();
        persons.add(person);
        persons.add(person1);
        System.out.println(persons);
    }
}
