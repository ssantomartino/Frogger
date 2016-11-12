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
* Description:
*
* ****************************************
 */
package FroggerObjects;

import javafx.animation.PathTransition;
import javafx.scene.image.ImageView;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

/**
 *
 * @author gmc017
 */
public class Frog extends ImageView {

    private static final int STARTING_X_POS = 350;
    private static final int STARTING_Y_POS = 675;

    private static final double width = 25;
    private static final double height = 25;

    private double XLocation;
    private double YLocation;
    private Path thePath;
    private PathTransition pathTransition;

    public Frog(String fileName, double XLocation,
                double YLocation) {

        super(fileName);
        this.XLocation = XLocation;
        this.YLocation = YLocation;
        setFitHeight(height);
        setFitWidth(width);
        setSmooth(true);
        setTranslateX(this.XLocation);
        setTranslateY(this.YLocation);
    }

    public void createPath(int startX, int startY, int endX) {
        this.thePath = new Path();
        this.thePath.getElements().add(new MoveTo(startX, startY));
        this.thePath.getElements().add(new HLineTo(endX));
        this.thePath.setOpacity(0.0);
    }

    public void createPathTransition(int startX, int endX) {
        this.pathTransition = new PathTransition();
        pathTransition.setPath(thePath);
        pathTransition.setNode(this);
        pathTransition.setCycleCount(1);
        pathTransition.setDuration(
                Duration.seconds(Math.abs(startX - endX) / 50));
    }

    public void setDuration(int seconds) {
        pathTransition.setDuration(Duration.seconds(seconds));
    }

    public void setDelay(int seconds) {
        pathTransition.setDelay(Duration.seconds(seconds));
    }

    public void moveFrog() {
        pathTransition.play();
    }

    public Path getThePath() {
        return thePath;
    }

    public double getXLocation() {
        return this.XLocation;
    }

    public double getYLocation() {
        return this.YLocation;
    }

    public void setXLocation(double XLocation) {
        this.XLocation = XLocation;
    }

    public void setYLocation(double YLocation) {
        this.YLocation = YLocation;
    }

    public static double getWidth() {
        return width;
    }

    public static double getHeight() {
        return height;
    }

    public void restartFrog() {
        setTranslateX(STARTING_X_POS);
        setTranslateY(STARTING_Y_POS);
    }

    public void setPathNull() {
        this.thePath = null;
        this.pathTransition = null;
    }

}
