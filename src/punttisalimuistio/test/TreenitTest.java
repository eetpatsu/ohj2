package punttisalimuistio.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import punttisalimuistio.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2023.06.06 17:37:00 // Generated by ComTest
 *
 */
@SuppressWarnings("all")
public class TreenitTest {



  // Generated by ComTest BEGIN
  /** 
   * testLisaa56 
   * @throws SailoException when error
   */
  @Test
  public void testLisaa56() throws SailoException {    // Treenit: 56
    Treenit treenit = new Treenit(); 
    Treeni tre1 = new Treeni(); 
    Treeni tre2 = new Treeni(); 
    assertEquals("From: Treenit line: 62", 0, treenit.getLkm()); 
    treenit.lisaa(tre1); assertEquals("From: Treenit line: 63", 1, treenit.getLkm()); 
    treenit.lisaa(tre2); assertEquals("From: Treenit line: 64", 2, treenit.getLkm()); 
    treenit.lisaa(tre1); assertEquals("From: Treenit line: 65", 3, treenit.getLkm()); 
    assertEquals("From: Treenit line: 67", tre1, treenit.anna(0)); 
    assertEquals("From: Treenit line: 68", tre2, treenit.anna(1)); 
    assertEquals("From: Treenit line: 69", tre1, treenit.anna(2)); 
    assertEquals("From: Treenit line: 70", false, treenit.anna(1) == tre1); 
    assertEquals("From: Treenit line: 71", true, treenit.anna(1) == tre2); 
    try {
    assertEquals("From: Treenit line: 72", tre1, treenit.anna(3)); 
    fail("Treenit: 72 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
    treenit.lisaa(tre1); assertEquals("From: Treenit line: 74", 4, treenit.getLkm()); 
    treenit.lisaa(tre1); assertEquals("From: Treenit line: 75", 5, treenit.getLkm()); 
    treenit.lisaa(tre1); treenit.lisaa(tre2); 
    treenit.lisaa(tre1); treenit.lisaa(tre2); 
    treenit.lisaa(tre1); treenit.lisaa(tre2); 
    treenit.lisaa(tre1); 
    assertEquals("From: Treenit line: 81", 12, treenit.getLkm()); 
    try {
    treenit.lisaa(tre2); 
    fail("Treenit: 82 Did not throw SailoException");
    } catch(SailoException _e_){ _e_.getMessage(); }
    assertEquals("From: Treenit line: 83", 12, treenit.getLkm()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testAnna99 
   * @throws SailoException when error
   */
  @Test
  public void testAnna99() throws SailoException {    // Treenit: 99
    Treenit treenit = new Treenit(); 
    Treeni tre1 = new Treeni(); 
    Treeni tre2 = new Treeni(); 
    assertEquals("From: Treenit line: 105", 0, treenit.getLkm()); 
    treenit.lisaa(tre1); assertEquals("From: Treenit line: 106", 1, treenit.getLkm()); 
    treenit.lisaa(tre2); assertEquals("From: Treenit line: 107", 2, treenit.getLkm()); 
    treenit.lisaa(tre1); assertEquals("From: Treenit line: 108", 3, treenit.getLkm()); 
    assertEquals("From: Treenit line: 110", tre1, treenit.anna(0)); 
    assertEquals("From: Treenit line: 111", tre2, treenit.anna(1)); 
    assertEquals("From: Treenit line: 112", tre1, treenit.anna(2)); 
    assertEquals("From: Treenit line: 113", false, treenit.anna(1) == tre1); 
    assertEquals("From: Treenit line: 114", true, treenit.anna(1) == tre2); 
    try {
    assertEquals("From: Treenit line: 115", tre1, treenit.anna(3)); 
    fail("Treenit: 115 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
  } // Generated by ComTest END
}