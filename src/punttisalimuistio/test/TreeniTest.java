package punttisalimuistio.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import punttisalimuistio.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2023.06.06 17:37:11 // Generated by ComTest
 *
 */
@SuppressWarnings("all")
public class TreeniTest {



  // Generated by ComTest BEGIN
  /** testGetTunnusNro42 */
  @Test
  public void testGetTunnusNro42() {    // Treeni: 42
    Treeni tre1 = new Treeni(); 
    assertEquals("From: Treeni line: 44", 0, tre1.getTunnusNro()); 
    tre1.rekisteroi(); 
    assertEquals("From: Treeni line: 46", 1, tre1.getTunnusNro()); 
    Treeni tre2 = new Treeni(); 
    tre2.rekisteroi(); 
    assertEquals("From: Treeni line: 50", 2, tre2.getTunnusNro()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetPvm61 */
  @Test
  public void testGetPvm61() {    // Treeni: 61
    Treeni tre = new Treeni(); 
    assertEquals("From: Treeni line: 63", "", tre.getPvm()); 
    tre.taytaTreeni(); 
    assertEquals("From: Treeni line: 65", "12.06.2023", tre.getPvm()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testTaytaTreeni76 */
  @Test
  public void testTaytaTreeni76() {    // Treeni: 76
    Treeni tre = new Treeni(); 
    assertEquals("From: Treeni line: 78", "", tre.getPvm()); 
    tre.taytaTreeni(); 
    assertEquals("From: Treeni line: 80", "12.06.2023", tre.getPvm()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testRekisteroi118 */
  @Test
  public void testRekisteroi118() {    // Treeni: 118
    Treeni tre1 = new Treeni(); 
    assertEquals("From: Treeni line: 120", 0, tre1.getTunnusNro()); 
    tre1.rekisteroi(); 
    assertEquals("From: Treeni line: 122", 3, tre1.getTunnusNro()); 
    Treeni tre2 = new Treeni(); 
    assertEquals("From: Treeni line: 125", 0, tre2.getTunnusNro()); 
    tre2.rekisteroi(); 
    assertEquals("From: Treeni line: 127", 4, tre2.getTunnusNro()); 
    int n1 = tre1.getTunnusNro(); 
    int n2 = tre2.getTunnusNro(); 
    assertEquals("From: Treeni line: 131", n2-1, n1); 
  } // Generated by ComTest END
}