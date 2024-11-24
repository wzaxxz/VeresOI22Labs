package main;

import commands.*;
import menu.Menu;
import services.TourService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TourService tourService = new TourService();

        Menu menu = new Menu();
        menu.addCommand("1", new DisplayToursCommand(tourService));
        menu.addCommand("2", new BookTourCommand(tourService));
        menu.addCommand("3", new DisplayTourInfoCommand(tourService));
        menu.addCommand("4", new SearchTourCommand(tourService));
        menu.addCommand("5", new SortToursCommand(tourService));

        Scanner scanner = new Scanner(System.in);
        String choice;

        while (true) {
            menu.displayMenu();
            System.out.print("Виберіть опцію: ");
            choice = scanner.nextLine();

            if (choice.equals("0")) {
                System.out.println("Вихід...");
                break;
            }

            menu.executeCommand(choice);
        }

        scanner.close();
    }
}
