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

/**
 *
 * @author jeo008
 */
public class WaterObjectPath {

    private static final int NUM_OBJECTS_IN_RIVER = 5;
    private boolean faceRight;
    private WaterObject[] theObjects;

    public WaterObjectPath(int startX, int startY, int endX, boolean faceR) {
        this.theObjects = new WaterObject[NUM_OBJECTS_IN_RIVER];
        this.faceRight = faceR;
        this.addObjects(startX, startY, endX);
    }

    private void addObjects(int startX, int startY, int endX) {

        for (int i = 0; i < this.theObjects.length; i++) {
            String objectType = getObjectType();
            WaterObject waterObject = new WaterObject(objectType, startX, startY,
                                                      endX);
            this.theObjects[i] = waterObject;
        }
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
