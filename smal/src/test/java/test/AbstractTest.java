package test;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import javax.naming.InitialContext;

public abstract class AbstractTest {

	@BeforeClass
	public static void setUpClass() throws Exception {
	}

	@AfterClass
	public static void tearDownClass() throws Exception {

		InitialContext ic = new InitialContext();

		ic.unbind("java:comp/env/jdbc/tutorialDS");
	}
}
