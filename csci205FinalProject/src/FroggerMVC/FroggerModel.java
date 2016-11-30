/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2016
*
* Name: Sam Santomartino, Grace Cook, and John O'Brien
* Date: Nov 3, 2016
* Time: 2:54:17 PM
*
* Project: csci205FinalProject
* Package: FroggerMVC
* File: FroggerModel
* Description: Model class of MVC model
*
* ****************************************
 */
package FroggerMVC;

import FroggerObjects.CarPath;
import FroggerObjects.LilyPad;
import FroggerObjects.WaterObjectPath;

/**
 * Model class of MVC model
 *
 * @author jeo008, sms063, gmc017
 */
public class FroggerModel {

    /*
    int representing the game level being played at - beginner(50) or expert(100)
    to be set by the Main Menu Options
     */
    private int gameMode;
    /*
    the current level of the game that the frog is playing
     */
    private int currentLevel;
    /*
    the max amount of levels before winning
     */
    private static final int MAX_LEVELS = 5;

    /**
     * Constructor initializes necessary instance variables
     */
    public FroggerModel() {
        this.gameMode = 50; // arbitrary initialization - will be set by Main Menu before actually used
        this.currentLevel = 0;
    }

    /**
     * Generates 5 new CarPaths with the correct spacing on the screen and
     * returns an array of the CarPaths
     *
     * @return CarPath[] array of CarPaths
     */
    public CarPath[] generateCarPaths() {

        int numRoads = 5;
        CarPath[] paths = new CarPath[numRoads];

        for (int i = 0; i < numRoads; i++) {
            int startX;
            int startY;
            int endX;

            // changes the direction of every other path
            if (i % 2 == 1) {
                startX = -50;
                startY = 637 - i * 50;
                endX = 750;
                paths[i] = new CarPath(startX, startY, endX, true, this.gameMode);
            } else {
                startX = 750;
                startY = 637 - i * 50;
                endX = -50;
                paths[i] = new CarPath(startX, startY, endX, false,
                                       this.gameMode);
            }

        }
        return paths;
    }

    /**
     * sets the type of game play given by an int either 50 (beginner) or 100
     * (expert) set by the Main Menu
     *
     * @param mode int beginner or expert
     */
    public void setGameMode(int mode) {
        this.gameMode = mode;
    }

    /**
     * Generates 5 new WaterObjectPaths with the correct spacing on the screen
     * and returns an array of the WaterObjectPaths
     *
     * @return WaterObjectPath[] array of WaterObjectPaths
     */
    public WaterObjectPath[] generateWaterObjectPaths() {

        int numRivers = 5;
        WaterObjectPath[] paths = new WaterObjectPath[numRivers];

        for (int i = 0; i < numRivers; i++) {
            int startX;
            int startY;
            int endX;

            // changes the direction of every other path
            if (i % 2 == 1) {
                startX = -70;
                startY = 337 - i * 50;
                endX = 750;
                paths[i] = new WaterObjectPath(startX, startY, endX, true,
                                               this.gameMode);
            } else {
                startX = 770;
                startY = 337 - i * 50;
                endX = -50;
                paths[i] = new WaterObjectPath(startX, startY, endX, false,
                                               this.gameMode);
            }

        }
        return paths;
    }

    /**
     * Generates the top level of 5 lily pads at the correct location on the
     * board
     *
     * @return LilyPad[] an array of LilyPads
     */
    public LilyPad[] generateLilyPads() {
        int numPads = 5;
        LilyPad[] lilyPads = new LilyPad[numPads];

        for (int i = 0; i < numPads; i++) {
            double xLocation = (i + 1) * (700 / 6) - 25;
            lilyPads[i] = new LilyPad(xLocation);
        }
        return lilyPads;
    }

    /**
     * Method increases the level count for the game checks if the increase in
     * level is the increase needed to win the game
     *
     * @return true if the level is successfully increased, false if the game is
     * won
     */
    public boolean levelUp() {
        this.currentLevel++;
        if (this.currentLevel >= MAX_LEVELS) {
            //POINTS BONUS AND WIN GAME
            return false;
        }
        return true;
    }

}
