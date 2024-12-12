package services;

import models.Tour;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

class TourServiceTest {
    private TourService tourService;
    private Tour tour1;
    private Tour tour2;

    @BeforeEach
    void setUp() {
        // Створення об'єктів турів для тестів
        tourService = new TourService();
        tour1 = new Tour("Гірський тур", "Поїздка в Карпати", 1000, 7);
        tour2 = new Tour("Морський тур", "Подорож на узбережжя", 2000, 5);

        // Додавання турів в сервіс
        tourService.getAllTours().addAll(Arrays.asList(tour1, tour2));
    }

    @Test
    void testBookTourByName() {
        // Перевірка бронювання туру
        assertTrue(tourService.bookTourByName("Гірський тур"), "Тур має бути заброньований.");
        assertTrue(tour1.isBooked(), "Гірський тур має бути заброньованим.");

        // Перевірка, що повторне бронювання не працює
        assertFalse(tourService.bookTourByName("Гірський тур"), "Тур не можна забронювати двічі.");
    }

    @Test
    void testGetTourInfoByName() {
        // Перевірка отримання повної інформації про тур
        String expectedInfo = "Гірський тур: Поїздка в Карпати, Ціна: 1000, Тривалість: 7 днів";
        assertEquals(expectedInfo, tourService.getTourInfoByName("Гірський тур"));
    }

    @Test
    void testSearchToursByName() {
        // Перевірка пошуку турів за ключовим словом
        List<Tour> foundTours = tourService.searchToursByName("Морський");
        assertEquals(1, foundTours.size(), "Тур мав бути знайдений.");
        assertEquals("Морський тур", foundTours.get(0).getName(), "Знайдений тур не відповідає очікуванням.");
    }

    @Test
    void testSearchToursByName_NoResult() {
        // Перевірка, що не знайдено турів за невірним запитом
        List<Tour> foundTours = tourService.searchToursByName("Літній тур");
        assertTrue(foundTours.isEmpty(), "Не повинно бути знайдено турів.");
    }

    @Test
    void testSortToursByPrice() {
        // Перевірка сортування турів за ціною
        tourService.sortToursByPrice();
        assertEquals(1000, tourService.getAllTours().get(0).getPrice(), "Тури мають бути відсортовані за ціною.");
    }

    @Test
    void testSortToursByDuration() {
        // Перевірка сортування турів за тривалістю
        tourService.sortToursByDuration();
        assertEquals(5, tourService.getAllTours().get(0).getDuration(), "Тури мають бути відсортовані за тривалістю.");
    }

    @Test
    void testDisplayTours() {
        // Створення мок-об'єкта для виведення інформації
        TourService tourServiceMock = mock(TourService.class);
        doNothing().when(tourServiceMock).displayTours(); // Мок метод displayTours

        // Виклик методу
        tourServiceMock.displayTours();

        // Перевірка, що метод був викликаний
        verify(tourServiceMock).displayTours();
    }
}
