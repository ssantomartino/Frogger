/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2016
*
* Name: Sam Santomartino, Grace Cook, and John O'Brien
* Date: Nov 4, 2016
* Time: 10:31:39 AM
*
* Project: csci205FinalProject
* Package: FroggerMVC
* File: Frog
* Description: Represents the Frog object in the Game
*
* ****************************************
 */
package FroggerObjects;

import javafx.scene.image.ImageView;

/**
 * Represents the Frog object in the Game
 *
 * @author jeo008, sms063, gmc017
 */
public class Frog extends ImageView {

    /*
    Source: got the Frog Image From the following Website:
    https://www.spriters-resource.com/search/?q=frogger

    Constant image file name
     */
    public static final String fileName = "basicFrog.png";

    /*
    Constant Starting X Position of the Frog
     */
    public static final int STARTING_X_POS = 350;
    /*
    Constant Starting Y Position of the Frog
     */
    public static final int STARTING_Y_POS = 675;

    /*
    constant width of frog image
     */
    private static final double width = 25;
    /*
    constant height of frog image
     */
    private static final double height = 25;

    /*
    Flag is true if the Frog is currently on a Water Object, false otherwise
     */
    private boolean isOnWaterObject;

    /**
     * Initializes Frog variables and position
     *
     * @param fileName the frog image file name
     */
    public Frog() {

        super(fileName);
        setFitHeight(height);
        setFitWidth(width);
        setSmooth(true);
        setTranslateX(STARTING_X_POS);
        setTranslateY(STARTING_Y_POS);

        this.isOnWaterObject = false;

    }

    /**
     * Sets the X Translation of the Frog Object within the Scene
     *
     * @param Xtranslate the distance to translate
     */
    public void setXTranslation(double Xtranslate) {
        setTranslateX(Xtranslate);
    }

    /**
     * Getter returns the width of the Frog image
     *
     * @return double width
     */
    public static double getWidth() {
        return width;
    }

    /**
     * Getter returns the height of the Frog image
     *
     * @return double height
     */
    public static double getHeight() {
        return height;
    }

    /**
     * Getter returns the status of the flag returns true if th frog is on the
     * water object, false otherwise
     *
     * @return boolean is on water object
     */
    public boolean getisOnWaterObject() {
        return this.isOnWaterObject;
    }

    /**
     * Sets the isOnWaterObject Flag to true
     */
    public void setisOnWaterObjectTrue() {
        this.isOnWaterObject = true;
    }

    /**
     * Sets the isOnWaterObject Flag to false
     */
    public void setisOnWaterObjectFalse() {
        this.isOnWaterObject = false;
    }

    /**
     * Restarts the Frog position to the starting position
     */
    public void restartFrog() {
        setTranslateX(STARTING_X_POS);
        setTranslateY(STARTING_Y_POS);
    }

}
