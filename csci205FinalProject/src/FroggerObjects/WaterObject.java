/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2016
*
* Name: Sam Santomartino, Grace Cook, and John O'Brien
* Date: Nov 11, 2016
* Time: 2:42:36 PM
*
* Project: csci205FinalProject
* Package: FroggerObjects
* File: WaterObject
* Description: Represents a Water Object - either a Turtle or Log - in a River
*
* ****************************************
 */
package FroggerObjects;

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.scene.image.ImageView;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

/**
 * Represents a Water Object - either a Turtle or Log - in a River
 *
 * @author jeo008, sms063, gmc017
 */
public class WaterObject extends ImageView {

    /*
    the path that the water object will follow
     */
    private Path thePath;
    /*
    the path transistion that animates the water object across the path
     */
    private PathTransition pathTransition;
    /*
    constant height of water object image
     */
    private static final double HEIGHT = 25;
    /*
    constant width of water object image
     */
    private static final double WIDTH = 70;

    /*
    int representing the game level being played at - beginner(50) or expert(100)
    to be set by the Main Menu Options
     */
    private int gameMode;

    /**
     * Constructor initializes instance variables and sets the path that the
     * water object will follow
     *
     * @param fileName name of the image file for the water object
     * @param startX starting X position for water object path
     * @param startY starting Y position for the water object path
     * @param endX ending X position for the water object path
     * @param gameMode designated game mode
     */
    public WaterObject(String fileName, int startX, int startY, int endX,
                       int gameMode) {
        super(fileName);
        this.gameMode = gameMode;
        setFitHeight(HEIGHT);
        setFitWidth(WIDTH);
        setX(startX);
        setY(startY);
        createPath(startX, startY, endX);
        createPathTransition(startX, endX);
    }

    /**
     * Initializes the Path that the Water Object will follow
     *
     * @param startX starting X position for the path
     * @param startY starting Y position for the path
     * @param endX ending X position for the path
     */
    private void createPath(int startX, int startY, int endX) {
        this.thePath = new Path();
        this.thePath.getElements().add(new MoveTo(startX, startY));
        this.thePath.getElements().add(new HLineTo(endX));
        this.thePath.setOpacity(0.0);
    }

    /**
     * Initializes the Path Transition that will run the Water Object along the
     * Path
     *
     * @param startX starting X position
     * @param endX ending X position
     */
    private void createPathTransition(int startX, int endX) {
        this.pathTransition = new PathTransition();
        pathTransition.setPath(thePath);
        pathTransition.setNode(this);
        pathTransition.setCycleCount(Animation.INDEFINITE);
        pathTransition.setDuration(
                Duration.seconds(Math.abs(startX - endX) / this.gameMode));
    }

    /**
     * Plays the Path Transition that animates the Water Object
     */
    public void moveWaterObject() {
        pathTransition.play();
    }

    /**
     * Returns the Path that the Water Object follows
     *
     * @return Path
     */
    public Path getThePath() {
        return thePath;
    }

}
