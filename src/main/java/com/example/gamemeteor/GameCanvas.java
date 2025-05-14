package com.example.gamemeteor;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import javafx.scene.input.KeyEvent;

public class GameCanvas extends Canvas {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private final GraphicsContext gc;
    private final Player player;

    public GameCanvas(int width, int height) {
        super(width, height);
        gc = getGraphicsContext2D();

        player = new Player((width - 40) / 2.0, (height - 40) / 2.0);
    }

    public void onKeyPressed(KeyEvent e){
        player.onKeyPressed(e.getCode());
    }

    public void onKeyReleased(KeyEvent e){
        player.onKeyReleased(e.getCode());
    }

    /** Здесь будем обновлять всё (движение, физику, ввод) */
    public void update(double deltaTime) {
        player.update(deltaTime);
    }

    /** Здесь рисуем текущий фрейм */
    public void render() {
        // 1. Очистка экрана
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, getWidth(), getHeight());

        // 2. Отрисовка игрока
        player.render(gc);

        // (Опционально) отрисуем подпись
        String msg = "Verwende WASD oder Pfeiltasten";
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Arial", 16));
        Text helper = new Text(msg);
        helper.setFont(gc.getFont());
        double w = helper.getLayoutBounds().getWidth();
        gc.fillText(msg, (getWidth()-w)/2, getHeight()-10);
    }
}
