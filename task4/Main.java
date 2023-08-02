package task4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Необходимо указать путь к файлу входных данных");
            return;
        }
        System.out.println(minStepsToReduce(readInputFromFile(args[0])));
    }

    private static List<Integer> readInputFromFile(String filePath) {
        try {
            List<Integer> numbers = Files.lines(Paths.get(filePath)).map(Integer::parseInt).collect(Collectors.toList());   // Получение данных из файла
            Collections.sort(numbers);                                                                                      // Сортировка для дальнейшего метода
            return numbers;
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла");
            return null;
        }
    }

    public static int minStepsToReduce(List<Integer> nums) {
        if (nums.size() <= 1) return 0;                                     // В случае, если у нас 0 или 1 число, смысла выполнения нет

        int steps = 0 ,medianFst, medianSec, medianCentral;
        if (nums.size() % 2 == 0) {                                         // Тут, для случая, когда делится на 2
            medianSec = nums.size() / 2;
            medianFst = medianSec - 1;
            medianCentral = (nums.get(medianFst) + nums.get(medianSec)) / 2;
        } else {                                                            // И когда не делится на 2
            medianCentral = nums.get(nums.size() / 2);
        }
        for (int num : nums) {
            steps += Math.abs(num - medianCentral);                         // Выясняем количество шагов
        }
        return steps;
    }
}