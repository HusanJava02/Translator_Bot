package TelegramApiConnection;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class Initialization {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        UpdateClass updateClass = new UpdateClass();
        TelegramBotsApi api = new TelegramBotsApi();
        try {
            api.registerBot(updateClass);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }


    }
}
