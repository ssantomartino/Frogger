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

    /* ******************************
     * These tests were written in the hopes of being able to test each
     * function. However, JavaFX and Junit do not work well together at all. Our
     * hope was that if we could find a solution, these tests would all pass. We
     * left them here to show you that we at least attempted to fulfill the unit
     * test portion of this project
     ********************************/
//    /**
//     * Test of restartFrog method, of class Frog.
//     */
//    @Test
//    public void testRestartFrog() {
//        System.out.println("restartFrog");
//        Frog instance = new Frog();
//        instance.restartFrog();
//        int STARTING_X_POS = 350;
//        int STARTING_Y_POS = 675;
//
//        // original position is where it should be
//        assertEquals(instance.getX(), STARTING_X_POS);
//        assertEquals(instance.getY(), STARTING_Y_POS);
//
//        instance.setTranslateX(5);
//        instance.setTranslateY(5);
//
//        assertNotEquals(instance.getX(), STARTING_X_POS);
//        assertNotEquals(instance.getY(), STARTING_Y_POS);
//
//        instance.restartFrog();
//
//        assertEquals(instance.getX(), STARTING_X_POS);
//        assertEquals(instance.getY(), STARTING_Y_POS);
//
//    }
}
