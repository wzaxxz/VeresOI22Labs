package services;

import utils.FileUtils;
import models.Tour;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class TourService {
    private List<Tour> tours = new ArrayList<>();

    public void loadToursFromFile(String fileName) {
        tours = FileUtils.loadToursFromFile(fileName);
    }

    public List<Tour> getAllTours() {
        return tours;
    }

    public boolean bookTourByName(String name) {
        for (Tour tour : tours) {
            if (tour.getName().equalsIgnoreCase(name) && !tour.isBooked()) {
                tour.bookTour();
                return true;
            }
        }
        return false;
    }

    public String getTourInfoByName(String name) {
        for (Tour tour : tours) {
            if (tour.getName().equalsIgnoreCase(name)) {
                return tour.getFullInfo();
            }
        }
        return null;
    }

    public List<Tour> searchToursByName(String keyword) {
        return tours.stream()
                .filter(tour -> tour.getName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void sortToursByPrice() {
        tours.sort((t1, t2) -> Integer.compare(t1.getPrice(), t2.getPrice()));
    }

    public void sortToursByDuration() {
        tours.sort((t1, t2) -> Integer.compare(t1.getDuration(), t2.getDuration()));
    }

    public void displayTours() {
        tours.forEach(tour -> System.out.println(tour.getInfo()));
    }
}
