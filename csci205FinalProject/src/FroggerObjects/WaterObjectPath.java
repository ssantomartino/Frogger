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

import java.util.ArrayList;

/**
 *
 * @author jeo008
 */
public class WaterObjectPath {

    private static final int NUM_OBJECTS_IN_RIVER = 5;
    private static final int[] NUM_OBJECTS_IN_RIVER_ARRAY = {5, 4, 3, 2, 2};
    private static final int RIVER_HEIGHT = 22;
    private static final int RIVER_WIDTH = 700;
    private boolean faceRight;
    private WaterObject[] theObjects;
    private ArrayList<WaterObject> theObjectsArray;

    public WaterObjectPath(int startX, int startY, int endX, boolean faceR) {
        this.theObjects = new WaterObject[NUM_OBJECTS_IN_RIVER];
        this.faceRight = faceR;
        this.theObjectsArray = new ArrayList<WaterObject>();
        this.addObjects(startX, startY, endX);
        this.addObjectsArray(startX, startY, endX);
    }

    private void addObjects(int startX, int startY, int endX) {

        for (int i = 0; i < this.theObjects.length; i++) {
            String objectType = getObjectType();
            WaterObject waterObject = new WaterObject(objectType, startX, startY,
                                                      endX);
            this.theObjects[i] = waterObject;
        }
    }

    private void addObjectsArray(int startX, int startY, int endX) {

        for (int i = 0; i < this.theObjects.length; i++) {
            String objectType = getObjectType();
            WaterObject waterObject = new WaterObject(objectType, startX, startY,
                                                      endX);
            this.theObjectsArray.add(waterObject);
        }
    }

    private void removeWaterObject() {
        this.theObjectsArray.remove(this.theObjectsArray.size() - 1);
    }

    private int getCurNumWaterObjectsInRiver() {
        return this.theObjectsArray.size();
    }

    public int getNumWaterObjectsInRiverAtLevel(int index) {
        return NUM_OBJECTS_IN_RIVER_ARRAY[index];
    }

    public ArrayList<WaterObject> getWaterObjects() {
        return this.theObjectsArray;
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
