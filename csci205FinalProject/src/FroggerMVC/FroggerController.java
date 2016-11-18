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

    private CheckCollisionsTask theCollisionsTask;
    private CheckDrowningTask theDrowningTask;
    private RideTheWaterObjectTask theRidingWaterObjectTask;

    private int numLives;
    private int score;
    private int maxScore;

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
        this.highScores = new HighScores();
        this.gameOver = false;
    }

    public int getNumLives() {
        return this.numLives;
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
        this.adjustScore(1);
    }

    public void updateFrogDownPosition() {
        stopRidingWaterObjectTask();
        this.theView.getTheFrog().setTranslateY(
                this.theView.getTheFrog().getTranslateY() + STEP_SIZE_UD);
        this.theView.getTheFrog().setRotate(180);
        this.adjustScore(-1);
    }

    public void updateFrogRightPosition() {
        stopRidingWaterObjectTask();
        //this.theRidingWaterObjectTask.pauseTask();
        this.theView.getTheFrog().setTranslateX(
                this.theView.getTheFrog().getTranslateX() + STEP_SIZE_LR);

        this.theView.getTheFrog().setRotate(90);
        //this.theRidingWaterObjectTask.resumeTask();
    }

    public void updateFrogLeftPosition() {
        stopRidingWaterObjectTask();
        //this.theRidingWaterObjectTask.pauseTask();
        this.theView.getTheFrog().setTranslateX(
                this.theView.getTheFrog().getTranslateX() - STEP_SIZE_LR);
        this.theView.getTheFrog().setRotate(270);
        //this.theRidingWaterObjectTask.resumeTask();
    }

    public boolean checkBottomBound() {
        int yMax = theView.getRootYMax();
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
        int yMin = theView.getRootYMin();
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
        CarPath[] theRoad = this.theView.getTheRoad();
        for (CarPath path : theRoad) {
            Car[] cars = path.getTheCars();
            this.theMoveCarTask = new MoveCarsTask(cars);
            Thread th = new Thread(theMoveCarTask);
            th.setDaemon(true);
            th.start();
        }
    }

    public void startTheWaterObjects() {
        WaterObjectPath[] theRiver = this.theView.getTheRiver();
        for (WaterObjectPath path : theRiver) {
            WaterObject[] waterObjects = path.getTheObjects();
            this.theMoveWaterObjectsTask = new MoveWaterObjectsTask(waterObjects);
            Thread th = new Thread(theMoveWaterObjectsTask);
            th.setDaemon(true);
            th.start();
        }
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

    public void checkFellInWater() {
        WaterObjectPath[] theRiver = this.theView.getTheRiver();
        for (WaterObjectPath path : theRiver) {
            this.theDrowningTask = new CheckDrowningTask(path);
            Thread th = new Thread(this.theDrowningTask);
            th.setDaemon(true);
            th.start();

        }
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
                    //FroggerController.this.numLives--;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
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
            return 1;
        }

    }

    class CheckDrowningTask extends Task<Integer> {

        private final WaterObjectPath waterPath;

        /**
         * Construct the task with the model and the water path to check for
         * collisions
         */
        public CheckDrowningTask(WaterObjectPath path) {
            this.waterPath = path;
        }

        @Override
        protected Integer call() throws Exception {

            if (FroggerController.this.theView.getTheFrog().getisOnWaterObject()) {
                return 1;
            }

            Bounds frogBoundsParent = FroggerController.this.theView.getTheFrog().getBoundsInParent();

            boolean isOnALog = false;
            WaterObject[] waterObjects = this.waterPath.getTheObjects();
            for (WaterObject waterObject : waterObjects) {
                if (waterObject.getBoundsInParent().intersects(frogBoundsParent)) {
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
            if ((!isOnALog) && this.waterPath.getTheRiver().getBoundsInParent().intersects(
                    frogBoundsParent)) {
                System.out.println("Hit water");
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
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

            return 1;
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
