/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2016
*
* Name: Sam Santomartino, Grace Cook, and John O'Brien
* Date: Nov 7, 2016
* Time: 10:10:47 AM
*
* Project: csci205FinalProject
* Package: FroggerObjects
* File: MovingObject
* Description: Represents one Frogger Object (MovingObject, Log, or Turtle) on either a Road or River
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
 * Represents one Frogger Object (MovingObject, Log, or Turtle) on either a Road
 * or River
 *
 * @author jeo008, sms063, gmc017
 */
public class MovingObject extends ImageView {

    /*
    the path that the object will follow
     */
    private Path thePath;
    /*
    the path transistion that animates the object across the path
     */
    private PathTransition pathTransition;

    /*
    constant height of the object's image
     */
    private static final double HEIGHT = 25;

    /*
    int representing the game level being played at - beginner(50) or expert(100)
    to be set by the Main Menu Options
     */
    private int gameMode;

    /**
     * Constructor initializes instance variables and sets the path that the
     * object either a car, turtle, or log, will follow
     *
     * @param fileName name of the image file for the object
     * @param startX starting X position for object path
     * @param startY starting Y position for the object path
     * @param endX ending X position for the object path
     * @param gameMode designated game mode
     */
    public MovingObject(String fileName, int startX, int startY, int endX,
                        int gameMode) {

        super(fileName);
        this.gameMode = gameMode;
        setFitHeight(HEIGHT);
//        setFitWidth(WIDTH);
        setX(startX);
        setY(startY);
        createPath(startX, startY, endX);
        createPathTransition(startX, endX);
    }

    public void setWidth(int width) {
        setFitWidth(width);
    }

    /**
     * Initializes the Path that the Frogger Object will follow
     *
     * Source: creating a Path
     * http://docs.oracle.com/javafx/2/animations/basics.htm#CJAJJAGI
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
     * Initializes the Path Transition that will run the Frogger Object along
     * the Path
     *
     * Source: creating Path Transitions and Animations
     * http://www.javaworld.com/article/2074529/core-java/javafx-2-animation--path-transitions.html
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
                Duration.seconds(Math.abs(startX - endX) / gameMode));
    }

    /**
     * Plays the Path Transition that animates the Frogger Object
     */
    public void moveFroggerObject() {
        pathTransition.play();
    }

    /**
     * Returns the Path that the Frogger Object follows
     *
     * @return Path
     */
    public Path getThePath() {
        return thePath;
    }

}
