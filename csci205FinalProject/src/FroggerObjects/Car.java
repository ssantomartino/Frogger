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

import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author sms063
 */
public class Car {

    private Rectangle theCar;
    private Path carPath;

    public Car() {
        this.theCar = new Rectangle(30, 15, Color.RED);
        this.carPath = new Path();

    }

}
