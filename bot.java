import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.github.gr1228.chatgpt.ChatGPTClient;

public class Bot extends TelegramLongPollingBot {

    private ChatGPTClient chatGPTClient;

    public Bot() {
        this.chatGPTClient = new ChatGPTClient("<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>");
    }

    @Override
    public String getBotUsername() {
        return "<@Ravan_telegraph_bot>";
    }

    @Override
    public String getBotToken() {
        return "<1612851329:AAHrHzGcJ_X0zeVilazgMrlDoECI_qyBJJI>";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();

            String userMessage = message.getText();
            String chatGPTResponse = chatGPTClient.generateText(userMessage);

            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId());
            sendMessage.setText(chatGPTResponse);

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ApiContextInitializer.init();

        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

        Bot bot = new Bot();

        try {
            telegramBotsApi.registerBot(bot);
            System.out.println("Bot started successfully!");
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
