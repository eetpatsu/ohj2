package punttisalimuistio.test;
// Generated by ComTest BEGIN
import punttisalimuistio.*;
import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2023.06.08 15:56:29 // Generated by ComTest
 *
 */
@SuppressWarnings("all")
public class LiikkeetTest {



  // Generated by ComTest BEGIN
  /** testIterator86 */
  @Test
  public void testIterator86() {    // Liikkeet: 86
    Liikkeet liikkeet = new Liikkeet(); 
    Liike lii21 = new Liike(2); liikkeet.lisaa(lii21); 
    Liike lii11 = new Liike(1); liikkeet.lisaa(lii11); 
    Liike lii22 = new Liike(2); liikkeet.lisaa(lii22); 
    Liike lii12 = new Liike(1); liikkeet.lisaa(lii12); 
    Liike lii23 = new Liike(2); liikkeet.lisaa(lii23); 
    Iterator<Liike> i2=liikkeet.iterator(); 
    assertEquals("From: Liikkeet line: 97", lii21, i2.next()); 
    assertEquals("From: Liikkeet line: 98", lii11, i2.next()); 
    assertEquals("From: Liikkeet line: 99", lii22, i2.next()); 
    assertEquals("From: Liikkeet line: 100", lii12, i2.next()); 
    assertEquals("From: Liikkeet line: 101", lii23, i2.next()); 
    try {
    assertEquals("From: Liikkeet line: 102", lii12, i2.next()); 
    fail("Liikkeet: 102 Did not throw NoSuchElementException");
    } catch(NoSuchElementException _e_){ _e_.getMessage(); }
    int n = 0; 
    int tnrot[] = { 2,1,2,1,2} ; 
    for ( Liike lii:liikkeet ) {
    assertEquals("From: Liikkeet line: 108", tnrot[n], lii.getTreeniNro()); n++; 
    }
    assertEquals("From: Liikkeet line: 110", 5, n); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAnnaLiikkeet124 */
  @Test
  public void testAnnaLiikkeet124() {    // Liikkeet: 124
    Liikkeet liikkeet = new Liikkeet(); 
    Liike lii21 = new Liike(2); liikkeet.lisaa(lii21); 
    Liike lii11 = new Liike(1); liikkeet.lisaa(lii11); 
    Liike lii22 = new Liike(2); liikkeet.lisaa(lii22); 
    Liike lii12 = new Liike(1); liikkeet.lisaa(lii12); 
    Liike lii23 = new Liike(2); liikkeet.lisaa(lii23); 
    Liike lii51 = new Liike(5); liikkeet.lisaa(lii51); 
    List<Liike> loytyneet; 
    loytyneet = liikkeet.annaLiikkeet(3); 
    assertEquals("From: Liikkeet line: 136", 0, loytyneet.size()); 
    loytyneet = liikkeet.annaLiikkeet(1); 
    assertEquals("From: Liikkeet line: 138", 2, loytyneet.size()); 
    assertEquals("From: Liikkeet line: 139", true, loytyneet.get(0) == lii11); 
    assertEquals("From: Liikkeet line: 140", true, loytyneet.get(1) == lii12); 
    loytyneet = liikkeet.annaLiikkeet(5); 
    assertEquals("From: Liikkeet line: 142", 1, loytyneet.size()); 
    assertEquals("From: Liikkeet line: 143", true, loytyneet.get(0) == lii51); 
  } // Generated by ComTest END
}