package com.example.gamemeteor;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameCanvas extends Canvas {

    private final GraphicsContext gc;
    private final String message = "Hallo, Spielwelt!";
    private final Font font = new Font("Arial", 24);

    public GameCanvas(int width, int height) {
        super(width, height);
        gc = getGraphicsContext2D();
    }

    /** Здесь будем обновлять всё (движение, физику, ввод) */
    public void update(double deltaTime) {

    }

    /** Здесь рисуем текущий фрейм */
    public void render() {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, getWidth(), getHeight());

        // 2. Рисуем тестовый текст по центру
        gc.setFont(font);
        gc.setFill(Color.WHITE);
        Text helper = new Text(message);
        helper.setFont(font);
        double textWidth  = helper.getLayoutBounds().getWidth();
        double textHeight = helper.getLayoutBounds().getHeight();

        // Центрируем и рисуем
        double x = (getWidth()  - textWidth)  / 2;
        double y = (getHeight() + textHeight) / 2;
        gc.fillText(message, x, y);
    }
}
