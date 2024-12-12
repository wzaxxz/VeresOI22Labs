package commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.TourService;

import static org.mockito.Mockito.*;

class BookTourCommandTest {
    private TourService tourServiceMock;
    private BookTourCommand bookTourCommand;

    @BeforeEach
    void setUp() {
        // Створення мок-об'єкта для TourService
        tourServiceMock = mock(TourService.class);
        bookTourCommand = new BookTourCommand(tourServiceMock);
    }

    @Test
    void testExecute_TourBookedSuccessfully() {
        // Налаштування мока: тур успішно заброньовано
        when(tourServiceMock.bookTourByName("Гірський відпочинок")).thenReturn(true);

        // Емуляція введення користувача через Scanner
        System.setIn(new java.io.ByteArrayInputStream("Гірський відпочинок\n".getBytes()));

        // Виклик методу
        bookTourCommand.execute();

        // Перевірка, що метод bookTourByName був викликаний з правильним аргументом
        verify(tourServiceMock).bookTourByName("Гірський відпочинок");
    }

    @Test
    void testExecute_TourAlreadyBooked() {
        // Налаштування мока: тур уже заброньовано
        when(tourServiceMock.bookTourByName("Морський відпочинок")).thenReturn(false);

        // Емуляція введення користувача через Scanner
        System.setIn(new java.io.ByteArrayInputStream("Морський відпочинок\n".getBytes()));

        // Виклик методу
        bookTourCommand.execute();

        // Перевірка, що метод bookTourByName був викликаний з правильним аргументом
        verify(tourServiceMock).bookTourByName("Морський відпочинок");
    }
}
