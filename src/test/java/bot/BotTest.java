package bot;

import example.bot.BotLogic;
import example.bot.ConsoleBot;
import example.bot.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Scanner;

public class BotTest {
    private BotLogic currentBotLogic;
    private Scanner scanner;
    private User testUser;

    /**
     * Получение действующей логики из бота, создание сканера и создание пользователя
     */
    @Before
    public void createNoteLogic() throws Exception {
        var testConsoleBot = new ConsoleBot();
        testConsoleBot.run();

        var botLogicField = testConsoleBot.getClass().getDeclaredField("botLogic");
        botLogicField.setAccessible(true);
        BotLogic currentBotLogic = (BotLogic) botLogicField.get(testConsoleBot);

        this.currentBotLogic = currentBotLogic;
        this.scanner = new Scanner(System.in);

        testUser = new User(0L);
    }

    @Test
    public void correctMessageTestTest() {
        currentBotLogic.processCommand(testUser, "/test");
        String consoleOutput = scanner.nextLine();
        
        Assert.assertEquals(consoleOutput, "Вычислите степень: 10^2");

    }
    @Test
    public void wrongMessageTestTest() {

    }
}
