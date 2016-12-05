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
     * Test of levelUp method, of class FroggerModel.
     */
    @Test
    public void testLevelUp() {
        System.out.println("levelUp");
        FroggerModel instance = new FroggerModel();
        boolean expResult = true;

        // while less than max levels, level up should return true
        int MAX_LEVELS = 5;
        for (int i = 1; i < MAX_LEVELS; i++) {
            assertEquals(expResult, instance.levelUp());
        }
        // once >= max levels, level up should return false
        expResult = false;
        assertEquals(expResult, instance.levelUp());

    }

}
