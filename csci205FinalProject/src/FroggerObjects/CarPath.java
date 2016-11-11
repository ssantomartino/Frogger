/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2016
*
* Name: Sam Santomartino, Grace Cook, and John O'Brien
* Date: Nov 10, 2016
* Time: 10:10:32 PM
*
* Project: csci205FinalProject
* Package: FroggerObjects
* File: CarPath
* Description:
*
* ****************************************
 */
package FroggerObjects;

import java.util.Random;

/**
 *
 * @author sms063
 */
public class CarPath {

    private static final int NUM_CARS_ON_ROAD = 8;
    //private static final String[] carFilesL = {"Car.png", "Police.png", "taxi.png"};
    private static final String[] carFilesR = {"Audi.png", "Black_viper.png", "Mini_truck.png"};
    private Car[] theCars;

    public CarPath(int startX, int startY, int endX) {
        this.theCars = new Car[NUM_CARS_ON_ROAD];
        this.addCars(startX, startY, endX);
    }

    private void addCars(int startX, int startY, int endX) {

        for (int i = 0; i < this.theCars.length; i++) {
            String carType = this.carFilesR[new Random().nextInt(
                    this.carFilesR.length)];
            Car car = new Car(carType, startX, startY, endX);
            this.theCars[i] = car;
        }
    }

    public Car[] getTheCars() {
        return theCars;
    }

}
