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
package FroggerMVC;

import javafx.scene.image.ImageView;

/**
 *
 * @author gmc017
 */
public class Frog extends ImageView {

    private double XLocation;
    private double YLocation;

    public Frog(double width, double height, String fileName, double XLocation,
                double YLocation) {

        super(fileName);
        this.XLocation = XLocation;
        this.YLocation = YLocation;
        setFitHeight(height);
        setFitWidth(width);
        setTranslateX(this.XLocation);
        setTranslateY(this.YLocation);

    }

    public double getXLocation() {
        return XLocation;
    }

    public double getYLocation() {
        return YLocation;
    }

    public void setXLocation(double XLocation) {
        this.XLocation = XLocation;
    }

    public void setYLocation(double YLocation) {
        this.YLocation = YLocation;
    }

}
