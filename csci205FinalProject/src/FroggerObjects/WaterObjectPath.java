/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2016
*
* Name: Sam Santomartino, Grace Cook, and John O'Brien
* Date: Nov 12, 2016
* Time: 3:46:13 PM
*
* Project: csci205FinalProject
* Package: FroggerMVC
* File: WaterObjectPath
* Description:
*
* ****************************************
 */
package FroggerObjects;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author jeo008
 */
public class WaterObjectPath {

    private static final int NUM_OBJECTS_IN_RIVER = 5;
    private static final int RIVER_HEIGHT = 22;
    private static final int RIVER_WIDTH = 700;
    private boolean faceRight;
    private WaterObject[] theObjects;
    private Rectangle theRiver;

    public WaterObjectPath(int startX, int startY, int endX, boolean faceR) {
        this.theObjects = new WaterObject[NUM_OBJECTS_IN_RIVER];
        this.faceRight = faceR;
        this.addObjects(startX, startY, endX);
        this.theRiver = new Rectangle(RIVER_WIDTH, RIVER_HEIGHT, Color.BLUE);
        this.theRiver.setX(0);
        this.theRiver.setY(startY - RIVER_HEIGHT / 2);
    }

    private void addObjects(int startX, int startY, int endX) {

        for (int i = 0; i < this.theObjects.length; i++) {
            String objectType = getObjectType();
            WaterObject waterObject = new WaterObject(objectType, startX, startY,
                                                      endX);
            this.theObjects[i] = waterObject;
        }
    }

    public Rectangle getTheRiver() {
        return theRiver;
    }

    private String getObjectType() {
        if (this.faceRight) {
            return "log.png"; //log
        } else {
            return "turtles.png"; //turtle
        }
    }

    public WaterObject[] getTheObjects() {
        return theObjects;
    }

}
