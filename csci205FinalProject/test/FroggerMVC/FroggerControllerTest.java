/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2016
*
* Name: Sam Santomartino, Grace Cook, and John O'Brien
* Date: Dec 5, 2016
* Time: 6:09:49 PM
*
* Project: csci205FinalProject
* Package: FroggerMVC
* File: FroggerControllerTest
* Description:
*
* ****************************************
 */
package FroggerMVC;

import javafx.stage.Stage;
import junit.framework.TestCase;

/**
 *
 * @author gmc017
 */
public class FroggerControllerTest extends TestCase {

    private FroggerController theController;

    public FroggerControllerTest(String testName) {
        super(testName);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
//        FroggerModel theModel = new FroggerModel();
//        FroggerView theView = new FroggerView(theModel);
//        FroggerMainMenu theMainMenu = new FroggerMainMenu(theModel);
//        this.theController = new FroggerController(theView, theModel,
//                                                   theMainMenu);
        FroggerMain theMain = new FroggerMain();
        Stage primaryStage = new Stage();
        theMain.start(primaryStage);
        this.theController = theMain.getTheController();

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
//     * Test of getNumLives method, of class FroggerController.
//     */
//    @Test
//    public void testGetNumLives() {
//        System.out.println("getNumLives");
//        int beginningNumLives = this.theController.getNumLives();
//        assertEquals(beginningNumLives, 4);
//
//    }
//
//    /**
//     * Test of restartFrogIndex method, of class FroggerController.
//     */
//    @Test
//    public void testRestartFrogIndex() {
//        System.out.println("restartFrogIndex");
//        this.theController.restartFrogIndex();
//        assertEquals(this.theController.getFrogIndex(), -1);
//
//    }
//
//    /**
//     * Test of setGameOver method, of class FroggerController.
//     */
//    @Test
//    public void testSetGameOver() {
//        System.out.println("setGameOver");
//        this.theController.setGameOver(true);
//        assertTrue(this.theController.isGameOver());
//        this.theController.setGameOver(false);
//        assertFalse(this.theController.isGameOver());
//
//    }
//
//    /**
//     * Test of updateFrogUpPosition method, of class FroggerController.
//     */
//    @Test
//    public void testUpdateFrogUpPosition() {
//        System.out.println("updateFrogUpPosition");
//        double frogPosition = this.theController.getTheFrog().getTranslateY();
//        this.theController.updateFrogUpPosition();
//        assertEquals(this.theController.getTheFrog().getTranslateY(),
//                     frogPosition - 50);
//    }
//
//    /**
//     * Test of updateFrogDownPosition method, of class FroggerController.
//     */
//    @Test
//    public void testUpdateFrogDownPosition() {
//        System.out.println("updateFrogDownPosition");
//        double frogPosition = this.theController.getTheFrog().getTranslateY();
//        this.theController.updateFrogDownPosition();
//        assertEquals(this.theController.getTheFrog().getTranslateY(),
//                     frogPosition + 50);
//    }
//
//    /**
//     * Test of updateFrogRightPosition method, of class FroggerController.
//     */
//    @Test
//    public void testUpdateFrogRightPosition() {
//        System.out.println("updateFrogRightPosition");
//        double frogPosition = this.theController.getTheFrog().getTranslateX();
//        this.theController.updateFrogRightPosition();
//        assertEquals(this.theController.getTheFrog().getTranslateX(),
//                     frogPosition + 50);
//    }
//
//    /**
//     * Test of updateFrogLeftPosition method, of class FroggerController.
//     */
//    @Test
//    public void testUpdateFrogLeftPosition() {
//        System.out.println("updateFrogLeftPosition");
//        double frogPosition = this.theController.getTheFrog().getTranslateX();
//        this.theController.updateFrogLeftPosition();
//        assertEquals(this.theController.getTheFrog().getTranslateX(),
//                     frogPosition - 50);
//    }
//
//    /**
//     * Test of removeLife method, of class FroggerController.
//     */
//    @Test
//    public void testRemoveLife() {
//        System.out.println("removeLife");
//        int highestNumLives = 4;
//        int beginningNumLives = this.theController.getNumLives();
//        assertEquals(beginningNumLives, highestNumLives);
//        this.theController.removeLife();
//        assertEquals(this.theController.getNumLives(), highestNumLives - 1);
//    }
}
