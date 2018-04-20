package bot;



public class MainTest {

	public static void main(String[] args) {
				
		Raspa r = new Raspa();
		
		r.raspando();
		
		System.out.println(r.procurandoPais("austria"));
		System.out.println(r.procurandoMoeda("dolar"));
		System.out.println(r.procurandoPadrao("dolar"));
		
		
	}
}
