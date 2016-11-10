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
import java.util.Random;

/**
 *
 * @author sms063
 */
public class FroggerModel {

    private static final String[] carFiles = {"Audi.png", "Black_viper.png", "Car.png", "Mini_truck.png", "Police.png", "taxi.png", "truck.png"};
//    private static final String[] carFiles = {"blackCar.png"};

    public Car[][] generateCars() {
        int numRoads = 5;
        int numCars = 10;
        Car[][] carList = new Car[numRoads][numCars];
        for (int i = 1; i <= numRoads; i++) {
            int startX;
            if (i % 2 == 1) {
                startX = 0;
            } else {
                startX = 700;
            }
            for (int j = 1; j <= numCars; j++) {
                String carType = this.carFiles[new Random().nextInt(
                        this.carFiles.length)];
                if (i % 2 == 1) {
                    startX += -275;
                    int startY = 675 - i * 25;
                    int endX = startX + numCars * 200;
                    Car car = new Car(carType, startX, startY, endX);
                    carList[i - 1][j - 1] = car;
                } else {
                    startX += 275;
                    int startY = 675 - i * 25;
                    int endX = startX - (numCars * 200);
                    Car car = new Car(carType, startX, startY, endX);
                    carList[i - 1][j - 1] = car;
                }
            }
        }
        return carList;
    }

}
