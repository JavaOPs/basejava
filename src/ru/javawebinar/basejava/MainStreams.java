package ru.javawebinar.basejava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MainStreams {

  public static void main(String[] args) {
    int value = minValue(new int[]{3, 2, 6, 6, 3, 1, 2, 2});
    System.out.println(value);

    List<Integer> numbers = new ArrayList<>();
    numbers.add(2);
    numbers.add(3);
    numbers.add(1);
    numbers.add(6);
    numbers.add(4);
    numbers.add(5);
    System.out.println(oddOrEven(numbers));
  }

  private static int minValue(int[] values) {
    int[] sortedDistinctNumbers = Arrays.stream(values)
        .distinct()
        .sorted()
        .toArray();
    AtomicInteger size = new AtomicInteger(sortedDistinctNumbers.length);
    return Arrays.stream(sortedDistinctNumbers)
        .map(number -> number * (int) Math.pow(10, size.decrementAndGet()))
        .sum();
  }

  private static List<Integer> oddOrEven(List<Integer> integers) {
    int sum = integers.stream()
        .mapToInt(i -> i)
        .sum();
    return sum % 2 == 0 ? getOdd(integers) : getEven(integers);
  }

  private static List<Integer> getOdd(List<Integer> integers) {
    return integers.stream()
        .filter(n -> n % 2 != 0)
        .collect(Collectors.toList());
  }

  private static List<Integer> getEven(List<Integer> integers) {
    return integers.stream()
        .filter(n -> n % 2 == 0)
        .collect(Collectors.toList());
  }
}
