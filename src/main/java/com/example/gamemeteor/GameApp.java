package com.example.gamemeteor;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameApp extends Application {

    @Override
    public void start(Stage primaryStage) {

        Pane root = new Pane();
        GameCanvas canvas = new GameCanvas(GameCanvas.WIDTH, GameCanvas.HEIGHT);
        root.getChildren().add(canvas);

        Scene scene = new Scene(root, GameCanvas.WIDTH, GameCanvas.HEIGHT);

        // Проксируем события клавиатуры в Canvas
        scene.setOnKeyPressed(canvas::onKeyPressed);
        scene.setOnKeyReleased(canvas::onKeyReleased);

        primaryStage.setTitle("2D-Spiel - Meteoren ausweichen");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        AnimationTimer gameLoop = new AnimationTimer() {
            private long lastTimeNano;

            @Override
            public void start() {
                lastTimeNano = System.nanoTime();
                super.start();
            }

            @Override
            public void handle(long nowNano) {
                // 1. Считаем дельту времени в секундах
                double deltaTime = (nowNano - lastTimeNano) / 1_000_000_000.0;
                lastTimeNano = nowNano;

                // 2. Обновляем состояние игры
                canvas.update(deltaTime);

                // 3. Перерисовываем экран
                canvas.render();
            }
        };

        gameLoop.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}