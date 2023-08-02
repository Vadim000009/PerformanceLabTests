package task1;

import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Менее двух аргументов");
            return;
        } else if (args.length > 2) {
            System.out.println("Более двух аргументов");
            return;
        }
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);
        circularArrayTraversal(n, m);
    }

    /**
     * Метод, для нахождения интервалов, пока концом не будет являться элемент circularArray[0]
     * @param n - длина массива
     * @param m - интервал хода
     */
    public static void circularArrayTraversal(int n, int m) {
        StringBuilder path = new StringBuilder();                                   // Результирующая строка
        int[] circularArray = IntStream.range(0, n).map(i -> i + 1).toArray();      // Заполняем массив числами
        int currentIndex = 0;                                                       // Индекс

        while (true) {                                                              // Пока путь не содержит все элементы
            if (circularArray[(currentIndex + m - 1) % n] != circularArray[0]) {    // Проверка, что число удовлетворяет условиям
                path.append(circularArray[currentIndex]);                           // Добавляем текущий элемент в путь
                currentIndex = (currentIndex + m - 1) % n;                          // Вычисляем индекс следующего элемента с учетом интервала m
            } else {                                                                // Иначе
                path.append(circularArray[currentIndex]);                           // Добавляем текущий элемент в путь
                break;                                                              // Уходим
            }
        }
        System.out.println(path);                                                   // И сообщаем результат
    }
}
