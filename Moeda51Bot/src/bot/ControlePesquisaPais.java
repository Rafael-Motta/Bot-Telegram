package bot;

import com.pengrad.telegrambot.model.Update;

public class ControlePesquisaPais implements ControlePesquisa{

	private Raspa raspa;
	private Tela tela;
	
	public ControlePesquisaPais(Raspa raspa, Tela tela){
		this.raspa = raspa; 
		this.tela = tela; 
	}
	
	public void pesquisa(Update update){
		tela.sendTypingMessage(update);
		raspa.procurandoPais(update);
	}
}
