package commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.TourService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class SortToursCommandTest {
    private TourService tourServiceMock;
    private SortToursCommand sortToursCommand;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        // Створення мок-об'єкта для TourService
        tourServiceMock = mock(TourService.class);

        // Створення екземпляра команди з мок-об'єктом
        sortToursCommand = new SortToursCommand(tourServiceMock);

        // Перенаправлення System.out для перевірки виводу
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testExecute_SortByPrice() {
        // Емуляція введення вибору сортування "1"
        System.setIn(new ByteArrayInputStream("1\n".getBytes()));

        // Виклик методу
        sortToursCommand.execute();

        // Перевірка, чи був викликаний метод sortToursByPrice
        verify(tourServiceMock).sortToursByPrice();

        // Перевірка виводу
        String output = outContent.toString();
        assertTrue(output.contains("Тури відсортовано за ціною."));
    }

    @Test
    void testExecute_SortByDuration() {
        // Емуляція введення вибору сортування "2"
        System.setIn(new ByteArrayInputStream("2\n".getBytes()));

        // Виклик методу
        sortToursCommand.execute();

        // Перевірка, чи був викликаний метод sortToursByDuration
        verify(tourServiceMock).sortToursByDuration();

        // Перевірка виводу
        String output = outContent.toString();
        assertTrue(output.contains("Тури відсортовано за тривалістю."));
    }

    @Test
    void testExecute_InvalidOption() {
        // Емуляція введення неправильного вибору "3"
        System.setIn(new ByteArrayInputStream("3\n".getBytes()));

        // Виклик методу
        sortToursCommand.execute();

        // Перевірка, що методи сортування не були викликані
        verify(tourServiceMock, never()).sortToursByPrice();
        verify(tourServiceMock, never()).sortToursByDuration();

        // Перевірка виводу
        String output = outContent.toString();
        assertTrue(output.contains("Неправильний вибір."));
    }
}
