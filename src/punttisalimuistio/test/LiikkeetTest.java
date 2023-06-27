package punttisalimuistio.test;
// Generated by ComTest BEGIN
import java.io.File;
import punttisalimuistio.*;
import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2023.06.27 17:26:42 // Generated by ComTest
 *
 */
@SuppressWarnings("all")
public class LiikkeetTest {



  // Generated by ComTest BEGIN
  /** testGetLkm42 */
  @Test
  public void testGetLkm42() {    // Liikkeet: 42
    Liikkeet liikkeet = new Liikkeet(); 
    Liike lii1T1 = new Liike(1); 
    assertEquals("From: Liikkeet line: 45", 0, liikkeet.getLkm()); 
    lii1T1.rekisteroi(); 
    liikkeet.lisaa(lii1T1); 
    assertEquals("From: Liikkeet line: 48", 1, liikkeet.getLkm()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testLisaa60 */
  @Test
  public void testLisaa60() {    // Liikkeet: 60
    Liikkeet liikkeet = new Liikkeet(); 
    Liike lii1T1 = new Liike(1); 
    Liike lii2T1 = new Liike(1); 
    assertEquals("From: Liikkeet line: 64", 0, liikkeet.getLkm()); 
    lii1T1.rekisteroi(); 
    liikkeet.lisaa(lii1T1); 
    assertEquals("From: Liikkeet line: 67", 1, liikkeet.getLkm()); 
    lii2T1.rekisteroi(); 
    liikkeet.lisaa(lii2T1); 
    assertEquals("From: Liikkeet line: 70", 2, liikkeet.getLkm()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testKorvaaTaiLisaa84 
   * @throws CloneNotSupportedException when error
   */
  @Test
  public void testKorvaaTaiLisaa84() throws CloneNotSupportedException {    // Liikkeet: 84
    Liikkeet liikkeet = new Liikkeet(); 
    Liike lii1T1 = new Liike(); 
    lii1T1.rekisteroi(); 
    lii1T1.taytaLiike(1); 
    liikkeet.lisaa(lii1T1); 
    Liike lii2T1 = new Liike(); 
    lii2T1.rekisteroi(); 
    lii2T1.taytaLiike(1); 
    liikkeet.lisaa(lii2T1); 
    liikkeet.korvaaTaiLisaa(lii1T1); 
    liikkeet.korvaaTaiLisaa(lii2T1); 
    assertEquals("From: Liikkeet line: 98", 2, liikkeet.getLkm()); 
    Liike klooni = lii1T1.clone(); 
    assertEquals("From: Liikkeet line: 100", true, lii1T1.toString().equals(klooni.toString())); 
    liikkeet.korvaaTaiLisaa(klooni); 
    assertEquals("From: Liikkeet line: 102", 2, liikkeet.getLkm()); 
    lii1T1.parse("2|1|kyykky|80|3|5"); 
    assertEquals("From: Liikkeet line: 104", false, lii1T1.toString().equals(klooni.toString())); 
    liikkeet.korvaaTaiLisaa(lii1T1); 
    assertEquals("From: Liikkeet line: 106", 3, liikkeet.getLkm()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testPoista127 
   * @throws SailoException when error
   */
  @Test
  public void testPoista127() throws SailoException {    // Liikkeet: 127
    Liikkeet liikkeet = new Liikkeet(); 
    Liike lii1T1 = new Liike(); lii1T1.taytaLiike(1); 
    Liike lii2T1 = new Liike(); lii2T1.taytaLiike(1); 
    Liike lii3T2 = new Liike(); lii3T2.taytaLiike(2); 
    Liike lii4T2 = new Liike(); lii4T2.taytaLiike(2); 
    Liike lii5T2 = new Liike(); lii5T2.taytaLiike(2); 
    liikkeet.lisaa(lii1T1); 
    liikkeet.lisaa(lii2T1); 
    liikkeet.lisaa(lii3T2); 
    liikkeet.lisaa(lii4T2); 
    assertEquals("From: Liikkeet line: 140", false, liikkeet.poista(lii5T2)); assertEquals("From: Liikkeet line: 140", 4, liikkeet.getLkm()); 
    assertEquals("From: Liikkeet line: 141", true, liikkeet.poista(lii1T1)); assertEquals("From: Liikkeet line: 141", 3, liikkeet.getLkm()); 
    List<Liike> liikkeet1 = liikkeet.anna(1); 
    assertEquals("From: Liikkeet line: 143", 1, liikkeet1.size()); 
    assertEquals("From: Liikkeet line: 144", lii2T1, liikkeet1.get(0)); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testPoista160 */
  @Test
  public void testPoista160() {    // Liikkeet: 160
    Liikkeet liikkeet = new Liikkeet(); 
    Liike lii1T1 = new Liike(); lii1T1.taytaLiike(1); 
    Liike lii2T1 = new Liike(); lii2T1.taytaLiike(1); 
    Liike lii3T2 = new Liike(); lii3T2.taytaLiike(2); 
    Liike lii4T2 = new Liike(); lii4T2.taytaLiike(2); 
    Liike lii5T2 = new Liike(); lii5T2.taytaLiike(2); 
    liikkeet.lisaa(lii1T1); 
    liikkeet.lisaa(lii2T1); 
    liikkeet.lisaa(lii3T2); 
    liikkeet.lisaa(lii4T2); 
    liikkeet.lisaa(lii5T2); 
    assertEquals("From: Liikkeet line: 172", 3, liikkeet.poista(2)); assertEquals("From: Liikkeet line: 172", 2, liikkeet.getLkm()); 
    assertEquals("From: Liikkeet line: 173", 0, liikkeet.poista(3)); assertEquals("From: Liikkeet line: 173", 2, liikkeet.getLkm()); 
    List<Liike> liikkeet2 = liikkeet.anna(2); 
    assertEquals("From: Liikkeet line: 175", 0, liikkeet2.size()); 
    liikkeet2 = liikkeet.anna(1); 
    assertEquals("From: Liikkeet line: 177", lii1T1, liikkeet2.get(0)); 
    assertEquals("From: Liikkeet line: 178", lii2T1, liikkeet2.get(1)); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testLueTiedostosta200 
   * @throws SailoException when error
   */
  @Test
  public void testLueTiedostosta200() throws SailoException {    // Liikkeet: 200
    Liikkeet liikkeet = new Liikkeet(); 
    Liike lii1T1 = new Liike(); lii1T1.taytaLiike(1); 
    Liike lii2T1 = new Liike(); lii2T1.taytaLiike(1); 
    Liike lii3T2 = new Liike(); lii3T2.taytaLiike(2); 
    Liike lii4T2 = new Liike(); lii4T2.taytaLiike(2); 
    Liike lii5T2 = new Liike(); lii5T2.taytaLiike(2); 
    String nimi = "testi"; 
    File tiedosto = new File(nimi + "/liikkeet.dat"); 
    File dir = new File(nimi); 
    dir.mkdir(); 
    tiedosto.delete(); 
    liikkeet.lisaa(lii1T1); 
    liikkeet.lisaa(lii2T1); 
    liikkeet.lisaa(lii3T2); 
    liikkeet.lisaa(lii4T2); 
    liikkeet.lisaa(lii5T2); 
    liikkeet.talleta(nimi); 
    Iterator<Liike> i = liikkeet.iterator(); 
    assertEquals("From: Liikkeet line: 221", lii1T1.toString(), i.next().toString()); 
    assertEquals("From: Liikkeet line: 222", lii2T1.toString(), i.next().toString()); 
    assertEquals("From: Liikkeet line: 223", lii3T2.toString(), i.next().toString()); 
    assertEquals("From: Liikkeet line: 224", lii4T2.toString(), i.next().toString()); 
    assertEquals("From: Liikkeet line: 225", lii5T2.toString(), i.next().toString()); 
    liikkeet = new Liikkeet(); 
    i = liikkeet.iterator(); 
    assertEquals("From: Liikkeet line: 228", false, i.hasNext()); 
    liikkeet.lueTiedostosta(nimi); 
    liikkeet.lisaa(lii5T2); 
    liikkeet.talleta(nimi); 
    assertEquals("From: Liikkeet line: 232", true, tiedosto.delete()); 
    assertEquals("From: Liikkeet line: 233", true, dir.delete()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testIterator302 */
  @Test
  public void testIterator302() {    // Liikkeet: 302
    Liikkeet liikkeet = new Liikkeet(); 
    Liike lii1T1 = new Liike(1); liikkeet.lisaa(lii1T1); 
    Liike lii2T1 = new Liike(1); liikkeet.lisaa(lii2T1); 
    Liike lii3T2 = new Liike(2); liikkeet.lisaa(lii3T2); 
    Liike lii4T2 = new Liike(2); liikkeet.lisaa(lii4T2); 
    Liike lii5T2 = new Liike(2); liikkeet.lisaa(lii5T2); 
    Iterator<Liike> i2=liikkeet.iterator(); 
    assertEquals("From: Liikkeet line: 312", lii1T1, i2.next()); 
    assertEquals("From: Liikkeet line: 313", lii2T1, i2.next()); 
    assertEquals("From: Liikkeet line: 314", lii3T2, i2.next()); 
    assertEquals("From: Liikkeet line: 315", lii4T2, i2.next()); 
    assertEquals("From: Liikkeet line: 316", lii5T2, i2.next()); 
    try {
    assertEquals("From: Liikkeet line: 317", lii4T2, i2.next()); 
    fail("Liikkeet: 317 Did not throw NoSuchElementException");
    } catch(NoSuchElementException _e_){ _e_.getMessage(); }
    int n = 0; 
    int treNrot[] = { 1,1,2,2,2} ; 
    for ( Liike lii : liikkeet ) {
    assertEquals("From: Liikkeet line: 321", treNrot[n], lii.getTreeniNro()); n++; 
    }
    assertEquals("From: Liikkeet line: 323", 5, n); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAnna337 */
  @Test
  public void testAnna337() {    // Liikkeet: 337
    Liikkeet liikkeet = new Liikkeet(); 
    Liike lii1T1 = new Liike(1); liikkeet.lisaa(lii1T1); 
    Liike lii2T1 = new Liike(1); liikkeet.lisaa(lii2T1); 
    Liike lii3T2 = new Liike(2); liikkeet.lisaa(lii3T2); 
    Liike lii4T2 = new Liike(2); liikkeet.lisaa(lii4T2); 
    Liike lii5T2 = new Liike(2); liikkeet.lisaa(lii5T2); 
    Liike lii6T5 = new Liike(5); liikkeet.lisaa(lii6T5); 
    List<Liike> loytyneet; 
    loytyneet = liikkeet.anna(3); 
    assertEquals("From: Liikkeet line: 348", 0, loytyneet.size()); 
    loytyneet = liikkeet.anna(1); 
    assertEquals("From: Liikkeet line: 350", 2, loytyneet.size()); 
    assertEquals("From: Liikkeet line: 351", true, loytyneet.get(0) == lii1T1); 
    assertEquals("From: Liikkeet line: 352", true, loytyneet.get(1) == lii2T1); 
    loytyneet = liikkeet.anna(5); 
    assertEquals("From: Liikkeet line: 354", 1, loytyneet.size()); 
    assertEquals("From: Liikkeet line: 355", true, loytyneet.get(0) == lii6T5); 
  } // Generated by ComTest END
}