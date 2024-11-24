package droidBattle.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BattleReplayer {

    public static void play(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            System.out.println("Відтворення запису бою з файлу: " + fileName);
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Помилка при читанні файлу: " + e.getMessage());
        }
    }
}
