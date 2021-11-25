package TelegramApiConnection;

import ConnectToApi.Translator;
import MessageHandler.TextMessageHandler;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.net.URISyntaxException;

public class UpdateClass extends TelegramLongPollingBot {
    public static String fromLang="";
    public static String toLang="";
    public static String Botstate;
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            try {
                String text = "tilidagi matnni kiriting!";
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(update.getMessage().getChatId());
                sendMessage.setReplyMarkup(new ReplyKeyboardRemove());
                if (message.getText().equals("/start")) {
                    SendMessage start = TextMessageHandler.getStart(update);
                    execute(start);
                }else if (message.getText().equals("/choose")){
                    SendMessage sendMessage1 = new SendMessage();
                    sendMessage1.setReplyMarkup(TextMessageHandler.setKeyboardChoosingLanguage());
                    sendMessage1.setChatId(update.getMessage().getChatId());
                    sendMessage1.setText("Tanlang");
                    execute(sendMessage1);
                }else {
                    if (message.getText().equals(TextMessageHandler.CONTEXT.get(0))) {
                        fromLang = "uz";
                        toLang = "en";
                        Botstate = "Input";
                        sendMessage.setText("O'zbek "+text);
                        execute(sendMessage);
                    } else if (message.getText().equals(TextMessageHandler.CONTEXT.get(1))) {
                        fromLang = "en";
                        toLang = "uz";
                        Botstate = "Input";
                        sendMessage.setText("Inliz "+text);
                        execute(sendMessage);
                    } else if (message.getText().equals(TextMessageHandler.CONTEXT.get(2))) {
                        fromLang = "uz";
                        toLang = "ru";
                        Botstate = "Input";
                        sendMessage.setText("O'zbek "+text);
                        execute(sendMessage);
                    } else if (message.getText().equals(TextMessageHandler.CONTEXT.get(3))) {
                        fromLang = "ru";
                        toLang = "uz";
                        Botstate = "Input";
                        sendMessage.setText("Rus "+text);
                        execute(sendMessage);
                    } else if (message.getText().equals(TextMessageHandler.CONTEXT.get(4))) {
                        fromLang = "en";
                        toLang = "ru";
                        Botstate = "Input";
                        sendMessage.setText("Ingliz "+text);
                        execute(sendMessage);
                    } else if (message.getText().equals(TextMessageHandler.CONTEXT.get(5))) {
                        fromLang = "ru";
                        toLang = "en";
                        Botstate = "Input";
                        sendMessage.setText("Rus "+text);
                        execute(sendMessage);
                    }else if (Botstate.equals("Input")) {

                        Translator translator = new Translator();

                        String textTranslated = message.getText();
                        String responseString = translator.translate(fromLang, toLang, textTranslated);
                        SendMessage translated = TextMessageHandler.sendResponse(responseString, update);
                        execute(translated);

                    }
                }

            } catch (TelegramApiException | IOException e) {
                e.printStackTrace();
            }catch (URISyntaxException e) {
                e.printStackTrace();
            }

        }else if (update.hasCallbackQuery()){
            CallbackQuery callbackQuery = update.getCallbackQuery();
            if (callbackQuery.getData().equals("Choose")){
                SendMessage start = new SendMessage()
                        .setText("Tanlang")
                        .setChatId(callbackQuery.getMessage().getChatId())
                        .setReplyMarkup(TextMessageHandler.setKeyboardChoosingLanguage());

                if (start!=null){
                    try {
                        execute(start);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "@tarjimonamakibot";
    }

    @Override
    public String getBotToken() {
        return "1972804388:AAFRpiqpErfcTS2-jSU7wjnwhcqvRTfQHOE";
    }
}
