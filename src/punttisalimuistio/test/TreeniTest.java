package punttisalimuistio.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import punttisalimuistio.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2023.06.03 16:43:09 // Generated by ComTest
 *
 */
@SuppressWarnings("all")
public class TreeniTest {


  // Generated by ComTest BEGIN
  /** testGetPvm33 */
  @Test
  public void testGetPvm33() {    // Treeni: 33
    Treeni tre = new Treeni(); 
    tre.vastaaTreeni(); 
    { String _l_=tre.getPvm(),_r_="12.06.2023"; if ( !_l_.matches(_r_) ) fail("From: Treeni line: 36" + " does not match: ["+ _l_ + "] != [" + _r_ + "]");}; 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testRekisteroi82 */
  @Test
  public void testRekisteroi82() {    // Treeni: 82
    Treeni tre1 = new Treeni(); 
    assertEquals("From: Treeni line: 84", 0, tre1.getTunnusNro()); 
    tre1.rekisteroi(); 
    Treeni tre2 = new Treeni(); 
    tre2.rekisteroi(); 
    int n1 = tre1.getTunnusNro(); 
    int n2 = tre2.getTunnusNro(); 
    assertEquals("From: Treeni line: 90", n2-1, n1); 
  } // Generated by ComTest END
}