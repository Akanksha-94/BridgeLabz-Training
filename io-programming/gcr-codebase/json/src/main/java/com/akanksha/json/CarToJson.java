package com.akanksha.json;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CarToJson {
  static class Car {
    public String make;
    public String model;
    public int year;

    Car() {
    }

    Car(String make, String model, int year) {
      this.make = make;
      this.model = model;
      this.year = year;
    }
  }

  public static void main(String[] args) throws Exception {
    ObjectMapper m = new ObjectMapper();
    Car c = new Car("Toyota", "Camry", 2022);
    System.out.println(m.writerWithDefaultPrettyPrinter().writeValueAsString(c));
  }
}