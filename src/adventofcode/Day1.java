package adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day1 {

    public static void main(String[] argv) throws IOException {
        System.out.println(getMaxCalories(Files.readAllLines(Path.of("resources/day1.txt"))));
        System.out.println(getMaxCalories(Files.readAllLines(Path.of("resources/day1.txt")), 3));
    }

    public static Integer getMaxCalories(List<String> lines) {
        int max = 0;
        int cur = 0;
        for (String line : lines) {
            if (line.isBlank()) {
                max = Math.max(cur, max);
                cur = 0;
            } else {
                cur += Integer.parseInt(line);
            }
        }

        return max;
    }

    public static Integer getMaxCalories(List<String> lines, int maxElves) {
        int cur = 0;
        List<Integer> elves = new ArrayList<>();
        for (String line : lines) {
            if (line.isBlank()) {
                elves.add(cur);
                cur = 0;
            } else {
                cur += Integer.parseInt(line);

            }
        }

        elves.sort(Comparator.reverseOrder());
        int sum = 0;
        for(int i = maxElves-1; i>=0; i--){
            sum += elves.get(i);
        }
        return sum;
    }
}
