/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2016
*
* Name: Sam Santomartino, Grace Cook, and John O'Brien
* Date: Nov 22, 2016
* Time: 1:32:57 PM
*
* Project: csci205FinalProject
* Package: FroggerObjects
* File: LilyPad
* Description: Represents one LilyPad
*
* ****************************************
 */
package FroggerObjects;

import javafx.scene.image.ImageView;

/**
 * Represents one LilyPad
 *
 * @author jeo008, sms063, gmc017
 */
public class LilyPad extends ImageView {

    /*
    Constant height of the Lily pad image
     */
    private static final double HEIGHT = 50;
    /*
    Constant width of the Lily pad image
     */
    private static final double WIDTH = 50;
    /*
    X location of the Lily pad on the screen
     */
    private double xLocation;
    /*
    Constant y location of the Lily pad on the screen
     */
    private static final double yLocation = 55;
    /*
    Constant name of the image file name for the lily pad
     */
    private static final String fileName = "lilyPad.jpg";
    /*
    Flag variable is true of the lily pad is currently occupied by a frog, false if it is open
     */
    private boolean isOccupied;

    /**
     * Constructor initializes the lily pad and positions
     *
     * @param xLocation the x location of the lily pad
     */
    public LilyPad(double xLocation) {
        super(fileName);
        setFitHeight(HEIGHT);
        setFitWidth(WIDTH);
        this.xLocation = xLocation;
        setPosition();
        this.isOccupied = false;
    }

    /**
     * Sets the position of the lily pad on the screen
     */
    private void setPosition() {
        setTranslateX(xLocation);
        setTranslateY(yLocation);
    }

    /**
     * returns the x location of the lily pad
     *
     * @return double x location
     */
    public double getxLocation() {
        return xLocation;
    }

    /**
     * Returns the y location of the lily pad
     *
     * @return double of the y location
     */
    public double getyLocation() {
        return yLocation;
    }

    /**
     * returns the status of the occupied flag returns true if the lily pad is
     * occupied, false otherwise
     *
     * @return boolean
     */
    public boolean getIsOccupied() {
        return this.isOccupied;
    }

    /**
     * sets the isOccupied flag variable to true
     */
    public void setOccupiedTrue() {
        this.isOccupied = true;
    }

    /**
     * sets the isOccupied flag variable to false
     */
    public void setOccupiedFalse() {
        this.isOccupied = false;
    }

}
