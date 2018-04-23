package bot;

import java.util.List;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;

public class MainTest {

	public static void main(String[] args) throws InterruptedException {
		TelegramBot bot = TelegramBotAdapter.build("571250573:AAHyb-6Ia-Dn80Reluq23nxBrS_yERZ7Pmo");
		
		GetUpdatesResponse updatesResponse;
		SendResponse sendResponse;
		BaseResponse baseResponse;
		int msg = 0;
		
		Raspa raspa = new Raspa();
		raspa.raspando();
		
		
		while(true) {
			updatesResponse = bot.execute(new GetUpdates().limit(100).offset(msg));
			List<Update> updates = updatesResponse.updates();
			
			for(Update update : updates) {
				msg = update.updateId()+1;
				
				baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
				Thread.sleep(500);
				
				sendResponse = bot.execute(new SendMessage(update.message().chat().id(), raspa.procurandoPais(update.message().text())));
				
			}
		}
	}
}
