import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioBD implements UsuarioDAO {

	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void inserir(Usuario u) {

		try (Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/coursera", "postgres",
				"postgres")) {

			String sql = "INSERT INTO usuario(login, email, nome, senha, pontos) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement stm = c.prepareStatement(sql);

			stm.setString(1, u.getLogin());
			stm.setString(2, u.getEmail());
			stm.setString(3, u.getNome());
			stm.setString(4, u.getSenha());
			stm.setInt(5, u.getPontos());
			stm.executeUpdate();
			stm.close();

		} catch (SQLException e) {
			throw new RuntimeException("Não foi possivel executar o acesso", e);
		}

	}

	@Override
	public Usuario recuperar(String login) {

		Usuario u = new Usuario();

		try (Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/coursera", "postgres",
				"postgres")) {

			String sql = "SELECT * FROM usuario WHERE login = ?";
			PreparedStatement stm = c.prepareStatement(sql);
			stm.setString(1, login);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				u.setLogin(rs.getString("login"));
				u.setNome(rs.getString("nome"));
				u.setEmail(rs.getString("email"));
				u.setSenha(rs.getString("senha"));
				u.setPontos(rs.getInt("pontos"));
			}
			rs.close();
			stm.close();

		} catch (SQLException e) {
			throw new RuntimeException("Não foi possivel executar o acesso", e);
		}
		return u;
	}

	@Override
	public void adicionarPontos(String login, int pontos) {

		// Usuario u = recuperar(login);

		try (Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/coursera", "postgres",
				"postgres")) {

			String sql = "UPDATE usuario SET pontos = pontos + ? WHERE login = ?";
			PreparedStatement stm = c.prepareStatement(sql);
			stm.setString(2, login);
			stm.setInt(1, pontos);
			stm.executeUpdate();
			stm.close();

		} catch (SQLException e) {
			throw new RuntimeException("Não foi possivel executar o acesso", e);
		}

	}

	@Override
	public List<Usuario> ranking() {

		List<Usuario> rank = new ArrayList<>();

		try (Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/usuarios", "postgres",
				"postgres")) {

			String sql = "SELECT * FROM usuario";
			PreparedStatement stm = c.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Usuario u = new Usuario();
				u.setLogin(rs.getString("login"));
				u.setNome(rs.getString("nome"));
				u.setEmail(rs.getString("email"));
				u.setSenha(rs.getString("senha"));
				u.setPontos(rs.getInt("pontos"));
				
				rank.add(u);
			}

		} catch (SQLException e) {
			throw new RuntimeException("Não foi possivel executar o acesso", e);
		}

		return rank;
	}

}
