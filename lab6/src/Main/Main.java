package Main;

import commands.*;
import services.TourService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TourService tourService = new TourService();

        Map<String, Command> commands = new HashMap<>();
        commands.put("1", new DisplayToursCommand(tourService));
        commands.put("2", new BookTourCommand(tourService));
        commands.put("3", new DisplayTourInfoCommand(tourService));
        commands.put("4", new SearchTourCommand(tourService));
        commands.put("5", new SortToursCommand(tourService));
        commands.put("6", new EmptyCommand());

        Scanner scanner = new Scanner(System.in);
        String choice;

        while (true) {
            displayMenu();
            System.out.print("Виберіть: ");
            choice = scanner.nextLine();

            if ("0".equals(choice)) {
                System.out.println("Вихід...");
                break;
            }

            Command command = commands.get(choice);
            if (command != null) {
                command.execute();
            } else {
                System.out.println("Неправильний вибір");
            }
        }
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("Меню:");
        System.out.println("1. Відображення усіх тури");
        System.out.println("2. Бронювання туру");
        System.out.println("3. Відображення опису туру");
        System.out.println("4. Пошук турів");
        System.out.println("5. Сортування турів");
        System.out.println("6. Тестова команда");
        System.out.println("0. Вихід");
    }
}
