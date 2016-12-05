/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2016
*
* Name: Sam Santomartino, Grace Cook, and John O'Brien
* Date: Dec 5, 2016
* Time: 6:29:07 PM
*
* Project: csci205FinalProject
* Package: FroggerObjects
* File: FrogTest
* Description:
*
* ****************************************
 */
package FroggerObjects;

import junit.framework.TestCase;
import org.junit.Test;

/**
 *
 * @author sms063
 */
public class FrogTest extends TestCase {

    public FrogTest(String testName) {
        super(testName);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of restartFrog method, of class Frog.
     */
    @Test
    public void testRestartFrog() {
        System.out.println("restartFrog");
        Frog instance = new Frog();
        instance.restartFrog();
        int STARTING_X_POS = 350;
        int STARTING_Y_POS = 675;

        assertEquals(instance.getX(), STARTING_X_POS);

    }

}
