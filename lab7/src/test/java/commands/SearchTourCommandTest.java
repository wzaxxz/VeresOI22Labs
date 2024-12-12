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

class SearchTourCommandTest {
    private TourService tourServiceMock;
    private SearchTourCommand searchTourCommand;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        // Створення мок-об'єкта для TourService
        tourServiceMock = mock(TourService.class);

        // Створення екземпляра команди з мок-об'єктом
        searchTourCommand = new SearchTourCommand(tourServiceMock);

        // Перенаправлення System.out для перевірки виводу
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testExecute_SearchByKeyword() {
        // Налаштування поведінки мока
        List<Tour> mockTours = Arrays.asList(
                new Tour("Гірський тур", "Поїздка в Карпати", 1000, 5),
                new Tour("Морський тур", "Подорож на узбережжя", 2000, 7)
        );
        when(tourServiceMock.searchToursByName("тур")).thenReturn(mockTours);

        // Емуляція введення ключового слова
        System.setIn(new ByteArrayInputStream("тур\n".getBytes()));

        // Виклик методу
        searchTourCommand.execute();

        // Перевірка, чи метод searchToursByName був викликаний з правильним аргументом
        verify(tourServiceMock).searchToursByName("тур");

        // Перевірка виводу
        String output = outContent.toString();
        assertTrue(output.contains("Гірський тур"));
        assertTrue(output.contains("Морський тур"));
    }
}
