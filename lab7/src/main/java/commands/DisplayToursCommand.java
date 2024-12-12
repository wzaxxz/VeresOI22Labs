package commands;

import services.TourService;
import java.util.Scanner;

public class DisplayToursCommand implements Command {
    private TourService tourService;

    public DisplayToursCommand(TourService tourService) {
        this.tourService = tourService;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Чи завантажувати тури з файлу? (0/1): ");
        String loadFromFile = scanner.nextLine().trim().toLowerCase();

        if (loadFromFile.equals("1")) {
            System.out.print("Введіть назву файлу: ");
            String fileName = scanner.nextLine();
            tourService.loadToursFromFile(fileName);
        }

        System.out.println("Доступні тури:");
        tourService.getAllTours().forEach(tour -> System.out.println(tour.getInfo()));
    }
}
