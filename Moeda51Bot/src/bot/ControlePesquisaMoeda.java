package bot;

import com.pengrad.telegrambot.model.Update;

public class ControlePesquisaMoeda implements ControlePesquisa{

	private Raspa raspa;
	private Tela tela;
	
	public ControlePesquisaMoeda(Raspa raspa, Tela tela){
		this.raspa = raspa; 
		this.tela = tela; 
	}
	
	public void pesquisa(Update update){
		tela.sendTypingMessage(update);
		raspa.pesquisaMoeda(update);
	}
	
}
