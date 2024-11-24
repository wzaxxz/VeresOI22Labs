package menu;

import commands.Command;

import java.util.HashMap;
import java.util.Map;

public class Menu {
    private Map<String, Command> commands = new HashMap<>();

    public void addCommand(String option, Command command) {
        commands.put(option, command);
    }

    public void executeCommand(String option) {
        Command command = commands.get(option);
        if (command != null) {
            command.execute();
        } else {
            System.out.println("Неправильний вибір");
        }
    }

    public void displayMenu() {
        System.out.println("Меню:");
        System.out.println("1. Показ турів");
        System.out.println("2. Бронювання туру");
        System.out.println("3. Повна інформація про тур");
        System.out.println("4. Пошук турів");
        System.out.println("5. Сортування турів");
        System.out.println("0. Вихід");
    }
}
