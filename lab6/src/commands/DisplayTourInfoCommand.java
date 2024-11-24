package commands;

import services.TourService;
import java.util.Scanner;

public class DisplayTourInfoCommand implements Command {
    private TourService tourService;

    public DisplayTourInfoCommand(TourService tourService) {
        this.tourService = tourService;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введи назву туру, щоб подивитися повну інформацію: ");
        String tourName = scanner.nextLine();

        String info = tourService.getTourInfoByName(tourName);
        if (info != null) {
            System.out.println("Інформація про тур:");
            System.out.println(info);
        } else {
            System.out.println("Тур не знайдено.");
        }
    }
}
