package bot;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.NotFound;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;
import com.pengrad.telegrambot.model.Update;

public class Raspa implements Sujeito {
	
	private List<Observador> observadores = new LinkedList<Observador>();
	
	public void registerObserver(Observador observador){
		observadores.add(observador);
	}
	
	public void notifyObservers(long chatId, String moedaData){
		for(Observador observador:observadores){
			observador.update(chatId, moedaData);
		}
	}
	
	private ArrayList<String> lista = new ArrayList<String>();
	
	public void raspando() {
	
		try {
			UserAgent userAgent = new UserAgent();
			userAgent.visit("https://financeone.com.br/moedas/cotacoes-do-real-e-outras-moedas/");     
			Elements links = userAgent.doc.findFirst("<table>").findEach("<tr>").findEach("<td>");
		
			for(Element link : links) {
				lista.add(link.getText());
			}		
		}
		catch(ResponseException re) {
			System.err.println(re);
			re.printStackTrace();
		} catch (NotFound nf) {
			System.err.println(nf);
			nf.printStackTrace();
		}
	}
	
	/*public void searchStudent(Update update){
		String studentsData = null;
		for(Student student: students){
			if(student.getName().equals(update.message().text())){
				studentsData = student.getAcademicNumber();
			}
		}
		
		if(studentsData != null){
			this.notifyObservers(update.message().chat().id(), studentsData);
		} else {
			this.notifyObservers(update.message().chat().id(), "Student not found");
		}
		
	}
	
	public void searchTeacher(Update update){
		String teachersData = null;
		for(Teacher teacher:teachers){
			if(teacher.getName().equals(update.message().text())) teachersData = teacher.getField();
		}
		
		if(teachersData != null){
			this.notifyObservers(update.message().chat().id(), teachersData);
		} else {
			this.notifyObservers(update.message().chat().id(), "Teacher not found");
		}
		
	}*/
	
	public void procurandoPais(Update update) {
		String paisDados = null;
		for(String s : lista) {
			if(s.toLowerCase().replace(" ", "").equals(update.message().text().toLowerCase().replace(" ", ""))) {
				paisDados = "Preço de compra: "+lista.get((lista.indexOf(s)) + 2)+"\n"+
						"Preço de venda: "+lista.get((lista.indexOf(s)) + 3);
			}
			else if ((lista.indexOf(s)) == (lista.size() -4)) {
				paisDados = "Não foi possivel encontrar o País.";
			}
		}	
		
	}
	
	public void procurandoMoeda(Update update) {
		String moedaDados = null;
		for(String s : lista) {
			if((s.toLowerCase().replace(" ", "")
					.equals(update.message().text().toLowerCase().replace(" ", "")))) {
				moedaDados = "Preço de compra: "+lista.get((lista.indexOf(s)) + 1)+"\n"+
						"Preço de venda: "+lista.get((lista.indexOf(s)) + 2);
			}
			else if (lista.indexOf(s) == (lista.size() -3)) {
				moedaDados = "Não foi possivel encontrar a Moeda.";
			}
		}	
	}
	
	public String procurandoPadrao(String moeda) {
		
			for(String s : lista) {
				if(lista.get(lista.indexOf(s) -1) == "Estados Unidos") {
					return "Preço de compra: "+lista.get((lista.indexOf(s)) + 1)+"\n"+
							"Preço de venda: "+lista.get((lista.indexOf(s)) + 2);
				}
			}
		
		
		if("moeda".toLowerCase().replace(" ", "") == "libra") {
			for(String s : lista) {
				if(s == "Reino Unido") {
					return "Preço de compra: "+lista.get((lista.indexOf(s)) + 1)+"\n"+
							"Preço de venda: "+lista.get((lista.indexOf(s)) + 2);
				}
			}
		}
		
		if("moeda".toLowerCase().replace(" ", "") == "euro") {
			for(String s : lista) {
				if(s == "Comunidade Europeia") {
					return "Preço de compra: "+lista.get((lista.indexOf(s)) + 1)+"\n"+
							"Preço de venda: "+lista.get((lista.indexOf(s)) + 2);
				}
			}
		}

		return "";
	}	
}
