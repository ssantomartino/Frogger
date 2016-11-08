/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2016
*
* Name: Sam Santomartino, Grace Cook, and John O'Brien
* Date: Nov 3, 2016
* Time: 2:54:17 PM
*
* Project: csci205FinalProject
* Package: FroggerMVC
* File: FroggerModel
* Description:
*
* ****************************************
 */
package FroggerMVC;

import FroggerObjects.Car;

/**
 *
 * @author sms063
 */
public class FroggerModel {

    public Car[] generateCars() {
        int numRoads = 5;
        Car[] carList = new Car[numRoads];
        for (int i = 0; i < numRoads; i++) {
            if (i % 2 == 1) {
                Car car = new Car(0, 380 + (i * 20), 600); //start at x = 0, at height i*20, go until x = 600
                carList[i] = car;
            } else {
                Car car = new Car(500, 380 + (i * 20), -100);
                carList[i] = car;
            }
        }
        return carList;
    }

}
