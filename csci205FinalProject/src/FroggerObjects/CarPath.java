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

    private static final int NUM_CARS_ON_ROAD = 5;
    private static final String[] carFilesL = {"Car.png", "Audi.png", "taxi.png"};
    private static final String[] carFilesR = {"Police.png", "Black_viper.png", "Mini_truck.png"};
    private Car[] theCars;
    private boolean faceRight;

    public CarPath(int startX, int startY, int endX, boolean faceR) {
        this.theCars = new Car[NUM_CARS_ON_ROAD];
        this.faceRight = faceR;
        this.addCars(startX, startY, endX);
    }

    private void addCars(int startX, int startY, int endX) {

        for (int i = 0; i < this.theCars.length; i++) {
            String carType = getCarType();
            Car car = new Car(carType, startX, startY, endX);
            this.theCars[i] = car;
        }
    }

    private String getCarType() {
        if (this.faceRight) {
            return this.carFilesR[new Random().nextInt(
                    this.carFilesR.length)];
        } else {
            return this.carFilesL[new Random().nextInt(
                    this.carFilesL.length)];
        }
    }

    public Car[] getTheCars() {
        return theCars;
    }

}
