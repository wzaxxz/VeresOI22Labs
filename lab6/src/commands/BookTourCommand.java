package commands;

import services.TourService;
import java.util.Scanner;

public class BookTourCommand implements Command {
    private TourService tourService;

    public BookTourCommand(TourService tourService) {
        this.tourService = tourService;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть назву туру для бронювання: ");
        String tourName = scanner.nextLine();

        boolean success = tourService.bookTourByName(tourName);
        if (success) {
            System.out.println("Тур був успішно заброньований.");
        } else {
            System.out.println("Тур уже є заброньованим.");
        }
    }
}
