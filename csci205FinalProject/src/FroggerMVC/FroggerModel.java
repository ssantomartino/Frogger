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

import FroggerObjects.Road;
import FroggerObjects.LilyPad;
import FroggerObjects.River;

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
    /*
    how large each horizontal row is
     */
    private static final int INDEX_INCREMENT = 50;
    /*
    Height at which river objects start
     */
    private static final int RIVER_START_Y = 337;
    /*
    index where object is offscreen to the right
     */
    private static final int OFFSCREEN_RIGHT = 770;
    /*
    index where object is offscreen to the left
     */
    private static final int OFFSCREEN_LEFT = -70;
    /*
    width of the pane
     */
    private int PANE_WIDTH = 700;

    /**
     * Constructor initializes necessary instance variables
     */
    public FroggerModel() {
        this.gameMode = INDEX_INCREMENT; // arbitrary initialization - will be set by Main Menu before actually used
        this.currentLevel = 0;
    }

    /**
     * Generates 5 new CarPaths with the correct spacing on the screen and
     * returns an array of the CarPaths
     *
     * @return Road[] array of CarPaths
     */
    public Road[] generateCarPaths() {

        int numRoads = 5;
        Road[] paths = new Road[numRoads];

        for (int i = 0; i < numRoads; i++) {
            int startX;
            int startY;
            int endX;

            // changes the direction of every other path
            if (i % 2 == 1) {
                startX = OFFSCREEN_LEFT;
                startY = CAR_START_Y - i * INDEX_INCREMENT;
                endX = OFFSCREEN_RIGHT;
                paths[i] = new Road(startX, startY, endX, true, this.gameMode);
            } else {
                startX = OFFSCREEN_RIGHT;
                startY = CAR_START_Y - i * INDEX_INCREMENT;
                endX = OFFSCREEN_LEFT;
                paths[i] = new Road(startX, startY, endX, false,
                                       this.gameMode);
            }

        }
        return paths;
    }
    private static final int CAR_START_Y = 637;

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
     * @return River[] array of WaterObjectPaths
     */
    public River[] generateWaterObjectPaths() {

        int numRivers = 5;
        River[] paths = new River[numRivers];

        for (int i = 0; i < numRivers; i++) {
            int startX;
            int startY;
            int endX;

            // changes the direction of every other path
            if (i % 2 == 1) {
                startX = OFFSCREEN_LEFT;
                startY = RIVER_START_Y - i * INDEX_INCREMENT;
                endX = OFFSCREEN_RIGHT;
                paths[i] = new River(startX, startY, endX, true,
                                               this.gameMode);
            } else {
                startX = OFFSCREEN_RIGHT;
                startY = RIVER_START_Y - i * INDEX_INCREMENT;
                endX = OFFSCREEN_LEFT;
                paths[i] = new River(startX, startY, endX, false,
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
        int lilyPadWidth = 50;

        for (int i = 0; i < numPads; i++) {
            double xLocation = (i + 1) * (PANE_WIDTH / (numPads + 1)) - lilyPadWidth / 2;
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

    /**
     * Resets the level counter to 0
     */
    public void resetLevels() {
        this.currentLevel = 0;
    }

}
