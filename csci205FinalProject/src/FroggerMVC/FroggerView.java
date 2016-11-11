/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2016
*
* Name: Sam Santomartino, Grace Cook, and John O'Brien
* Date: Nov 2, 2016
* Time: 5:19:31 PM
*
* Project: csci205FinalProject
* Package:
* File: FroggerView
* Description:
*
* ****************************************
 */
package FroggerMVC;

import FroggerObjects.Car;
import FroggerObjects.CarPath;
import FroggerObjects.Frog;
import javafx.scene.Group;
import javafx.scene.layout.Pane;

/**
 *
 * @author jeo008
 */
class FroggerView {

    private FroggerModel theModel;
    private Pane root;
    private Frog theFrog;
    private Car[][] theCars;
    private CarPath[] theRoad;
    private Group carGroup;

    public FroggerView(FroggerModel theModel) {
        this.theModel = theModel;
        root = new Pane();

        root.setPrefWidth(700);
        root.setPrefHeight(700);

        this.theFrog = new Frog("basicFrog.png", root.getPrefWidth() / 2,
                                root.getPrefHeight() - theFrog.getHeight());
        System.out.println(theFrog.getHeight() + "," + theFrog.getWidth());

        root.getChildren().add(theFrog);
        //addCars();
        addPaths();

    }

    private void addPaths() {
        this.theRoad = theModel.generateCarPaths();
        this.carGroup = new Group();
        for (CarPath path : this.theRoad) {
            Car[] cars = path.getTheCars();
            for (Car car : cars) {
                carGroup.getChildren().add(car.getThePath());
                carGroup.getChildren().add(car);
            }
        }
        root.getChildren().add(carGroup);
    }

    private void addCars() {
        this.theCars = theModel.generateCars();
        this.carGroup = new Group();
        for (Car[] carList : theCars) {
            for (Car car : carList) {
                carGroup.getChildren().add(car.getThePath());
                carGroup.getChildren().add(car);
            }
        }
        root.getChildren().add(carGroup);
    }

    private void removeCars() {
        this.root.getChildren().remove(this.carGroup);
    }

    public void resetRoad() {
        this.removeCars();
        this.addCars();
    }

    public Pane getRootNode() {
        return root;
    }

    public int getRootXMin() {
        return 0;
    }

    public int getRootXMax() {
        return (int) this.root.getPrefWidth();
    }

    public int getRootYMin() {
        return (int) this.root.getBoundsInLocal().getMinY();
    }

    public int getRootYMax() {
        return (int) this.root.getBoundsInLocal().getMaxY();
    }

    public Frog getTheFrog() {
        return this.theFrog;
    }

    public Car[][] getTheCars() {
        return this.theCars;
    }

    public CarPath[] getTheRoad() {
        return theRoad;
    }

}
