package security;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.TestCase;

public class Test_Encrypt extends TestCase {

	@Test
	public void test() {
		Encrypt myEncrypt = new Encrypt("ABCDQRSTAB","0000000000","bacdefgh");
		assertEquals(myEncrypt.getChiper(), "qrstbacdqr");
	}

}
