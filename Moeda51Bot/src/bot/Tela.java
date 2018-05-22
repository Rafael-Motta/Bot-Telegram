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

public class Tela implements Observador {
	
	TelegramBot bot = TelegramBotAdapter.build("571250573:AAHyb-6Ia-Dn80Reluq23nxBrS_yERZ7Pmo");
	
	GetUpdatesResponse updatesResponse;
	SendResponse sendResponse;
	BaseResponse baseResponse;
	int msg = 0;

	ControlePesquisa controlePesquisa;
	
	boolean searchBehaviour = false;
	
	private Raspa raspa;
	
	public Tela(Raspa raspa){
		this.raspa = raspa; 
	}
	
	public void setControlePesquisa(ControlePesquisa controlePesquisa){
		this.controlePesquisa = controlePesquisa;
	}
	
	public void receiveUsersMessages() {
		
		//infinity loop
		while (true){
		
			//taking the Queue of Messages
			updatesResponse =  bot.execute(new GetUpdates().limit(100).offset(msg));
			
			//Queue of messages
			List<Update> updates = updatesResponse.updates();

			//taking each message in the Queue
			for (Update update : updates) {
				
				//updating queue's index
				msg = update.updateId()+1;
				
				if(this.searchBehaviour==true){
					this.callController(update);
					
				}else if(update.message().text().equals("MOEDA")){
					setControlePesquisa(new ControlePesquisaMoeda(raspa, this));
					sendResponse = bot.execute(new SendMessage(update.message().chat().id(),"what's the student name?"));
					this.searchBehaviour = true;
					
				} else if(update.message().text().equals("PAIS")){
					setControlePesquisa(new ControlePesquisaPais(raspa, this));
					sendResponse = bot.execute(new SendMessage(update.message().chat().id(),"what's the teacher name?"));
					this.searchBehaviour = true;
					
				} else {
					sendResponse = bot.execute(new SendMessage(update.message().chat().id(),"Type teacher or student"));
				}
				
				
				
			}

		}
		
		
	}
	
	
	public void callController(Update update){
		this.controlePesquisa.pesquisa(update);
	}
	
	public void update(long chatId, String moedaDados){
		sendResponse = bot.execute(new SendMessage(chatId, moedaDados));
		this.searchBehaviour = false;
	}
	
	public void sendTypingMessage(Update update){
		baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
	}


}
