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

import FroggerObjects.LilyPad;
import FroggerObjects.River;
import FroggerObjects.Road;
import junit.framework.TestCase;
import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;

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
     * Test of generateCarPaths method, of class FroggerModel.
     */
    @Test
    public void testGenerateCarPaths() {
        System.out.println("generateCarPaths");
        FroggerModel instance = new FroggerModel();
        Road[] expResult = null;
        Road[] result = instance.generateCarPaths();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGameMode method, of class FroggerModel.
     */
    @Test
    public void testSetGameMode() {
        System.out.println("setGameMode");
        int mode = 0;
        FroggerModel instance = new FroggerModel();
        instance.setGameMode(mode);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateWaterObjectPaths method, of class FroggerModel.
     */
    @Test
    public void testGenerateWaterObjectPaths() {
        System.out.println("generateWaterObjectPaths");
        FroggerModel instance = new FroggerModel();
        River[] expResult = null;
        River[] result = instance.generateWaterObjectPaths();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateLilyPads method, of class FroggerModel.
     */
    @Test
    public void testGenerateLilyPads() {
        System.out.println("generateLilyPads");
        FroggerModel instance = new FroggerModel();
        LilyPad[] expResult = null;
        LilyPad[] result = instance.generateLilyPads();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of levelUp method, of class FroggerModel.
     */
    @Test
    public void testLevelUp() {
        System.out.println("levelUp");
        FroggerModel instance = new FroggerModel();
        boolean expResult = false;
        boolean result = instance.levelUp();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resetLevels method, of class FroggerModel.
     */
    @Test
    public void testResetLevels() {
        System.out.println("resetLevels");
        FroggerModel instance = new FroggerModel();
        instance.resetLevels();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
