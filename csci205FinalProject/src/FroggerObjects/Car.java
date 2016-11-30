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
* File: Car
* Description: Represents one Car in a Road
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
 * Represents one Car in a Road
 *
 * @author jeo008, sms063, gmc017
 */
public class Car extends ImageView {

    /*
    the path that the car will follow
     */
    private Path thePath;
    /*
    the path transistion that animates the car across the path
     */
    private PathTransition pathTransition;

    /*
    constant height of car image
     */
    private static final double HEIGHT = 25;
    /*
    constant width of car image
     */
    private static final double WIDTH = 50;

    /*
    int representing the game level being played at - beginner(50) or expert(100)
    to be set by the Main Menu Options
     */
    private int gameMode;

    /**
     * Constructor initializes instance variables and sets the path that the car
     * will follow
     *
     * @param fileName name of the image file for the car
     * @param startX starting X position for car path
     * @param startY starting Y position for the car path
     * @param endX ending X position for the car path
     * @param gameMode designated game mode
     */
    public Car(String fileName, int startX, int startY, int endX, int gameMode) {

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
     * Initializes the Path that the Car will follow
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
     * Initializes the Path Transition that will run the Car along the Path
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
     * Plays the Path Transition that animates the Car
     */
    public void moveCar() {
        pathTransition.play();
    }

    /**
     * Returns the Path that the Car follows
     *
     * @return Path
     */
    public Path getThePath() {
        return thePath;
    }

}
