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
* Description: Controller class of MVC model
*
* ****************************************
 */
package FroggerMVC;

import FroggerObjects.Car;
import FroggerObjects.CarPath;
import FroggerObjects.HighScores;
import FroggerObjects.LilyPad;
import FroggerObjects.WaterObject;
import FroggerObjects.WaterObjectPath;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Bounds;

/**
 * Description: Controller class of MVC model
 *
 * @author jeo008, sms063, gmc017
 */
class FroggerController {

    private FroggerView theView;
    private FroggerModel theModel;
    /*
    The FroggerMainMenu allows user to chose gammer level and start game
     */
    private FroggerMainMenu theMainMenu;

    /*
    The Task associated with sending the cars across the screen
     */
    private MoveCarsTask theMoveCarTask;
    /*
    The Task associated with sending the water objects across the screen
     */
    private MoveWaterObjectsTask theMoveWaterObjectsTask;

    /*
    The Task associated with checking for collisions between cars and the frog
     */
    private CarCollisionsTask carCollisionsTask;
    /*
    The Task associated with checking for collisions between water objects and the frog
     */
    private WaterObjectCollisionTask waterObjectCollisionsTask;
    /*
    The Task associated with updating the frog's position along with
    the associated water object that it is "riding"
     */
    private RideTheWaterObjectTask ridingWaterObjectTask;
    /*
    The Task associated with checking for to see if the frog successfully jumped onto a lily pad
     */
    private LilyPadCollisionsTask lilyPadCollisionsTask;

    /*
    the number of lives remaining in a play
     */
    private int numLives;
    /*
    the accumulated score of a play
     */
    private int score;
    /*
    the max score of all plays
     */
    private int maxScore;

    /*
    the indexed position of the frog on the game board
     */
    private int frogIndex;
    /*
    the maximum frog index that the frog can attain (top bound)
     */
    private static final int maxFrogIndex = 11;
    /*
    the minimum frog index the frog can attain (bottom bound)
     */
    private static final int minFrogIndex = -1;

    /*
    Flag variable changes to true when all lives are lost and the game is over
     */
    private boolean gameOver;

    /*
    the maximum number of high scores being stored
     */
    private static final int MAX_NUM_SCORES = 10;

    /*
    high scores being stored
     */
    private HighScores highScores;

    /*
    left and right arrow key step size
     */
    private static final double STEP_SIZE_LR = 25;
    /*
    up and down arrow key step size
     */
    private static final double STEP_SIZE_UD = 50;

    /*
    Flag varibale is true when the user is controlling the frog with
    the key controlls, false when the frog is riding a water object
     */
    private boolean controlledByKeys = true;

    /**
     * Controller Constructor initializes the objects of the class
     *
     * @param theView the View
     * @param theModel the Model
     * @param theMainMenu the Main Menu
     */
    FroggerController(FroggerView theView, FroggerModel theModel,
                      FroggerMainMenu theMainMenu) {
        this.theView = theView;
        this.theModel = theModel;
        this.theMainMenu = theMainMenu;

        this.theMoveCarTask = null;
        this.carCollisionsTask = null;
        this.waterObjectCollisionsTask = null;
        this.ridingWaterObjectTask = null;
        this.lilyPadCollisionsTask = null;

        this.numLives = FroggerView.getNUM_LIVES();
        this.maxScore = 0;
        this.score = 0;
        this.frogIndex = -1;
        this.highScores = new HighScores();
        this.gameOver = false;
    }

    /**
     * Getter returns the number of lives left in this play of the game
     *
     * @return int numLives
     */
    public int getNumLives() {
        return this.numLives;
    }

    /**
     * Restarts the index counter that keeps track of where the frog is on the
     * board where -1 is the starting (bottom) position and 11 is the top of the
     * board
     */
    public void restartFrogIndex() {
        this.frogIndex = -1;
    }

    /**
     * returns true if the game is over- due to a loss of all lives- false
     * otherwise
     *
     * @return boolean gameOver
     */
    public boolean isGameOver() {
        return this.gameOver;
    }

    /**
     * If the frog is currently riding a water object, the task is stopped
     * regardless of riding status, the boolean controlled by keys is now true,
     * indicating that either the up or down arrow key has been pressed and the
     * frog is now under user control again (rather than riding a water object)
     */
    public void stopRidingWaterObjectTask() {
        this.controlledByKeys = true;
        if (this.ridingWaterObjectTask != null) {
            this.ridingWaterObjectTask.stopTask();
        }
    }

    /**
     * Updates the frog position UP by the STEP_SIZE_UD value, launches the
     * correct collision checker, depending on the frog's index position on the
     * board, and updates the score
     *
     */
    public void updateFrogUpPosition() {

        stopRidingWaterObjectTask();
        this.theView.getTheFrog().setTranslateY(
                this.theView.getTheFrog().getTranslateY() - STEP_SIZE_UD);
        this.theView.getTheFrog().setRotate(0);
        this.frogIndex++;
        this.adjustScore(1);

        if (this.frogIndex > this.minFrogIndex && this.frogIndex < this.theView.getTheRivers().length) {
            //System.out.println("Check Car Collision");
            if (this.carCollisionsTask != null) {
                this.carCollisionsTask.stopTask();
            }
            if (!isGameOver()) {
                checkCarCollisions();
            }

        } else if (this.frogIndex > this.theView.getTheRivers().length && this.frogIndex < this.maxFrogIndex) {
            //System.out.println("Check Water Collision");
            if (this.waterObjectCollisionsTask != null) {
                this.waterObjectCollisionsTask.stopTask();
            }
            if (!isGameOver()) {
                checkWaterObjectCollision();
            }

        } else if (this.frogIndex == this.maxFrogIndex) {
            checkLilyCollisions();
        }

    }

    /**
     * Updates the frog position DOWN by the STEP_SIZE_UD value, launches the
     * correct collision checker, depending on the frog's index position on the
     * board, and updates the score
     *
     */
    public void updateFrogDownPosition() {
        stopRidingWaterObjectTask();
        this.theView.getTheFrog().setTranslateY(
                this.theView.getTheFrog().getTranslateY() + STEP_SIZE_UD);
        this.theView.getTheFrog().setRotate(180);
        this.frogIndex--;
        this.adjustScore(-1);

        if (this.frogIndex > this.minFrogIndex && this.frogIndex < this.theView.getTheRivers().length) {
            //System.out.println("Check Car Collision");
            if (this.carCollisionsTask != null) {
                this.carCollisionsTask.stopTask();
            }
            if (!isGameOver()) {
                checkCarCollisions();
            }

        } else if (this.frogIndex > this.theView.getTheRivers().length && this.frogIndex < this.maxFrogIndex) {
            //System.out.println("Check Water Collision");
            if (this.waterObjectCollisionsTask != null && !isGameOver()) {
                this.waterObjectCollisionsTask.stopTask();
            }
            if (!isGameOver()) {
                checkWaterObjectCollision();
            }

        }
    }

    /**
     * Updates the frog position RIGHT by the STEP_SIZE_LR value
     *
     */
    public void updateFrogRightPosition() {

        if (this.ridingWaterObjectTask != null) {
            this.ridingWaterObjectTask.pauseTask();
            this.theView.getTheFrog().setTranslateX(
                    this.theView.getTheFrog().getTranslateX() + (STEP_SIZE_LR * 2));

            this.ridingWaterObjectTask.resumeTask();
        } else {
            this.theView.getTheFrog().setTranslateX(
                    this.theView.getTheFrog().getTranslateX() + STEP_SIZE_LR);
        }

        this.theView.getTheFrog().setRotate(90);
    }

    /**
     * Updates the frog position LEFT by the STEP_SIZE_LR value
     *
     */
    public void updateFrogLeftPosition() {

        if (this.ridingWaterObjectTask != null) {
            this.ridingWaterObjectTask.pauseTask();
            this.theView.getTheFrog().setTranslateX(
                    this.theView.getTheFrog().getTranslateX() - (STEP_SIZE_LR * 2));

            this.ridingWaterObjectTask.resumeTask();
        } else {
            this.theView.getTheFrog().setTranslateX(
                    this.theView.getTheFrog().getTranslateX() - STEP_SIZE_LR);
        }

        this.theView.getTheFrog().setRotate(270);

    }

    /**
     * Checks the bottom bound of the game board to make sure the frog has not
     * violated the boundary, returns true if the frog can successfully move to
     * the designated location, false if the frog will be out of bounds
     *
     * @return boolean
     */
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

    /**
     * Checks the top bound of the game board to make sure the frog has not
     * violated the boundary, returns true if the frog can successfully move to
     * the designated location, false if the frog will be out of bounds
     *
     * @return boolean
     */
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

    /**
     * Checks the right bound of the game board to make sure the frog has not
     * violated the boundary, returns true if the frog can successfully move to
     * the designated location, false if the frog will be out of bounds
     *
     * @return boolean
     */
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

    /**
     * Checks the left bound of the game board to make sure the frog has not
     * violated the boundary, returns true if the frog can successfully move to
     * the designated location, false if the frog will be out of bounds
     *
     * @return boolean
     */
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

    /**
     * For each array of cars, a task is created that will go through and start
     * each car
     *
     * @param delay int - the delay between each car gives the spacing
     */
    public void startTheCars(int delay) {
        CarPath[] theRoad = this.theView.getTheRoads();
        for (CarPath path : theRoad) {
            Car[] cars = path.getTheCars();
            //ArrayList<Car> theCars = path.getCars();
            this.theMoveCarTask = new MoveCarsTask(cars, delay);
            Thread th = new Thread(theMoveCarTask);
            th.setDaemon(true);
            th.start();
        }
    }

    /**
     * For each array of water objects, a task is created that will go through
     * and start each water object
     *
     * @param delay int - the delay between each water object gives the spacing
     */
    public void startTheWaterObjects(int delay) {
        WaterObjectPath[] theRiver = this.theView.getTheRivers();
        for (WaterObjectPath path : theRiver) {
            WaterObject[] waterObjects = path.getTheObjects();
            //ArrayList<WaterObject> waterObjects = path.getWaterObjects();
            this.theMoveWaterObjectsTask = new MoveWaterObjectsTask(waterObjects,
                                                                    delay);
            Thread th = new Thread(theMoveWaterObjectsTask);
            th.setDaemon(true);
            th.start();
        }
    }

    /**
     * creates the task responsible for checking the frog collisions with lily
     * pads
     */
    public void checkLilyCollisions() {
        LilyPad[] lilyPads = this.theView.getTheLilyPads();

        FroggerController.this.lilyPadCollisionsTask = new LilyPadCollisionsTask(
                lilyPads);
        Thread th = new Thread(lilyPadCollisionsTask);
        th.setDaemon(true);
        th.start();

    }

    /**
     * creates a task to check for car collisions with the frog in only the
     * current row that the frog is currently located
     */
    public void checkCarCollisions() {
        CarPath[] theRoad = this.theView.getTheRoads();
        CarPath path = theRoad[this.frogIndex];
        Car[] cars = path.getTheCars();
        //ArrayList<Car> theCars = path.getCars();

        FroggerController.this.carCollisionsTask = new CarCollisionsTask(
                cars);
        Thread th = new Thread(carCollisionsTask);
        th.setDaemon(true);
        th.start();
    }

    /**
     * creates a task to check for water object collisions with the frog in only
     * the current row that the frog is currently located
     */
    public void checkWaterObjectCollision() {
        WaterObjectPath[] theRiver = this.theView.getTheRivers();
        WaterObjectPath path = theRiver[theRiver.length - (this.maxFrogIndex - this.frogIndex)];

        this.waterObjectCollisionsTask = new WaterObjectCollisionTask(path);
        Thread th = new Thread(this.waterObjectCollisionsTask);
        th.setDaemon(true);
        th.start();
    }

    /**
     * Sets the flag that the frog is being controlled by the user keys to
     * false, indicating that it is now riding a water object
     */
    public void setControlledByKeysFalse() {
        this.controlledByKeys = false;
    }

    /**
     * Returns the state of the controlledByKeys flag, returns true if the user
     * is controlling the frog, false if the frog is riding a water object
     *
     * @return boolean
     */
    public boolean getControlledByKeys() {
        return this.controlledByKeys;
    }

    /**
     * Removes a life from the frog
     */
    public void removeLife() {
        this.numLives--;
    }

    /**
     * saves the score into high scores and triggers the end game screen if the
     * game is beat, a 500 point bonus is given
     *
     * @param winGame boolean true if the game is won
     */
    public void endGame(boolean winGame) {
        if (winGame) {
            adjustScore(50);
        }
        System.out.println("Final Score: " + this.score);
        ArrayList<Integer> theScores = this.highScores.insertScore(this.score);
        this.highScores.saveScores();
        this.gameOver = true;
        this.theView.endGame(this.score, theScores, winGame);

    }

    /**
     * Updates the score of the game and the score displayed on the GUI
     *
     * @param i int - the score adjustment
     */
    private void adjustScore(int i) {
        this.score += i * 10;
        if (this.score > this.maxScore) {
            this.maxScore = this.score;
            theView.updateScore(maxScore);
        }
    }

    /**
     * returns the max score thus far
     *
     * @return int max score
     */
    public int getMaxScore() {
        return maxScore;
    }

    /**
     * Creates a Task for moving the Water Objects across the screen
     */
    class MoveWaterObjectsTask extends Task<Integer> {

        private final WaterObject[] waterObjects;
        //private final ArrayList<WaterObject> waterObjects;
        private int baseDelay;

        /**
         * Construct the task with the model and water objects to run through
         * Constructor initializes instance variables
         *
         * @param waterObjects the array of water objects to be sent across the
         * screen
         * @param delay the delay of time between water objects
         */
        public MoveWaterObjectsTask(WaterObject[] waterObjects,
                                    int delay) {
            this.waterObjects = waterObjects;
            this.baseDelay = delay;
        }

        /**
         * the call action that actually runs the water object path transitions
         *
         * @return arbitrary int
         * @throws Exception
         */
        @Override
        protected Integer call() throws Exception {
            for (WaterObject waterObject : this.waterObjects) {

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        waterObject.moveWaterObject();
                    }
                });

                // randomly generates the delay time between each water object
                // given the original base delay time
                double rand = Math.random() * 1000;
                int sleepTime = (int) (this.baseDelay + rand);
                Thread.sleep(sleepTime);
            }

            return 1;
        }
    }

    /**
     * Creates a Task for moving the Cars across the screen
     */
    class MoveCarsTask extends Task<Integer> {

        private final Car[] cars;
        //private final ArrayList<Car> cars;
        private int baseDelay;

        /**
         * Construct the task with the model and cars to run through Constructor
         * initializes instance variables
         *
         * @param cars the array of cars to be sent across the screen
         * @param delay the delay of time between cars
         */
        public MoveCarsTask(Car[] cars, int delay) {
            this.cars = cars;
            this.baseDelay = delay;
        }

        /**
         * the call action that actually runs the car path transitions
         *
         * @return arbitrary int
         * @throws Exception
         */
        @Override
        protected Integer call() throws Exception {
            for (Car car : this.cars) {

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        car.moveCar();
                    }
                });

                // randomly generates the delay time between each water object
                // given the original base delay time
                double rand = Math.random() * 1000;
                int sleepTime = (int) (this.baseDelay + rand);
                Thread.sleep(sleepTime);
            }

            return 1;
        }

    }

    /**
     * Creates a Task for continually checking collisions between cars of a
     * single lane and the frog
     */
    class CarCollisionsTask extends Task<Integer> {

        private final Car[] cars;
        //private final ArrayList<Car> cars;
        private boolean stopTask;

        /**
         * Construct the task with the model and cars to run through;
         * initializes instance variables
         *
         * @param cars array of cars to check for collisions
         */
        public CarCollisionsTask(Car[] cars) {
            this.cars = cars;
            this.stopTask = false;
        }

        /**
         * the call action that continually checks each car in the array for
         * collisions; restarts and deducts a life from the frog if a collision
         * occurs
         *
         * @return arbitrary int
         * @throws Exception
         */
        @Override
        protected Integer call() throws Exception {

            while (!stopTask) {
                Bounds frogBounds = FroggerController.this.theView.getTheFrog().getBoundsInParent();
                for (Car car : this.cars) {
                    if (car.getBoundsInParent().intersects(frogBounds)) {
                        //FroggerController.this.numLives--;
                        this.stopTask = true;
                        //System.out.println("Hit Car - remove life");
                        FroggerController.this.restartFrogIndex();
                        FroggerController.this.removeLife();
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {

                                FroggerController.this.theView.getTheFrog().restartFrog();
                                FroggerController.this.theView.removeNextLife();

                                if (FroggerController.this.numLives <= 0) {
                                    FroggerController.this.endGame(false);
                                }
                            }
                        });
                    }
                    Thread.sleep(1);
                }

            }

            return 1;
        }

        /**
         * Stops the task from executing
         */
        public void stopTask() {
            this.stopTask = true;
        }

    }

    /**
     * Creates a Task for continually checking collisions between water objects
     * of a single lane and the frog
     */
    class WaterObjectCollisionTask extends Task<Integer> {

        private final WaterObjectPath waterPath;
        private boolean stopTask;

        /**
         * Construct the task with the model and the water path to check for
         * collisions; initializes instance variables
         *
         * @param path array of water objects to check for collisions
         */
        public WaterObjectCollisionTask(WaterObjectPath path) {
            this.waterPath = path;
            this.stopTask = false;
        }

        /**
         * the call action that continually checks each water object in the
         * array for collisions; restarts and deducts a life from the frog if
         * the frog misses a water object, otherwise a rideTheWaterObjectTask is
         * created
         *
         * @return arbitrary int
         * @throws Exception
         */
        @Override
        protected Integer call() throws Exception {

            while (!stopTask) {
                if (FroggerController.this.theView.getTheFrog().getisOnWaterObject()) {
                    return 1;
                }

                Bounds frogBoundsParent = FroggerController.this.theView.getTheFrog().getBoundsInParent();

                boolean isOnALog = false;
                WaterObject[] waterObjects = this.waterPath.getTheObjects();
                //ArrayList<WaterObject> waterObjects = this.waterPath.getWaterObjects();
                for (WaterObject waterObject : waterObjects) {
                    if (waterObject.getBoundsInParent().intersects(
                            frogBoundsParent)) {
                        FroggerController.this.setControlledByKeysFalse();
                        FroggerController.this.theView.getTheFrog().setisOnWaterObjectTrue();
                        FroggerController.this.ridingWaterObjectTask = new RideTheWaterObjectTask(
                                waterObject);
                        Thread th = new Thread(
                                FroggerController.this.ridingWaterObjectTask);
                        th.setDaemon(true);
                        th.start();
                        //System.out.println("intersecting log/turtle");
                        return 1;

                    }

                    //Thread.sleep(1);
                }
                if (FroggerController.this.frogIndex >= FroggerController.this.maxFrogIndex - FroggerController.this.theView.getTheRivers().length) {
                    this.stopTask = true;
                    //System.out.println(
                    //"Hit water" + FroggerController.this.frogIndex);
                    FroggerController.this.restartFrogIndex();
                    FroggerController.this.removeLife();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {

                            FroggerController.this.theView.getTheFrog().restartFrog();
                            FroggerController.this.theView.removeNextLife();

                            if (FroggerController.this.numLives <= 0) {
                                FroggerController.this.endGame(false);
                            }
                        }
                    });

                    Thread.sleep(1);
                }
            }

            return 1;
        }

        /**
         * Stops the task from executing
         */
        public void stopTask() {
            this.stopTask = true;
        }

    }

    /**
     * Creates a Task that continually updates the frog's position along a water
     * object as it moves with it
     */
    class RideTheWaterObjectTask extends Task<Integer> {
        private final WaterObject theObject;
        private boolean isPaused;

        /**
         * Construct the task with the model and the water path to check for
         * collisions
         *
         * @param theObj the water object that the frog is currently in contact
         * with and "riding"
         */
        public RideTheWaterObjectTask(WaterObject theObj) {
            this.theObject = theObj;
            this.isPaused = false;

        }

        /**
         * the call action that continually updates the frog position as long as
         * the frog is not being controlled by the user keys
         *
         * @return arbitrary int
         * @throws Exception
         */
        @Override
        protected Integer call() throws Exception {

            Bounds frogBounds = FroggerController.this.theView.getTheFrog().localToScene(
                    FroggerController.this.theView.getTheFrog().getBoundsInLocal());
            Bounds objectBounds = this.theObject.localToScene(
                    this.theObject.getBoundsInLocal());

            double offset = objectBounds.getMaxX() - frogBounds.getMaxX();

            while (true && !FroggerController.this.getControlledByKeys()) {

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

        /**
         * Stops the task
         */
        public void stopTask() {
            //System.out.println("cancelled");
            FroggerController.this.theView.getTheFrog().setisOnWaterObjectFalse();
            this.cancel();
        }
    }

    /**
     * Creates a Task that checks collisions between the lily pads of the top
     * lane and the frog
     */
    class LilyPadCollisionsTask extends Task<Integer> {

        private final LilyPad[] lilyPads;
        private boolean onPad;

        /**
         * Construct the task with the model and initializes instance variables
         *
         * @param lilyPads the array of lily pads to check for collisions
         */
        public LilyPadCollisionsTask(LilyPad[] lilyPads) {
            this.lilyPads = lilyPads;
            this.onPad = false;
        }

        /**
         * the call action checks each lily pad for a collisions with the frog,
         * restarts and deducts a life from the frog if the frog tries to occupy
         * a lily pad that already has a frog on it or if the frog misses the
         * lily pad, otherwise it is safe and the next frog is generated at the
         * bottom of the screen
         *
         * @return arbitrary int
         * @throws Exception
         */
        @Override
        protected Integer call() throws Exception {

            Bounds frogBounds = FroggerController.this.theView.getTheFrog().getBoundsInParent();
            for (LilyPad lilyPad : this.lilyPads) {
                Bounds lilyBounds = lilyPad.getBoundsInParent();

                if (lilyPad.getBoundsInParent().intersects(frogBounds)) {
                    if (lilyPad.getIsOccupied()) {
                        break;
                    }
                    //System.out.println("On Lilypad");
                    boolean levelUp = FroggerController.this.theModel.levelUp();
                    lilyPad.setOccupied();

                    if (levelUp) {
                        FroggerController.this.restartFrogIndex();
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                FroggerController.this.theView.launchNewFrog();
                                FroggerController.this.theView.addFrog();
                            }
                        });
                        Thread.sleep(1);

                    } else {
                        Thread.sleep(500);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                FroggerController.this.endGame(true);
                            }
                        });
                        Thread.sleep(1);
                    }

                    this.onPad = true;
                    return 1;
                }
            }

            if (!this.onPad) {
                System.out.println("Missed Lily Pad");
                FroggerController.this.restartFrogIndex();
                FroggerController.this.removeLife();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {

                        FroggerController.this.theView.getTheFrog().restartFrog();
                        FroggerController.this.theView.removeNextLife();
                        if (FroggerController.this.numLives <= 0) {
                            FroggerController.this.endGame(false);
                        }

                    }
                });
                Thread.sleep(1);
                return 1;
            }
            return 1;
        }

    }

}
