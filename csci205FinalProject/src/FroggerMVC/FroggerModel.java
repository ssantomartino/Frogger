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

import FroggerObjects.CarPath;
import FroggerObjects.LilyPad;
import FroggerObjects.WaterObjectPath;

/**
 *
 * @author sms063
 */
public class FroggerModel {

    private static final String[] carFilesL = {"Car.png", "Police.png", "taxi.png"};
    private static final String[] carFilesR = {"Audi.png", "Black_viper.png", "Mini_truck.png"};
//    private static final String[] carFiles = {"blackCar.png"};

    public CarPath[] generateCarPaths() {

        int numRoads = 5;
        CarPath[] paths = new CarPath[numRoads];

        for (int i = 0; i < numRoads; i++) {
            int startX;
            int startY;
            int endX;

            if (i % 2 == 1) {
                startX = -50;
                startY = 637 - i * 50;
                endX = 750;
                paths[i] = new CarPath(startX, startY, endX, true);
            } else {
                startX = 750;
                startY = 637 - i * 50;
                endX = -50;
                paths[i] = new CarPath(startX, startY, endX, false);
            }

        }
        return paths;
    }

    public WaterObjectPath[] generateWaterObjectPaths() {

        int numRivers = 5;
        WaterObjectPath[] paths = new WaterObjectPath[numRivers];

        for (int i = 0; i < numRivers; i++) {
            int startX;
            int startY;
            int endX;

            if (i % 2 == 1) {
                startX = -70;
                startY = 337 - i * 50;
                endX = 750;
                paths[i] = new WaterObjectPath(startX, startY, endX, true);
            } else {
                startX = 770;
                startY = 337 - i * 50;
                endX = -50;
                paths[i] = new WaterObjectPath(startX, startY, endX, false);
            }

        }
        return paths;
    }

    public LilyPad[] generateLilyPads() {
        int numPads = 5;
        LilyPad[] lilyPads = new LilyPad[numPads];

        for (int i = 0; i < numPads; i++) {
            double xLocation = (i + 1) * (700 / 6) - 25;
            lilyPads[i] = new LilyPad(xLocation);
        }
        return lilyPads;
    }

}
