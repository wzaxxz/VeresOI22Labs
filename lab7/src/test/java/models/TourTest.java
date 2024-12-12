package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TourTest {
    private Tour tour;

    @BeforeEach
    void setUp() {
        tour = new Tour("Гірський тур", "Поїздка в Карпати", 1000, 7);
    }

    @Test
    void testConstructor() {
        assertEquals("Гірський тур", tour.getName());
        assertEquals(1000, tour.getPrice());
        assertEquals(7, tour.getDuration());
        assertFalse(tour.isBooked());
    }

    @Test
    void testBookTour() {
        // Перевірка методу bookTour
        tour.bookTour();
        assertTrue(tour.isBooked(), "Тур має бути заброньований.");
    }

    @Test
    void testGetInfo_WhenNotBooked() {
        // Перевірка методу getInfo, коли тур не заброньовано
        String expectedInfo = "Гірський тур, Ціна: 1000, Тривалість: 7 днів";
        assertEquals(expectedInfo, tour.getInfo());
    }

    @Test
    void testGetInfo_WhenBooked() {
        // Перевірка методу getInfo, коли тур заброньовано
        tour.bookTour();
        String expectedInfo = "Гірський тур, Ціна: 1000, Тривалість: 7 днів (Заброньовано)";
        assertEquals(expectedInfo, tour.getInfo());
    }

    @Test
    void testGetFullInfo_WhenNotBooked() {
        // Перевірка методу getFullInfo, коли тур не заброньовано
        String expectedFullInfo = "Гірський тур: Поїздка в Карпати, Ціна: 1000, Тривалість: 7 днів";
        assertEquals(expectedFullInfo, tour.getFullInfo());
    }

    @Test
    void testGetFullInfo_WhenBooked() {
        // Перевірка методу getFullInfo, коли тур заброньовано
        tour.bookTour();
        String expectedFullInfo = "Гірський тур: Поїздка в Карпати, Ціна: 1000, Тривалість: 7 днів (Заброньовано)";
        assertEquals(expectedFullInfo, tour.getFullInfo());
    }

    @Test
    void testBookTour_WhenAlreadyBooked() {
        // Перевірка методу bookTour, коли тур вже заброньований
        tour.bookTour();
        tour.bookTour(); // намагаємось забронювати знову
        assertTrue(tour.isBooked(), "Тур має бути заброньований.");
    }
}
