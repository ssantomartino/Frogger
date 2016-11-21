/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2016
*
* Name: Sam Santomartino, Grace Cook, and John O'Brien
* Date: Nov 20, 2016
* Time: 12:46:53 PM
*
* Project: csci205FinalProject
* Package: FroggerMVC
* File: FroggerMainMenu
* Description:
*
* ****************************************
 */
package FroggerMVC;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

/**
 *
 * @author samanthasantomartino
 */
public class FroggerMainMenu {
    private StackPane root;
    private Button start;
    private Button exit;
    private Label title;
    private VBox theVBox;
    private CheckBox onePlayer;
    private CheckBox twoPlayer;
    private CheckBox beginner;
    private CheckBox expert;

    private static final Integer STARTTIME = 7;
    private Timeline timeline;
    private Label timerLabel = new Label();
    private IntegerProperty timeSeconds = new SimpleIntegerProperty(
            STARTTIME * 100);

    private Stage subStage;

    public FroggerMainMenu() {
        this.subStage = new Stage();
        subStage.setTitle("Main Menu");

        this.root = new StackPane();
        root.setPrefWidth(700);
        root.setPrefHeight(750);
        Scene scene = new Scene(root);

        this.theVBox = new VBox();
        this.theVBox.setPadding(new Insets(10, 50, 10, 50));
        this.theVBox.setSpacing(50);
        this.theVBox.setAlignment(Pos.CENTER);

        addTitle();
        addPlayerBoxes();
        addLevelOptions();
        addButtons();
        addProgressBar();
        this.root.getChildren().add(this.theVBox);

        StackPane.setAlignment(this.theVBox, Pos.CENTER);

        subStage.setScene(scene);
        subStage.setResizable(false);
        subStage.show();

        // if main menu screen is closd, the whole program closes
        subStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent ev) {
                System.exit(0);
            }
        });
    }

    public void addProgressBar() {

        ProgressBar progressBar = new ProgressBar();
        progressBar.progressProperty().bind(
                timeSeconds.divide(STARTTIME * 100.0).subtract(1).multiply(-1));

        timeSeconds.set((STARTTIME + 1) * 100);
        timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(STARTTIME + 1),
                             new KeyValue(timeSeconds, 0)));
        timeline.playFromStart();

        HBox timeContainer = new HBox(20);             // gap between components is 20
        timeContainer.setAlignment(Pos.CENTER);
        timeContainer.getChildren().addAll(progressBar);
        this.theVBox.getChildren().add(timeContainer);

        this.timeline.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FroggerMainMenu.this.start.setDisable(false);
            }
        });

    }

    public void addPlayerBoxes() {
        HBox playerContainer = new HBox();
        playerContainer.setAlignment(Pos.CENTER);
        playerContainer.setSpacing(10);
        this.onePlayer = new CheckBox("One Player");
        this.onePlayer.setFont(new Font("Arial", 30));
        this.onePlayer.setSelected(true);
        this.twoPlayer = new CheckBox("Two Player");
        this.twoPlayer.setFont(new Font("Arial", 30));
        playerContainer.getChildren().addAll(this.onePlayer, this.twoPlayer);
        this.theVBox.getChildren().add(playerContainer);
    }

    public void addLevelOptions() {
        HBox levelContainer = new HBox();
        levelContainer.setAlignment(Pos.CENTER);
        levelContainer.setSpacing(10);
        this.beginner = new CheckBox("Beginner");
        this.beginner.setFont(new Font("Arial", 30));
        this.beginner.setSelected(true);
        this.expert = new CheckBox("Expert");
        this.expert.setFont(new Font("Arial", 30));
        levelContainer.getChildren().addAll(this.beginner, this.expert);
        this.theVBox.getChildren().add(levelContainer);

    }

    public void addTitle() {
        this.title = new Label("Welcome to FROGGER!");
        title.setFont(new Font("Arial", 50));
        title.setTextFill(Color.BLUEVIOLET);

        this.theVBox.getChildren().add(this.title);
    }

    public void addButtons() {

        HBox buttonContainer = new HBox();
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.setSpacing(10);

        this.start = new Button("Start");
        this.start.setFont(new Font("Arial", 20));
        this.start.setTextFill(Color.BLUEVIOLET);
        this.start.setPrefSize(100, 50);
        this.start.setDisable(true);
        this.start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FroggerMainMenu.this.subStage.close();
            }
        });

        this.exit = new Button("Exit");
        this.exit.setFont(new Font("Arial", 20));
        this.exit.setTextFill(Color.GREEN);
        this.exit.setPrefSize(100, 50);

        this.exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

        buttonContainer.getChildren().addAll(this.start, this.exit);
        this.theVBox.getChildren().add(buttonContainer);
    }

}
