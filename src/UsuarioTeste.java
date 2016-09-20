import java.util.List;

public class UsuarioTeste {

	public static void main(String[] args) {

		Usuario u1 = new Usuario("bruno", "brunomaia@gmail.com", "Bruno Maia", "123456", 8);
		Usuario u2 = new Usuario("larissa", "larissalucena@gmail.com", "Larissa Lucena", "654321", 10);
		Usuario u3 = new Usuario("pedrita", "pedritalucena@gmail.com", "Pedrita Lucena", "987654", 3);
		
		UsuarioBD bd = new UsuarioBD();
		//bd.inserir(u1);
		//bd.inserir(u2);
		//bd.inserir(u3);
		
		//System.out.println(bd.recuperar("larissa"));
		
		//bd.adicionarPontos("pedrita", 5);
		
		//System.out.println(bd.recuperar("pedrita"));
		
		List<Usuario> rank = bd.ranking();
		rank.forEach(System.out::println);
	}

}
