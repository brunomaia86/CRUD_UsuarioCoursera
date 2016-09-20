import static org.junit.Assert.*;

import org.dbunit.JdbcDatabaseTester;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.Before;
import org.junit.Test;

public class TesteUsuarioBD {
	
	JdbcDatabaseTester jdt;

	@Before
	public void setUp() throws Exception {
		
		jdt = new JdbcDatabaseTester("org.postgresql.Driver", "jdbc:postgresql://localhost:5432/coursera", "postgres",
				"postgres");
		FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
		jdt.setDataSet(loader.load("/inicio.xml"));
		jdt.onSetup();
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
