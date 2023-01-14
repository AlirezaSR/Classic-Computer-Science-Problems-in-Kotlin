package chapter02.search;


import java.util.List;

public class Sample {
    public static void main(String[] args) {
        System.out.println(LinearSearch.search(List.of(1, 5, 15, 15, 15, 15, 20), 5)); // 1
        System.out.println(BinarySearch.search(List.of("a", "d", "e", "f", "z"), "f")); // 3
        System.out.println(BinarySearch.search(List.of("john", "mark", "ronald", "sarah"), "sheila")); // -1
    }
}


