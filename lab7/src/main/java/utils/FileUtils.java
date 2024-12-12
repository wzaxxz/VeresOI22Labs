package utils;

import models.Tour;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static List<Tour> loadToursFromFile(String fileName) {
        List<Tour> tours = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length == 4) {
                    String name = details[0].trim();
                    String description = details[1].trim();
                    int price = Integer.parseInt(details[2].trim());
                    int duration = Integer.parseInt(details[3].trim());

                    Tour tour = new Tour(name, description, price, duration);
                    tours.add(tour);
                }
            }
            System.out.println("Тури успішно завантажені з файлу.");
        } catch (IOException e) {
            System.out.println("Помилка завантаження турів: " + e.getMessage());
        }
        return tours;
    }
}
