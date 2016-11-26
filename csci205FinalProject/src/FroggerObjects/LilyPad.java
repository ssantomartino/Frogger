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
* Description:
*
* ****************************************
 */
package FroggerObjects;

import javafx.scene.image.ImageView;

/**
 *
 * @author John
 */
public class LilyPad extends ImageView {

    private static final double HEIGHT = 50;
    private static final double WIDTH = 50;
    private double xLocation;
    private static final double yLocation = 55;
    private static final String fileName = "lilyPad.jpg";

    public LilyPad(double xLocation) {
        super(fileName);
        setFitHeight(HEIGHT);
        setFitWidth(WIDTH);
        this.xLocation = xLocation;
        setPosition();
    }

    private void setPosition() {
        setTranslateX(xLocation);
        setTranslateY(yLocation);
    }

    public double getxLocation() {
        return xLocation;
    }

    public void setxLocation(double xLocation) {
        this.xLocation = xLocation;
    }

    public double getyLocation() {
        return yLocation;
    }

}
