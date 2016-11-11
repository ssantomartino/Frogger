/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2016
*
* Name: Sam Santomartino, Grace Cook, and John O'Brien
* Date: Nov 2, 2016
* Time: 5:19:44 PM
*
* Project: csci205FinalProject
* Package:
* File: FroggerController
* Description:
*
* ****************************************
 */
package FroggerMVC;

import FroggerObjects.Car;
import javafx.geometry.Bounds;

/**
 *
 * @author jeo008
 */
class FroggerController {

    private FroggerView theView;
    private FroggerModel theModel;

    private int numLives;
    private static final double STEP_SIZE = 25;

    FroggerController(FroggerView theView, FroggerModel theModel) {
        this.theView = theView;
        this.theModel = theModel;
        this.numLives = FroggerView.getNUM_LIVES();

    }

    public void updateFrogUpPosition() {
        this.theView.getTheFrog().setTranslateY(
                this.theView.getTheFrog().getTranslateY() - STEP_SIZE);
        this.theView.getTheFrog().setRotate(0);
    }

    public void updateFrogDownPosition() {
        this.theView.getTheFrog().setTranslateY(
                this.theView.getTheFrog().getTranslateY() + STEP_SIZE);
        this.theView.getTheFrog().setRotate(180);
    }

    public void updateFrogRightPosition() {
        this.theView.getTheFrog().setTranslateX(
                this.theView.getTheFrog().getTranslateX() + STEP_SIZE);

        this.theView.getTheFrog().setRotate(90);
    }

    public void updateFrogLeftPosition() {
        this.theView.getTheFrog().setTranslateX(
                this.theView.getTheFrog().getTranslateX() - STEP_SIZE);
        this.theView.getTheFrog().setRotate(270);
    }

    public boolean checkBottomBound() {
        int yMax = theView.getRootYMax();
        Bounds frogBounds = theView.getTheFrog().localToScene(
                theView.getTheFrog().getBoundsInLocal());
        int frogYMax = (int) frogBounds.getMaxY();
        if (yMax - frogYMax < 10) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkTopBound() {
        int yMin = theView.getRootYMin();
        Bounds frogBounds = theView.getTheFrog().localToScene(
                theView.getTheFrog().getBoundsInLocal());
        int frogYMin = (int) frogBounds.getMinY();
        if (frogYMin - yMin < 10) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkRightBound() {
        int xMax = theView.getRootXMax();
        Bounds frogBounds = theView.getTheFrog().localToScene(
                theView.getTheFrog().getBoundsInLocal());
        int frogXMax = (int) frogBounds.getMaxX();
        //System.out.println(xMax + "," + frogXMax);
        if (xMax - frogXMax < 10) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkLeftBound() {
        int xMin = theView.getRootXMin();
        Bounds frogBounds = theView.getTheFrog().localToScene(
                theView.getTheFrog().getBoundsInLocal());
        int frogXMin = (int) frogBounds.getMinX();
        if (frogXMin - xMin < 10) {
            return false;
        } else {
            return true;
        }
    }

    public void checkCollisions() {
        Car[][] cars = theView.getTheCars();
        Bounds frogBounds = theView.getTheFrog().getBoundsInParent();
        for (Car[] carList : cars) {
            for (Car car : carList) {
                if (car.getBoundsInParent().intersects(frogBounds)) {
                    theView.getTheFrog().restartFrog();
                    //theView.resetRoad();
                    this.numLives--;
                    this.theView.removeNextLife();
                    if (this.numLives <= 0) {
                        this.theView.endGame();
                    }
                }
            }
        }
    }

    public int getNumLives() {
        return this.numLives;
    }

}
