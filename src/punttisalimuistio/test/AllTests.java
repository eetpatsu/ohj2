package punttisalimuistio.test;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * Kaikki testit
 * @author Eetu
 * @version 0.6, 20.6.2023 Tiedostonhallinta
 */
@Suite
@SelectClasses({ LiikeTest.class, LiikkeetTest.class,
        PunttisalimuistioTest.class, TreeniTest.class, TreenitTest.class })
public class AllTests {
    // Ajetaan kaikki testit
}