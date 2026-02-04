package com.akanksha.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;

public class ListToJsonArray {
  static class Person {
    public String name;
    public int age;

    Person() {
    }

    Person(String n, int a) {
      name = n;
      age = a;
    }
  }

  public static void main(String[] args) throws Exception {
    List<Person> people = new ArrayList<>();
    people.add(new Person("Alice", 30));
    people.add(new Person("Bob", 22));
    ObjectMapper m = new ObjectMapper();
    System.out.println(m.writerWithDefaultPrettyPrinter().writeValueAsString(people));
  }
}