package MessageHandler;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class TextMessageHandler {
    public static ArrayList<String> CONTEXT = new ArrayList<>();
    public static SendMessage getStart(Update update) {
        Long chatId = null;
        if (update.hasCallbackQuery()){
            chatId = update.getCallbackQuery().getMessage().getChatId();
        }else if (update.hasMessage()){
            chatId = update.getMessage().getChatId();
        }
        if (chatId!=null){
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            sendMessage.setReplyToMessageId(update.getMessage().getMessageId());
            sendMessage.setText("Assalomu alaykum , <b>Tarjimon Amakidan</b> foydalanish uchun tilni tanlang");
            sendMessage.setParseMode(ParseMode.HTML);
            sendMessage.setReplyMarkup(setKeyboardChoosingLanguage());
            return sendMessage;
        }
        return null;
    }

    public static ReplyKeyboard setKeyboardChoosingLanguage() {
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);

        KeyboardRow keyboardButtons = new KeyboardRow();
        keyboardButtons.add("\uD83C\uDDFA\uD83C\uDDFF Uzbek  English \uD83C\uDDFA\uD83C\uDDF8");
        keyboardButtons.add("\uD83C\uDDFA\uD83C\uDDF8 English  Uzbek \uD83C\uDDFA\uD83C\uDDFF");
        keyboardRowList.add(keyboardButtons);

        keyboardButtons = new KeyboardRow();
        keyboardButtons.add("\uD83C\uDDFA\uD83C\uDDFF Uzbek  Russian \uD83C\uDDF7\uD83C\uDDFA");
        keyboardButtons.add("\uD83C\uDDF7\uD83C\uDDFA Russian  Uzbek \uD83C\uDDFA\uD83C\uDDFF");
        keyboardRowList.add(keyboardButtons);

        keyboardButtons = new KeyboardRow();
        keyboardButtons.add("\uD83C\uDDFA\uD83C\uDDF8 English  Russian \uD83C\uDDF7\uD83C\uDDFA");
        keyboardButtons.add("\uD83C\uDDF7\uD83C\uDDFA Russian  English \uD83C\uDDFA\uD83C\uDDF8");
        keyboardRowList.add(keyboardButtons);

        CONTEXT.add("\uD83C\uDDFA\uD83C\uDDFF Uzbek  English \uD83C\uDDFA\uD83C\uDDF8");
        CONTEXT.add("\uD83C\uDDFA\uD83C\uDDF8 English  Uzbek \uD83C\uDDFA\uD83C\uDDFF");
        CONTEXT.add("\uD83C\uDDFA\uD83C\uDDFF Uzbek  Russian \uD83C\uDDF7\uD83C\uDDFA");
        CONTEXT.add("\uD83C\uDDF7\uD83C\uDDFA Russian  Uzbek \uD83C\uDDFA\uD83C\uDDFF");
        CONTEXT.add("\uD83C\uDDFA\uD83C\uDDF8 English  Russian \uD83C\uDDF7\uD83C\uDDFA");
        CONTEXT.add("\uD83C\uDDF7\uD83C\uDDFA Russian  English \uD83C\uDDFA\uD83C\uDDF8");

        replyKeyboardMarkup.setKeyboard(keyboardRowList);
        return replyKeyboardMarkup;

    }

    public static SendMessage sendResponse(String responseString, Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());
        sendMessage.setText(responseString);
        sendMessage.setReplyMarkup(new ReplyKeyboardRemove());
        sendMessage.setReplyMarkup(getChooseInlineMarkup());
        sendMessage.setParseMode(ParseMode.MARKDOWN);
        return sendMessage;
    }

    private static ReplyKeyboard getChooseInlineMarkup() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText("Til tanlash");
        inlineKeyboardButton.setCallbackData("Choose");
        List<InlineKeyboardButton> buttonRow = new ArrayList<>();
        buttonRow.add(inlineKeyboardButton);
        List<List<InlineKeyboardButton>> buttonCol = new ArrayList<>();
        buttonCol.add(buttonRow);
        inlineKeyboardMarkup.setKeyboard(buttonCol);
        return inlineKeyboardMarkup;

    }

}
