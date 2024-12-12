package utils;

import models.Tour;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilsTest {

    private static final String TEST_FILE = "test_tours.txt";

    @BeforeEach
    void setUp() throws IOException {
        // Створюємо тестовий файл із даними
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_FILE))) {
            writer.write("Гірський тур,Поїздка в Карпати,1000,7\n");
            writer.write("Морський тур,Подорож на узбережжя,2000,5\n");
        }
    }

    @Test
    void testLoadToursFromFile() {
        // Викликаємо метод для завантаження турів
        List<Tour> tours = FileUtils.loadToursFromFile(TEST_FILE);

        // Перевіряємо, що список турів завантажився правильно
        assertNotNull(tours, "Список турів не повинен бути null");
        assertEquals(2, tours.size(), "Кількість завантажених турів має бути 2");

        // Перевіряємо деталі кожного туру
        Tour tour1 = tours.get(0);
        assertEquals("Гірський тур", tour1.getName());
        assertEquals("Гірський тур: Поїздка в Карпати, Ціна: 1000, Тривалість: 7 днів", tour1.toString());
        assertEquals(1000, tour1.getPrice());
        assertEquals(7, tour1.getDuration());

        Tour tour2 = tours.get(1);
        assertEquals("Морський тур", tour2.getName());
        assertEquals("Морський тур: Подорож на узбережжя, Ціна: 2000, Тривалість: 5 днів", tour2.toString());
        assertEquals(2000, tour2.getPrice());
        assertEquals(5, tour2.getDuration());
    }

    @Test
    void testLoadToursFromFileWithInvalidData() throws IOException {
        // Створюємо файл із некоректними даними
        String invalidFile = "invalid_tours.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(invalidFile))) {
            writer.write("InvalidData\n");
            writer.write("Гірський тур,Поїздка в Карпати,1000\n"); // Менше полів
        }

        // Викликаємо метод і перевіряємо, що некоректні дані ігноруються
        List<Tour> tours = FileUtils.loadToursFromFile(invalidFile);
        assertNotNull(tours, "Список турів не повинен бути null");
        assertEquals(0, tours.size(), "Список турів має бути порожнім для некоректних даних");

        // Видаляємо тестовий файл
        Files.delete(Paths.get(invalidFile));

    }
}
