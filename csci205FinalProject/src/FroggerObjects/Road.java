/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2016
*
* Name: Sam Santomartino, Grace Cook, and John O'Brien
* Date: Nov 10, 2016
* Time: 10:10:32 PM
*
* Project: csci205FinalProject
* Package: FroggerObjects
* File: Road
* Description: Represents a Road that Holds Multiple Cars
*
* ****************************************
 */
package FroggerObjects;

import java.util.Random;

/**
 * Represents a Road that Holds Multiple Cars
 *
 * @author jeo008, sms063, gmc017
 */
public class Road {

    /*
    Constant Maximum number of Cars on the Road at a time
     */
    private static final int NUM_CARS_ON_ROAD = 5;
    /*
    Constant array of image file names for cars facing to the left
     */
    private static final String[] carFilesL = {"Car.png", "Audi.png", "taxi.png"};
    /*
    Constant array of image file names for cars facing to the right
     */
    private static final String[] carFilesR = {"Police.png", "Black_viper.png", "Mini_truck.png"};
    /*
    Array containing the Cars
     */
    private MovingObject[] theCars;
    /*
    Flag indicating if these Cars will be facing right (true) or left (false)
     */
    private boolean faceRight;

    /*
    constant width of water object image
     */
    private static final int WIDTH = 50;

    /*
    int representing the game level being played at - beginner(50) or expert(100)
    to be set by the Main Menu Options
     */
    private int gameMode;

    /**
     * Constructor initializes instance variables and adds the Cars to the Road
     *
     * @param startX starting X position for the first car path
     * @param startY starting Y position for the first car path
     * @param endX ending X position for the first car path
     * @param gameMode designated game mode
     * @param faceR boolean indicates if the cars on the road are facing right
     * (true) or not (false)
     */
    public Road(int startX, int startY, int endX, boolean faceR, int gameMode) {
        this.theCars = new MovingObject[NUM_CARS_ON_ROAD];
        this.faceRight = faceR;
        this.gameMode = gameMode;
        this.addCars(startX, startY, endX);
    }

    /**
     * Creates and adds Cars to the Array
     *
     * @param startX starting X position for the first car path
     * @param startY starting Y position for the first car path
     * @param endX ending X position for the first car path
     */
    private void addCars(int startX, int startY, int endX) {

        for (int i = 0; i < this.theCars.length; i++) {
            String carType = getCarType();
            MovingObject car = new MovingObject(carType, startX, startY, endX,
                                                  this.gameMode);
            car.setWidth(WIDTH);
            this.theCars[i] = car;
        }
    }

    /**
     * Determines which image file array to pull a random MovingObject image
 from, depending on if the cars should be facing right or not
     *
     * @return String of image file name
     */
    private String getCarType() {
        if (this.faceRight) {
            return this.carFilesR[new Random().nextInt(
                    this.carFilesR.length)];
        } else {
            return this.carFilesL[new Random().nextInt(
                    this.carFilesL.length)];
        }
    }

    /**
     * returns the array of Cars
     *
     * @return MovingObject[]
     */
    public MovingObject[] getTheCars() {
        return theCars;
    }

}
