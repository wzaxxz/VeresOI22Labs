package commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.TourService;
import models.Tour;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class DisplayToursCommandTest {
    private TourService tourServiceMock;
    private DisplayToursCommand displayToursCommand;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        // Створення мок-об'єкта для TourService
        tourServiceMock = mock(TourService.class);

        // Створення екземпляра команди з мок-об'єктом
        displayToursCommand = new DisplayToursCommand(tourServiceMock);

        // Перенаправлення System.out для тестування виводу
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testExecute_LoadFromFile() {
        // Емуляція введення користувачем
        System.setIn(new ByteArrayInputStream("1\ntours.txt\n".getBytes()));

        // Виклик методу
        displayToursCommand.execute();

        // Перевірка виклику методу завантаження файлів
        verify(tourServiceMock).loadToursFromFile("tours.txt");
    }

    @Test
    void testExecute_DisplayTours() {
        // Налаштування поведінки мока
        List<Tour> mockTours = Arrays.asList(
                new Tour("Гірський тур", "Поїздка в Карпати", 1000, 5), // тривалість 5 днів
                new Tour("Морський тур", "Подорож на узбережжя", 2000, 7)
        );
        when(tourServiceMock.getAllTours()).thenReturn(mockTours);

        // Емуляція введення користувачем (без завантаження файлів)
        System.setIn(new ByteArrayInputStream("0\n".getBytes()));

        // Виклик методу
        displayToursCommand.execute();

        // Перевірка виклику методу отримання турів
        verify(tourServiceMock).getAllTours();

        // Перевірка виводу
        String output = outContent.toString();
        assertTrue(output.contains("Доступні тури:"));
        assertTrue(output.contains("Гірський тур"));
        assertTrue(output.contains("Морський тур"));
    }
}
