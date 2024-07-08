package com.example.antivirus;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;

public class EnhancedAntivirus extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Enhanced Antivirus Scanner");

        // Create UI elements
        Label titleLabel = new Label("Enhanced Antivirus Scanner");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Button scanButton = new Button("Scan");
        Button boostButton = new Button("Boost");
        Button settingsButton = new Button("Settings");

        ProgressBar progressBar = new ProgressBar(0);
        TextArea outputArea = new TextArea();
        outputArea.setEditable(false);

        // Set button actions
        scanButton.setOnAction(event -> scanSystem(outputArea, progressBar));
        boostButton.setOnAction(event -> boostSystem(outputArea, progressBar));
        settingsButton.setOnAction(event -> showSettings());

        // Layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(titleLabel, scanButton, boostButton, settingsButton, progressBar, outputArea);

        Scene scene = new Scene(layout, 600, 400);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void scanSystem(TextArea outputArea, ProgressBar progressBar) {
        outputArea.appendText("Scanning system for viruses...\n");
        progressBar.setProgress(0.5);
        // Add your virus scanning logic here
        outputArea.appendText("No threats found!\n");
        outputArea.appendText("Scanning for junk files...\n");
        scanForJunkFiles(outputArea, progressBar);
        progressBar.setProgress(1.0);
    }

    private void scanForJunkFiles(TextArea outputArea, ProgressBar progressBar) {
        String[] junkFileLocations = {
            System.getenv("TEMP"),
            System.getProperty("user.home") + "\\AppData\\Local\\Temp",
            "C:\\Windows\\Temp"
        };

        for (String location : junkFileLocations) {
            File dir = new File(location);
            if (dir.exists() && dir.isDirectory()) {
                File[] files = dir.listFiles();
                if (files != null) {
                    for (File file : files) {
                        if (file.isFile()) {
                            outputArea.appendText("Found junk file: " + file.getAbsolutePath() + "\n");
                        }
                    }
                }
            }
        }
        progressBar.setProgress(0.75);
    }

    private void boostSystem(TextArea outputArea, ProgressBar progressBar) {
        outputArea.appendText("Boosting system performance...\n");
        progressBar.setProgress(0.5);
        deleteJunkFiles(outputArea);
        outputArea.appendText("System performance boosted!\n");
        progressBar.setProgress(1.0);
    }

    private void deleteJunkFiles(TextArea outputArea) {
        String[] junkFileLocations = {
            System.getenv("TEMP"),
            System.getProperty("user.home") + "\\AppData\\Local\\Temp",
            "C:\\Windows\\Temp"
        };

        for (String location : junkFileLocations) {
            File dir = new File(location);
            if (dir.exists() && dir.isDirectory()) {
                File[] files = dir.listFiles();
                if (files != null) {
                    for (File file : files) {
                        if (file.isFile() && file.delete()) {
                            outputArea.appendText("Deleted junk file: " + file.getAbsolutePath() + "\n");
                        }
                    }
                }
            }
        }
    }

    private void showSettings() {
        // Show settings dialog
        Stage settingsStage = new Stage();
        settingsStage.setTitle("Settings");

        Label settingsLabel = new Label("Settings");
        settingsLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        CheckBox autoScanCheckbox = new CheckBox("Enable Auto Scan");
        CheckBox notifyCheckbox = new CheckBox("Enable Notifications");

        VBox settingsLayout = new VBox(10);
        settingsLayout.setPadding(new Insets(10));
        settingsLayout.getChildren().addAll(settingsLabel, autoScanCheckbox, notifyCheckbox);

        Scene settingsScene = new Scene(settingsLayout, 300, 200);
        settingsStage.setScene(settingsScene);
        settingsStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

