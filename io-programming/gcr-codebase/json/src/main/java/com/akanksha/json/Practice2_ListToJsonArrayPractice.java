package com.akanksha.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Practice2_ListToJsonArrayPractice {
  public static void main(String[] args) throws Exception {
    ObjectMapper m = new ObjectMapper();
    List<String> names = IntStream.range(1, 6).mapToObj(i -> "User" + i).collect(Collectors.toList());
    System.out.println(m.writerWithDefaultPrettyPrinter().writeValueAsString(names));
  }
}