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
* File: River
* Description: Represents a River that Holds Multiple Water Objects (logs/turtles)
*
* ****************************************
 */
package FroggerObjects;

/**
 * Represents a River that Holds Multiple Water Objects (logs/turtles)
 *
 * @author jeo008, sms063, gmc017
 */
public class River {

    /*
    Constant Maximum number of Water Objects in the River at a time
     */
    private static final int NUM_OBJECTS_IN_RIVER = 5;

    /*
    Flag indicating if these Water Objects will be logs (true) or turtles (false)
     */
    private boolean logs;
    /*
    Array containing the Water Objects
     */
    private MovingObject[] theWaterObjects;

    /*
    constant width of water object image
     */
    private static final int WIDTH = 70;

    /*
    int representing the game level being played at - beginner(50) or expert(100)
    to be set by the Main Menu Options
     */
    private int gameMode;

    /**
     * Constructor initializes instance variables and adds the water object to
     * the River
     *
     * @param startX starting X position for the first water object path
     * @param startY starting Y position for the first water object path
     * @param endX ending X position for the first water object path
     * @param gameMode designated game mode
     * @param logs boolean indicates if the water object on the river are logs
     * (true) or turtles (false)
     */
    public River(int startX, int startY, int endX, boolean logs,
                           int gameMode) {
        this.theWaterObjects = new MovingObject[NUM_OBJECTS_IN_RIVER];
        this.logs = logs;

        this.gameMode = gameMode;
        this.addObjects(startX, startY, endX);
    }

    /**
     * Creates and adds water object to the Array
     *
     * @param startX starting X position for the first water object path
     * @param startY starting Y position for the first water object path
     * @param endX ending X position for the first water object path
     */
    private void addObjects(int startX, int startY, int endX) {

        for (int i = 0; i < this.theWaterObjects.length; i++) {
            String objectType = getWaterObjectType();
            MovingObject waterObject = new MovingObject(objectType, startX,
                                                        startY,
                                                        endX, this.gameMode);
            waterObject.setWidth(WIDTH);
            this.theWaterObjects[i] = waterObject;
        }
    }

    /**
     * Determines which image file to return depending on the "logs" flag
     * variable; if true, returns log file name, otherwise returns turtle file
     * name
     *
     * @return String of image file name
     */
    private String getWaterObjectType() {
        if (this.logs) {
            return "log.png"; //log
        } else {
            return "turtles.png"; //turtle
        }
    }

    /**
     * returns the array of Water Objects
     *
     * @return WaterObject[]
     */
    public MovingObject[] getTheObjects() {
        return theWaterObjects;
    }

}
