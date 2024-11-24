package droidBattle.menu;

import droidBattle.battle.OnevOne;
import droidBattle.battle.TeamvTeam;
import droidBattle.droids.Droid;
import droidBattle.file.BattleReplayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.File;

public class Menu {
    private List<Droid> droids;
    private Scanner scanner;

    public Menu() {
        droids = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("=== Droid Battle Menu ===");
            System.out.println("1. Створити дроїда");
            System.out.println("2. Показати список дроїдів");
            System.out.println("3. Запустити бій 1 на 1");
            System.out.println("4. Запустити командний бій");
            System.out.println("5. Переглянути записи боїв");
            System.out.println("6. Вийти з програми");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Очищення буфера

            switch (choice) {
                case 1:
                    createDroid();
                    break;
                case 2:
                    showDroids();
                    break;
                case 3:
                    startOneOnOneBattle();
                    break;
                case 4:
                    startTeamBattle();
                    break;
                case 5:
                    viewBattleRecords();
                    break;
                case 6:
                    System.out.println("Вихід...");
                    return;
                default:
                    System.out.println("Невірний вибір, спробуйте ще раз.");
            }
        }
    }

    private void createDroid() {
        System.out.println("Оберіть тип дроїда:");
        System.out.println("1. EnergyDroid");
        System.out.println("2. MirrorDroid");
        System.out.println("3. StealthDroid");
        System.out.println("4. VampireDroid");
        System.out.println("5. ExplosiveDroid");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Очищаєм буфер

        Droid droid = DroidFactory.createDroid(choice);
        if (droid != null) {
            droids.add(droid);
            System.out.println("Дроїд успішно створений: " + droid.getName());
        } else {
            System.out.println("Неправильний вибір. Дроїд не створений.");
        }
    }

    private void showDroids() {
        if (droids.isEmpty()) {
            System.out.println("Список дроїдів порожній.");
        } else {
            System.out.println("Список створених дроїдів:");
            for (int i = 0; i < droids.size(); i++) {
                System.out.println((i + 1) + ". " + droids.get(i).getName());
            }
        }
    }

    private void startOneOnOneBattle() {
        if (droids.size() < 2) {
            System.out.println("Недостатньо дроїдів для бою 1 на 1.");
            return;
        }

        System.out.println("Виберіть двох дроїдів для бою 1 на 1:");
        showDroids();

        System.out.print("Оберіть дроїда 1: ");
        int droid1Index = scanner.nextInt() - 1;
        System.out.print("Оберіть дроїда 2: ");
        int droid2Index = scanner.nextInt() - 1;

        Droid droid1 = droids.get(droid1Index);
        Droid droid2 = droids.get(droid2Index);

        String fileName = "battle_one_on_one_" + System.currentTimeMillis() + ".txt";
        OnevOne battle = new OnevOne(droid1, droid2, fileName);
        battle.start();
    }

    private void startTeamBattle() {
        if (droids.size() < 4) {
            System.out.println("Недостатньо дроїдів для командного бою. Потрібно щонайменше 4 дроїди.");
            return;
        }

        System.out.println("Виберіть 2 дроїдів для першої команди:");
        showDroids();

        System.out.print("Оберіть дроїда 1 для команди 1: ");
        int team1Droid1Index = scanner.nextInt() - 1;

        System.out.print("Оберіть дроїда 2 для команди 1: ");
        int team1Droid2Index = scanner.nextInt() - 1;

        System.out.println("Виберіть 2 дроїдів для другої команди:");
        showDroids();

        System.out.print("Оберіть дроїда 1 для команди 2: ");
        int team2Droid1Index = scanner.nextInt() - 1;

        System.out.print("Оберіть дроїда 2 для команди 2: ");
        int team2Droid2Index = scanner.nextInt() - 1;

        // Отримуємо дроїдів за індексами
        Droid team1Droid1 = droids.get(team1Droid1Index);
        Droid team1Droid2 = droids.get(team1Droid2Index);
        Droid team2Droid1 = droids.get(team2Droid1Index);
        Droid team2Droid2 = droids.get(team2Droid2Index);

        // Створюємо списки дроїдів для команд
        List<Droid> team1 = Arrays.asList(team1Droid1, team1Droid2);
        List<Droid> team2 = Arrays.asList(team2Droid1, team2Droid2);

        // Створюємо файл для запису командного бою
        String fileName = "battle_teamvteam_" + System.currentTimeMillis() + ".txt";

        // Створюємо командний бій
        TeamvTeam teamBattle = new TeamvTeam(
                team1, team2,
                fileName
        );
        teamBattle.start();
    }

    private void viewBattleRecords() {
        File folder = new File(".");
        File[] files = folder.listFiles((dir, name) -> name.startsWith("battle_") && name.endsWith(".txt"));

        if (files != null && files.length > 0) {
            System.out.println("Доступні записи боїв:");
            for (int i = 0; i < files.length; i++) {
                System.out.println((i + 1) + ". " + files[i].getName());
            }

            System.out.print("Оберіть номер запису для перегляду: ");
            int fileChoice = scanner.nextInt() - 1;

            if (fileChoice >= 0 && fileChoice < files.length) {
                BattleReplayer.play(files[fileChoice].getName());
            } else {
                System.out.println("Невірний вибір.");
            }
        } else {
            System.out.println("Записів боїв не знайдено.");
        }
    }
}
