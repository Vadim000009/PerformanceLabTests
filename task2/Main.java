package task2;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Начинаем чтение с файла начальных координат
        double x0, y0;
        int radius;
        try (Scanner readPosition = new Scanner(new File(args[0]))) {
            String[] centre = readPosition.nextLine().strip().split(" ");
            x0 = Double.parseDouble(centre[0]);
            y0 = Double.parseDouble(centre[1]);
            radius = Integer.parseInt(readPosition.nextLine());
            readPosition.close();
        } catch (Exception e) {
            System.out.println("Ошибка чтения файла " + args[0]);
            return;
        }

        // Чтение с файла проверяемых координат
        List<Double> coordinates = new ArrayList<>();
        try (Scanner readCoordinates = new Scanner(new File(args[1]))) {
            while (readCoordinates.hasNextLine()) {
                String[] points = readCoordinates.nextLine().strip().split(" ");
                if (points.length > 0) {
                    coordinates.add(Double.parseDouble(points[0]));
                    coordinates.add(Double.parseDouble(points[1]));
                } else {
                    break;
                }
            }
            readCoordinates.close();
        } catch (Exception e) {
            System.out.println("Ошибка чтения файла " + args[1]);
            return;
        }

        // Логика проверки координат на координатной плоскости
        for (int i = 0; i < coordinates.size(); i += 2) {
            double xn = coordinates.get(i), yn = coordinates.get(i + 1);
            double vectorLength = Math.pow(Math.pow(xn - x0, 2) + Math.pow(yn - y0, 2), 0.5);
            if (vectorLength == radius) {
                System.out.print(0 + " ");
            } else if (vectorLength < radius) {
                System.out.print(1 + " ");
            } else {
                System.out.print(2 + " ");
            }
        }
    }
}