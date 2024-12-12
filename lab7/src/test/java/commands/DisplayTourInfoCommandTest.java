package commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.TourService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class DisplayTourInfoCommandTest {
    private TourService tourServiceMock;
    private DisplayTourInfoCommand displayTourInfoCommand;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        // Створення мок-об'єкта для TourService
        tourServiceMock = mock(TourService.class);

        // Створення екземпляра команди з мок-об'єктом
        displayTourInfoCommand = new DisplayTourInfoCommand(tourServiceMock);

        // Перенаправлення System.out для тестування виводу
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testExecute_TourFound() {
        // Налаштування поведінки мока
        when(tourServiceMock.getTourInfoByName("Гірський тур")).thenReturn("Це чудовий тур у Карпати!");

        // Емуляція введення користувача
        System.setIn(new ByteArrayInputStream("Гірський тур\n".getBytes()));

        // Виклик методу
        displayTourInfoCommand.execute();

        // Перевірка виклику методу сервісу
        verify(tourServiceMock).getTourInfoByName("Гірський тур");

        // Перевірка виводу
        String output = outContent.toString();
        assertTrue(output.contains("Інформація про тур:"));
        assertTrue(output.contains("Це чудовий тур у Карпати!"));
    }

    @Test
    void testExecute_TourNotFound() {
        // Налаштування поведінки мока
        when(tourServiceMock.getTourInfoByName("Морський тур")).thenReturn(null);

        // Емуляція введення користувача
        System.setIn(new ByteArrayInputStream("Морський тур\n".getBytes()));

        // Виклик методу
        displayTourInfoCommand.execute();

        // Перевірка виклику методу сервісу
        verify(tourServiceMock).getTourInfoByName("Морський тур");

        // Перевірка виводу
        String output = outContent.toString();
        assertTrue(output.contains("Тур не знайдено."));
    }
}
