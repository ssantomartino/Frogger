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
 * @author gmc017
 */
public class WaterObject extends ImageView {

    private Path thePath;
    private PathTransition pathTransition;
    private double height;
    private double width;

    public WaterObject(String fileName, int startX, int startY, int endX,
                       double height, double width) {
        super(fileName);
        setFitHeight(height);
        setFitWidth(width);
        this.height = height;
        this.width = width;
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
                Duration.seconds(Math.abs(startX - endX) / 50));
        pathTransition.play();
    }

    public void setDuration(int seconds) {
        pathTransition.setDuration(Duration.seconds(seconds));
    }

    public void setDelay(int seconds) {
        pathTransition.setDelay(Duration.seconds(seconds));
    }

    public void moveWaterObject() {
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
