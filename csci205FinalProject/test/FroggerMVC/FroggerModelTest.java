/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2016
*
* Name: Sam Santomartino, Grace Cook, and John O'Brien
* Date: Dec 5, 2016
* Time: 6:09:40 PM
*
* Project: csci205FinalProject
* Package: FroggerMVC
* File: FroggerModelTest
* Description:
*
* ****************************************
 */
package FroggerMVC;

import junit.framework.TestCase;

/**
 *
 * @author jeo008
 */
public class FroggerModelTest extends TestCase {

    public FroggerModelTest(String testName) {
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
     * ******************************
     * These tests were written in the hopes of being able to test each
     * function. However, JavaFX and Junit do not work well together at all. Our
     * hope was that if we could find a solution, these tests would all pass. We
     * left them here to show you that we at least attempted to fulfill the unit
     * test portion of this project
     *******************************
     */
//    /**
//     * Test of generateCarPaths method, of class FroggerModel.
//     */
//    @Test
//    public void testGenerateCarPaths() {
//        System.out.println("generateCarPaths");
//        FroggerModel instance = new FroggerModel();
//        Road[] result = instance.generateCarPaths();
//        assertTrue(result.length != 0);
//        for (Road road : result) {
//            assertNotNull(road);
//        }
//    }
//
//    /**
//     * Test of generateWaterObjectPaths method, of class FroggerModel.
//     */
//    @Test
//    public void testGenerateWaterObjectPaths() {
//        System.out.println("generateWaterObjectPaths");
//        FroggerModel instance = new FroggerModel();
//        River[] result = instance.generateWaterObjectPaths();
//        assertTrue(result.length != 0);
//        for (River river : result) {
//            assertNotNull(river);
//        }
//    }
//
//    /**
//     * Test of generateLilyPads method, of class FroggerModel.
//     */
//    @Test
//    public void testGenerateLilyPads() {
//        System.out.println("generateLilyPads");
//        FroggerModel instance = new FroggerModel();
//        LilyPad[] result = instance.generateLilyPads();
//        for (LilyPad lilyPad : result) {
//            assertNotNull(lilyPad);
//            assertTrue(
//                    (lilyPad.getxLocation() > 0) && (lilyPad.getxLocation() < 700));
//            assertTrue(
//                    (lilyPad.getyLocation() > 0) && (lilyPad.getyLocation() < 750));
//        }
//    }
}
