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
import FroggerObjects.HighScores;
import FroggerObjects.WaterObject;
import FroggerObjects.WaterObjectPath;
import java.util.ArrayList;
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
    private MoveWaterObjectsTask theMoveWaterObjectsTask;

    private CarCollisionsTask theCollisionsTask;
    private WaterObjectCollisionTask theDrowningTask;
    private RideTheWaterObjectTask theRidingWaterObjectTask;

    private int numLives;
    private int score;
    private int maxScore;

    private int frogIndex;
    private static final int maxFrogIndex = 11;
    private static final int minFrogIndex = -1;

    private boolean gameOver;

    //private static ArrayList<Integer> highScores = new ArrayList<Integer>();
    private static final int MAX_NUM_SCORES = 10;

    private HighScores highScores;

    private static final double STEP_SIZE_LR = 25;
    private static final double STEP_SIZE_UD = 50;

    private boolean keyControls = true;

    FroggerController(FroggerView theView, FroggerModel theModel) {
        this.theView = theView;
        this.theModel = theModel;

        this.theMoveCarTask = null;
        this.theCollisionsTask = null;
        this.theDrowningTask = null;
        this.theRidingWaterObjectTask = null;

        this.numLives = FroggerView.getNUM_LIVES();
        this.maxScore = 0;
        this.score = 0;
        this.frogIndex = -1;
        this.highScores = new HighScores();
        this.gameOver = false;
    }

    public int getNumLives() {
        return this.numLives;
    }

    public void restartFrogIndex() {
        this.frogIndex = -1;
    }

    public boolean isGameOver() {
        return this.gameOver;
    }

    public void stopRidingWaterObjectTask() {
        this.keyControls = true;
        if (this.theRidingWaterObjectTask != null) {
            this.theRidingWaterObjectTask.stopTask();
        }
    }

    public void updateFrogUpPosition() {

        stopRidingWaterObjectTask();
        this.theView.getTheFrog().setTranslateY(
                this.theView.getTheFrog().getTranslateY() - STEP_SIZE_UD);
        this.theView.getTheFrog().setRotate(0);
        this.frogIndex++;
        this.adjustScore(1);

        if (this.frogIndex > this.minFrogIndex && this.frogIndex < this.theView.getTheRivers().length) {
            System.out.println("Check Car Collision");
            if (this.theCollisionsTask != null) {
                this.theCollisionsTask.stopTask();
            }
            checkCarCollisions();
        } else if (this.frogIndex > this.theView.getTheRivers().length && this.frogIndex < this.maxFrogIndex) {
            System.out.println("Check Water Collision");
            if (this.theDrowningTask != null) {
                this.theDrowningTask.stopTask();
            }
            checkWaterObjectCollision();
        }

    }

    public void updateFrogDownPosition() {
        stopRidingWaterObjectTask();
        this.theView.getTheFrog().setTranslateY(
                this.theView.getTheFrog().getTranslateY() + STEP_SIZE_UD);
        this.theView.getTheFrog().setRotate(180);
        this.frogIndex--;
        this.adjustScore(-1);

        if (this.frogIndex > this.minFrogIndex && this.frogIndex < this.theView.getTheRivers().length) {
            System.out.println("Check Car Collision");
            if (this.theCollisionsTask != null) {
                this.theCollisionsTask.stopTask();
            }
            checkCarCollisions();
        } else if (this.frogIndex > this.theView.getTheRivers().length && this.frogIndex < this.maxFrogIndex) {
            System.out.println("Check Water Collision");
            if (this.theDrowningTask != null) {
                this.theDrowningTask.stopTask();
            }
            checkWaterObjectCollision();
        }
    }

    public void updateFrogRightPosition() {
        //stopRidingWaterObjectTask();
        if (this.theRidingWaterObjectTask != null) {
            this.theRidingWaterObjectTask.pauseTask();
            this.theView.getTheFrog().setTranslateX(
                    this.theView.getTheFrog().getTranslateX() + (STEP_SIZE_LR * 2));

            this.theRidingWaterObjectTask.resumeTask();
        } else {
            this.theView.getTheFrog().setTranslateX(
                    this.theView.getTheFrog().getTranslateX() + STEP_SIZE_LR);
        }

        this.theView.getTheFrog().setRotate(90);
    }

    public void updateFrogLeftPosition() {
        //stopRidingWaterObjectTask();
        //this.theRidingWaterObjectTask.pauseTask();
        if (this.theRidingWaterObjectTask != null) {
            this.theRidingWaterObjectTask.pauseTask();
            this.theView.getTheFrog().setTranslateX(
                    this.theView.getTheFrog().getTranslateX() - (STEP_SIZE_LR * 2));

            this.theRidingWaterObjectTask.resumeTask();
        } else {
            this.theView.getTheFrog().setTranslateX(
                    this.theView.getTheFrog().getTranslateX() - STEP_SIZE_LR);
        }

        this.theView.getTheFrog().setRotate(270);
        //this.theRidingWaterObjectTask.resumeTask();
    }

    public boolean checkBottomBound() {
        int yMax = theView.getRootYMax() - 50;
        Bounds frogBounds = theView.getTheFrog().localToScene(
                theView.getTheFrog().getBoundsInLocal());
        int frogYMax = (int) frogBounds.getMaxY();
        //System.out.println(frogYMax);
        if (yMax - frogYMax < 25) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkTopBound() {
        int yMin = theView.getRootYMin() + 50;
        Bounds frogBounds = theView.getTheFrog().localToScene(
                theView.getTheFrog().getBoundsInLocal());
        int frogYMin = (int) frogBounds.getMinY();
        //System.out.println(frogYMin);
        if (frogYMin - yMin < 40) {
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
        //System.out.println(xMax + "," + frogXMax);
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
        //System.out.println(frogXMin);
        if (frogXMin - xMin < 10) {
            return false;
        } else {
            return true;
        }
    }

    public void startTheCars() {
        CarPath[] theRoad = this.theView.getTheRoads();
        for (CarPath path : theRoad) {
            Car[] cars = path.getTheCars();
            this.theMoveCarTask = new MoveCarsTask(cars);
            Thread th = new Thread(theMoveCarTask);
            th.setDaemon(true);
            th.start();
        }
    }

    public void startTheWaterObjects() {
        WaterObjectPath[] theRiver = this.theView.getTheRivers();
        for (WaterObjectPath path : theRiver) {
            WaterObject[] waterObjects = path.getTheObjects();
            this.theMoveWaterObjectsTask = new MoveWaterObjectsTask(waterObjects);
            Thread th = new Thread(theMoveWaterObjectsTask);
            th.setDaemon(true);
            th.start();
        }
    }

    public void checkCarCollisions() {
        CarPath[] theRoad = this.theView.getTheRoads();
        CarPath path = theRoad[this.frogIndex];
        Car[] cars = path.getTheCars();
//        for (CarPath path : theRoad) {
//            Car[] cars = path.getTheCars();
//            this.theCollisionsTask = new CheckCollisionsTask(cars);
//            Thread th = new Thread(theCollisionsTask);
//            th.setDaemon(true);
//            th.start();
//        }

        FroggerController.this.theCollisionsTask = new CarCollisionsTask(
                cars);
        Thread th = new Thread(theCollisionsTask);
        th.setDaemon(true);
        th.start();

//        new AnimationTimer() {
//            @Override
//            public void handle(long now) {
//                FroggerController.this.theCollisionsTask = new CarCollisionsTask(
//                        cars);
//                Thread th = new Thread(theCollisionsTask);
//                th.setDaemon(true);
//                th.start();
//            }
//
//        }.start();
    }

    public void checkWaterObjectCollision() {
        WaterObjectPath[] theRiver = this.theView.getTheRivers();
        WaterObjectPath path = theRiver[theRiver.length - (this.maxFrogIndex - this.frogIndex)];

        this.theDrowningTask = new WaterObjectCollisionTask(path);
        Thread th = new Thread(this.theDrowningTask);
        th.setDaemon(true);
        th.start();

//        for (WaterObjectPath path : theRiver) {
//            this.theDrowningTask = new WaterObjectCollisionTask(path);
//            Thread th = new Thread(this.theDrowningTask);
//            th.setDaemon(true);
//            th.start();
//
//        }
    }

    public void setKeyControlsFalse() {
        this.keyControls = false;
    }

    public boolean getKeyControls() {
        return this.keyControls;
    }

    public void removeLife() {
        this.numLives--;
    }

    public void endGame() {
        System.out.println("Final Score: " + this.score);
        ArrayList<Integer> theScores = this.highScores.insertScore(this.score);
        this.highScores.saveScores();
        this.gameOver = true;
        this.theView.endGame(this.score, theScores);

    }

    private void adjustScore(int i) {
        this.score += i * 10;
        if (this.score > this.maxScore) {
            this.maxScore = this.score;
            theView.updateScore(maxScore);
        }
    }

    public int getMaxScore() {
        return maxScore;
    }

    class MoveWaterObjectsTask extends Task<Integer> {

        private final WaterObject[] waterObjects;

        public MoveWaterObjectsTask(WaterObject[] waterObjects) {
            this.waterObjects = waterObjects;
        }

        @Override
        protected Integer call() throws Exception {
            for (WaterObject waterObject : this.waterObjects) {

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        waterObject.moveWaterObject();
                    }
                });

                int base = 3000;
                double rand = Math.random() * 1000;
                int sleepTime = (int) (base + rand);
                Thread.sleep(sleepTime);
            }

            return 1;
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
                int base = 3000;
                double rand = Math.random() * 1000;
                int sleepTime = (int) (base + rand);
                Thread.sleep(sleepTime);
            }

            return 1;
        }

    }

    class CarCollisionsTask extends Task<Integer> {

        private final Car[] cars;
        private boolean stopTask;

        /**
         * Construct the task with the model and cars to run through
         */
        public CarCollisionsTask(Car[] cars) {
            this.cars = cars;
            this.stopTask = false;
        }

        @Override
        protected Integer call() throws Exception {

            while (!stopTask) {
                Bounds frogBounds = FroggerController.this.theView.getTheFrog().getBoundsInParent();
                for (Car car : this.cars) {
                    if (car.getBoundsInParent().intersects(frogBounds)) {
                        //FroggerController.this.numLives--;
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                FroggerController.this.restartFrogIndex();
                                FroggerController.this.theView.getTheFrog().restartFrog();
                                FroggerController.this.theView.removeNextLife();
                                FroggerController.this.removeLife();
                                if (FroggerController.this.numLives <= 0) {
                                    FroggerController.this.endGame();
                                }
                            }
                        });
                    }
                    Thread.sleep(1);
                }

            }

            return 1;
        }

        public void stopTask() {
            this.stopTask = true;
        }

    }

    class WaterObjectCollisionTask extends Task<Integer> {

        private final WaterObjectPath waterPath;
        private boolean stopTask;

        /**
         * Construct the task with the model and the water path to check for
         * collisions
         */
        public WaterObjectCollisionTask(WaterObjectPath path) {
            this.waterPath = path;
            this.stopTask = false;
        }

        @Override
        protected Integer call() throws Exception {

            while (!stopTask) {
                if (FroggerController.this.theView.getTheFrog().getisOnWaterObject()) {
                    return 1;
                }

                Bounds frogBoundsParent = FroggerController.this.theView.getTheFrog().getBoundsInParent();

                boolean isOnALog = false;
                WaterObject[] waterObjects = this.waterPath.getTheObjects();
                for (WaterObject waterObject : waterObjects) {
                    if (waterObject.getBoundsInParent().intersects(
                            frogBoundsParent)) {
                        FroggerController.this.setKeyControlsFalse();
                        FroggerController.this.theView.getTheFrog().setisOnWaterObjectTrue();
                        FroggerController.this.theRidingWaterObjectTask = new RideTheWaterObjectTask(
                                waterObject);
                        Thread th = new Thread(
                                FroggerController.this.theRidingWaterObjectTask);
                        th.setDaemon(true);
                        th.start();
                        System.out.println("intersecting log/turtle");
                        return 1;

                    }

                    //Thread.sleep(1);
                }
                if (FroggerController.this.frogIndex >= FroggerController.this.maxFrogIndex - FroggerController.this.theView.getTheRivers().length) {
                    System.out.println(
                            "Hit water" + FroggerController.this.frogIndex);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            FroggerController.this.restartFrogIndex();
                            FroggerController.this.theView.getTheFrog().restartFrog();
                            FroggerController.this.theView.removeNextLife();
                            FroggerController.this.removeLife();
                            if (FroggerController.this.numLives <= 0) {
                                FroggerController.this.endGame();
                            }
                        }
                    });

                    Thread.sleep(1);
                }
            }

            return 1;
        }

        public void stopTask() {
            this.stopTask = true;
        }

    }

    class RideTheWaterObjectTask extends Task<Integer> {
        private final WaterObject theObject;
        private boolean isPaused;

        /**
         * Construct the task with the model and the water path to check for
         * collisions
         */
        public RideTheWaterObjectTask(WaterObject theObj) {
            this.theObject = theObj;
            this.isPaused = false;

        }

        @Override
        protected Integer call() throws Exception {

            Bounds frogBounds = FroggerController.this.theView.getTheFrog().localToScene(
                    FroggerController.this.theView.getTheFrog().getBoundsInLocal());
            Bounds objectBounds = this.theObject.localToScene(
                    this.theObject.getBoundsInLocal());

            //double offset = frogBounds.getMaxX() - objectBounds.getMaxX();
            double offset = objectBounds.getMaxX() - frogBounds.getMaxX();

            while (true && !FroggerController.this.getKeyControls()) {

                while (this.isPaused) {
                    System.out.println("Paused");
                    Thread.sleep(250);
                    //Thread.yield();
                    if (isCancelled()) {
                        break;
                    }
                }

                Bounds objectPosition = this.theObject.localToScene(
                        this.theObject.getBoundsInLocal());
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        FroggerController.this.theView.getTheFrog().setXTranslation(
                                objectPosition.getMaxX() - offset);
                    }
                });
                Thread.sleep(1);

                if (isCancelled()) {
                    break;
                }
            }
            return 1;
        }

        /**
         * Pauses the task
         */
        public void pauseTask() {
            this.isPaused = true;
        }

        /**
         * Resumes the task
         */
        public void resumeTask() {
            this.isPaused = false;
        }

        public void stopTask() {
            //System.out.println("cancelled");
            FroggerController.this.theView.getTheFrog().setisOnWaterObjectFalse();
            this.cancel();
        }
    }

}
