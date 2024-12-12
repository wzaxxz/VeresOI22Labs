import commands.Command;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.mockito.Mockito.*;

class MainTest {

    @Test
    void testRunAppWithValidCommands() {
        // Симулюємо ввід користувача
        String simulatedInput = "1\n2\n0\n"; // Вибір 1 -> 2 -> Вихід (0)
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        Scanner scanner = new Scanner(inputStream);

        // Моки для команд
        Command command1 = Mockito.mock(Command.class);
        Command command2 = Mockito.mock(Command.class);

        // Ініціалізуємо командну мапу
        Map<String, Command> commands = new HashMap<>();
        commands.put("1", command1);
        commands.put("2", command2);

        // Виконуємо метод
        Main.runApp(scanner, commands);

        // Перевіряємо, чи були викликані команди
        verify(command1, times(1)).execute();
        verify(command2, times(1)).execute();
    }

    @Test
    void testRunAppWithInvalidCommand() {
        // Симулюємо ввід користувача: неправильна команда + вихід
        String simulatedInput = "9\n0\n"; // Неправильна команда (9) -> Вихід (0)
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        Scanner scanner = new Scanner(inputStream);

        // Мапа з командами без визначення для "9"
        Map<String, Command> commands = new HashMap<>();

        // Перехоплюємо вивід у консоль
        Main.runApp(scanner, commands);

        // Немає команд для перевірки, перевіряємо відсутність викликів
        // (за бажанням можна перехопити вивід у консоль для перевірки повідомлення "Неправильний вибір").
    }
}
