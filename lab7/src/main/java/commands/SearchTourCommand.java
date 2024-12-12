package commands;

import services.TourService;
import java.util.Scanner;

public class SearchTourCommand implements Command {
    private TourService tourService;

    public SearchTourCommand(TourService tourService) {
        this.tourService = tourService;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть назву або ключове слово, щоб знайти тур: ");
        String keyword = scanner.nextLine();

        tourService.searchToursByName(keyword).forEach(tour ->
                System.out.println(tour.getInfo())
        );
    }
}
