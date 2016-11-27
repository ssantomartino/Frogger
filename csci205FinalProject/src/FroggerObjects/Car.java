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
* Description:
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
 *
 * @author sms063
 */
public class Car extends ImageView {

    //private Rectangle theCar;
    private Path thePath;
    private PathTransition pathTransition;

    private static double height = 25;
    private static double width = 50;

    private int gameMode;

    public Car(String fileName, int startX, int startY, int endX, int gameMode) {
        //this.theCar = new Rectangle(30, 15, Color.RED);
        super(fileName);
        this.gameMode = gameMode;
        setFitHeight(height);
        setFitWidth(width);
        //setSmooth(true);
        setX(startX);
        setY(startY);
        createPath(startX, startY, endX);
        createPathTransition(startX, endX);
    }

    private void createPath(int startX, int startY, int endX) {
        this.thePath = new Path();
        this.thePath.getElements().add(new MoveTo(startX, startY));
        this.thePath.getElements().add(new HLineTo(endX));
        this.thePath.setOpacity(0.0);
    }

    private void createPathTransition(int startX, int endX) {
        this.pathTransition = new PathTransition();
        pathTransition.setPath(thePath);
        pathTransition.setNode(this);
        pathTransition.setCycleCount(Animation.INDEFINITE);
        pathTransition.setDuration(
                Duration.seconds(Math.abs(startX - endX) / gameMode));
//        pathTransition.play();
    }

    public void setDuration(Duration d) {
        pathTransition.stop();
        pathTransition.setDuration(d);
        pathTransition.play();
    }

    public Duration getDuration() {
        return pathTransition.getDuration();
    }

    public void setDelay(int seconds) {
        pathTransition.setDelay(Duration.seconds(seconds));
    }

    public void moveCar() {
        pathTransition.play();
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public Path getThePath() {
        return thePath;
    }

}
