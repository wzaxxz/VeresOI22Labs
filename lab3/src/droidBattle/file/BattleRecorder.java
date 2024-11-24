package droidBattle.file;

import java.io.FileWriter;
import java.io.IOException;

public class BattleRecorder {
    private StringBuilder log;
    private String fileName;

    public BattleRecorder(String fileName) {
        this.log = new StringBuilder();
        this.fileName = fileName;
    }

    public void record(String action) {
        log.append(action).append("\n");
    }

    public void save() {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(log.toString());
            System.out.println("Запис бою збережено у файл: " + fileName);
        } catch (IOException e) {
            System.out.println("Помилка при записі файлу: " + e.getMessage());
        }
    }
}
