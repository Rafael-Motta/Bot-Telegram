package bot;

import java.util.ArrayList;

import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.NotFound;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;

public class Raspa {
	
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
	
	public String procurandoPais(String pais) {
		
		for(String s : lista) {
			if(s.toLowerCase().replace(" ", "").equals(pais.toLowerCase().replace(" ", ""))) {
				return "Preço de compra: "+lista.get((lista.indexOf(s)) + 2)+"\n"+
						"Preço de venda: "+lista.get((lista.indexOf(s)) + 3);
			}
			else if ((lista.indexOf(s)) == (lista.size() -4)) {
				return "Não foi possivel encontrar o País.";
			}
		}	
		return "";
	}
	
	public String procurandoMoeda(String moeda) {
		
		for(String s : lista) {
			if((s.toLowerCase().replace(" ", "")
					.equals(moeda.toLowerCase().replace(" ", "")))) {
				return "Preço de compra: "+lista.get((lista.indexOf(s)) + 1)+"\n"+
						"Preço de venda: "+lista.get((lista.indexOf(s)) + 2);
			}
			else if (lista.indexOf(s) == (lista.size() -3)) {
				return "Não foi possivel encontrar a Moeda.";
			}
		}	
		return "";
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
