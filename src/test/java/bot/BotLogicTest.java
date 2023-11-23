package bot;

import example.bot.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BotLogicTest {
    private BotLogic testBotLogic;
    private FakeBot testMockBot;
    private User testUser;

    @Before
    public void addingData() {
        testMockBot = new FakeBot();
        testBotLogic = new BotLogic(testMockBot);
        testUser = new User(101L);
    }

    @Test
    public void processCommandTestTest() {
        testBotLogic.processCommand(testUser, "/test");

        assertEquals(State.TEST, testUser.getState());
        assertEquals("Вычислите степень: 10^2", testMockBot.getLastMessage());
    }

    @Test
    public void checkAnswerIncorrectTest() {
        testUser.setState(State.TEST);
        testBotLogic.processCommand(testUser, "Test_1010");

        assertEquals(State.TEST, testUser.getState());
        assertFalse(testUser.getCurrentWrongAnswerQuestion().isEmpty());
        assertEquals("Сколько будет 2 + 2 * 2", testMockBot.getLastMessage());
    }

    @Test
    public void processCommandNotifyTest() throws InterruptedException {
        testUser.setState(State.SET_NOTIFY_TEXT);
        testBotLogic.processCommand(testUser, "Напоминание");
        assertNotNull(testUser.getNotification());

        testUser.setState(State.SET_NOTIFY_DELAY);
        testBotLogic.processCommand(testUser, "1");

        Thread.sleep(1_100);
        assertEquals("Сработало напоминание: 'Напоминание'", testMockBot.getLastMessage());
    }

    @Test
    public void processCommandSetNotifyInvalidTest() {
        testUser.setState(State.SET_NOTIFY_DELAY);
        testUser.createNotification("Test_02");
        testBotLogic.processCommand(testUser, "invalid_delay");

        assertEquals(State.SET_NOTIFY_DELAY, testUser.getState());
        assertEquals("Пожалуйста, введите целое число", testMockBot.getLastMessage());
    }

    @Test
    public void processCommandRepeatNoQuestionsTest() {
        testUser.setState(State.REPEAT);
        testBotLogic.processCommand(testUser, "/repeat");

        assertEquals(State.REPEAT, testUser.getState());
        assertEquals("Нет вопросов для повторения", testMockBot.getLastMessage());
    }

    private static class FakeBot implements Bot {
        private String lastMessage;

        @Override
        public void sendMessage(Long chatId, String message) {
            lastMessage = message;
        }

        public String getLastMessage() {
            return lastMessage;
        }
    }
}
