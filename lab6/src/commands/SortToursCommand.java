package commands;

import services.TourService;
import java.util.Scanner;

public class SortToursCommand implements Command {
    private TourService tourService;

    public SortToursCommand(TourService tourService) {
        this.tourService = tourService;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Виберіть критерій сортування (1 - за ціною, 2 - за тривалістю): ");
        String option = scanner.nextLine();

        switch (option) {
            case "1":
                tourService.sortToursByPrice();
                System.out.println("Тури відсортовано за ціною.");
                break;
            case "2":
                tourService.sortToursByDuration();
                System.out.println("Тури відсортовано за тривалістю.");
                break;
            default:
                System.out.println("Неправильний вибір.");
        }

        tourService.displayTours();
    }
}
