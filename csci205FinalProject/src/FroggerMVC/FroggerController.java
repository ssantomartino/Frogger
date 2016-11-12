/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2016
*
* Name: Sam Santomartino, Grace Cook, and John O'Brien
* Date: Nov 2, 2016
* Time: 5:19:44 PM
*
* Project: csci205FinalProject
* Package:
* File: FroggerController
* Description:
*
* ****************************************
 */
package FroggerMVC;

import FroggerObjects.Car;
import FroggerObjects.CarPath;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Bounds;

/**
 *
 * @author jeo008
 */
class FroggerController {

    private FroggerView theView;
    private FroggerModel theModel;
    private MoveCarsTask theMoveCarTask;
    private CheckCollisionsTask theCollisionsTask;

    private static final double STEP_SIZE = 25;

    FroggerController(FroggerView theView, FroggerModel theModel) {
        this.theView = theView;
        this.theModel = theModel;
        this.theMoveCarTask = null;
        this.theCollisionsTask = null;

    }

    public void updateFrogUpPosition() {
        this.theView.getTheFrog().setTranslateY(
                this.theView.getTheFrog().getTranslateY() - STEP_SIZE);
        this.theView.getTheFrog().setRotate(0);
    }

    public void updateFrogDownPosition() {
        this.theView.getTheFrog().setTranslateY(
                this.theView.getTheFrog().getTranslateY() + STEP_SIZE);
        this.theView.getTheFrog().setRotate(180);
    }

    public void updateFrogRightPosition() {
        this.theView.getTheFrog().setTranslateX(
                this.theView.getTheFrog().getTranslateX() + STEP_SIZE);

        this.theView.getTheFrog().setRotate(90);
    }

    public void updateFrogLeftPosition() {
        this.theView.getTheFrog().setTranslateX(
                this.theView.getTheFrog().getTranslateX() - STEP_SIZE);
        this.theView.getTheFrog().setRotate(270);
    }

    public boolean checkBottomBound() {
        int yMax = theView.getRootYMax();
        Bounds frogBounds = theView.getTheFrog().localToScene(
                theView.getTheFrog().getBoundsInLocal());
        int frogYMax = (int) frogBounds.getMaxY();
        System.out.println(frogYMax);
        if (yMax - frogYMax < 10) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkTopBound() {
        int yMin = theView.getRootYMin();
        Bounds frogBounds = theView.getTheFrog().localToScene(
                theView.getTheFrog().getBoundsInLocal());
        int frogYMin = (int) frogBounds.getMinY();
        System.out.println(frogYMin);
        if (frogYMin - yMin < 10) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkRightBound() {
        int xMax = theView.getRootXMax();
        Bounds frogBounds = theView.getTheFrog().localToScene(
                theView.getTheFrog().getBoundsInLocal());
        int frogXMax = (int) frogBounds.getMaxX();
        System.out.println(xMax + "," + frogXMax);
        if (xMax - frogXMax < 10) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkLeftBound() {
        int xMin = theView.getRootXMin();
        Bounds frogBounds = theView.getTheFrog().localToScene(
                theView.getTheFrog().getBoundsInLocal());
        int frogXMin = (int) frogBounds.getMinX();
        System.out.println(frogXMin);
        if (frogXMin - xMin < 10) {
            return false;
        } else {
            return true;
        }
    }

    public void startCars() {
        Task<Integer> t = new Task<Integer>() {
            @Override
            protected Integer call() throws Exception {

                CarPath[] theRoad = FroggerController.this.theView.getTheRoad();
                for (CarPath path : theRoad) {
                    Car[] cars = path.getTheCars();
                    for (Car car : cars) {
                        car.moveCar();
                        Thread.sleep(3000);
                    }
                }
                return 1;
            }
        };
        Thread thread = new Thread(t);
        thread.start();
    }

    public void startTheCars() {
        CarPath[] theRoad = this.theView.getTheRoad();
        for (CarPath path : theRoad) {
            Car[] cars = path.getTheCars();
            this.theMoveCarTask = new MoveCarsTask(cars);
            Thread th = new Thread(theMoveCarTask);
            th.setDaemon(true);
            th.start();
        }
    }

    public void moveCars() {
        Task<Integer> t = new Task<Integer>() {
            @Override
            protected Integer call() throws Exception {

                Car[][] carList = FroggerController.this.theView.getTheCars();
                for (Car[] carPath : carList) {
                    for (Car car : carPath) {
                        car.moveCar();
                        Thread.sleep(2000);
                    }
                    //Thread.sleep(2000);
                }
                return 1;
            }
        };
        Thread thread = new Thread(t);
        thread.start();
    }

    public void checkCollisions() {

        Task<Integer> t = new Task<Integer>() {
            @Override
            protected Integer call() throws Exception {
                Car[][] cars = FroggerController.this.theView.getTheCars();
                Bounds frogBounds = FroggerController.this.theView.getTheFrog().getBoundsInParent();
//                Bounds frogBounds = theView.getTheFrog().localToScene(
//                        theView.getTheFrog().getBoundsInLocal());
                for (Car[] carList : cars) {
                    for (Car car : carList) {
//                        Bounds carBounds = car.localToScene(
//                                car.getBoundsInLocal());
                        if (car.getBoundsInParent().intersects(frogBounds)) {
                            //return 1;
                            FroggerController.this.theView.getTheFrog().restartFrog();
                            //theView.resetRoad();
                        }

//                        if (frogBounds.intersects(carBounds) || carBounds.intersects(
//                                frogBounds)) {
//                            //return 1;
//                            theView.getTheFrog().restartFrog();
//                            //theView.resetRoad();
//                        }
                    }
                }
                return -1;
            }

        };
        Thread thread = new Thread(t);
        thread.start();
    }

    public void checkCarCollisions() {
        CarPath[] theRoad = this.theView.getTheRoad();
        for (CarPath path : theRoad) {
            Car[] cars = path.getTheCars();
            this.theCollisionsTask = new CheckCollisionsTask(cars);
            Thread th = new Thread(theCollisionsTask);
            th.setDaemon(true);
            th.start();
        }
    }

    class MoveCarsTask extends Task<Integer> {

        private final Car[] cars;

        /**
         * Construct the task with the model and cars to run through
         */
        public MoveCarsTask(Car[] cars) {
            this.cars = cars;
        }

        @Override
        protected Integer call() throws Exception {
            for (Car car : this.cars) {

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        car.moveCar();

                    }
                });

                Thread.sleep(3000);
            }

            return 1;
        }

    }

    class CheckCollisionsTask extends Task<Integer> {

        private final Car[] cars;

        /**
         * Construct the task with the model and cars to run through
         */
        public CheckCollisionsTask(Car[] cars) {
            this.cars = cars;
        }

        @Override
        protected Integer call() throws Exception {
            Bounds frogBounds = FroggerController.this.theView.getTheFrog().getBoundsInParent();
            for (Car car : this.cars) {
                if (car.getBoundsInParent().intersects(frogBounds)) {

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            FroggerController.this.theView.getTheFrog().restartFrog();
                        }
                    });
                }
                Thread.sleep(1);
            }
            return 1;
        }

    }

}
