package com.example.gamemeteor;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class Player implements Movable, Drawable{
    private double x, y;
    private final double size = 40;
    private final double speed = 200;

    //Флаги нажатий
    private boolean up, down, right, left;

    public Player(double startX, double startY) {
        this.x = startX;
        this.y = startY;
    }

    public void onKeyPressed(KeyCode code) {
        switch (code){
            case W, UP      -> up   = true;
            case S, DOWN    -> down = true;
            case A, LEFT    -> left = true;
            case F, RIGHT   -> right = true;
        }
    }

    public void onKeyReleased(KeyCode code) {
        switch (code){
            case W, UP      -> up   = false;
            case S, DOWN    -> down = false;
            case A, LEFT    -> left = false;
            case F, RIGHT   -> right = false;
        }
    }

    @Override
    public void update(double deltaTime) {
        double dx = 0;
        double dy = 0;
        if (up) dy -= 1;
        if (down) dy += 1;
        if (left) dx -= 1;
        if (right) dx += 1;

        // нормализация диагонального движения
        if (dx != 0 && dy != 0) {
            double inv = 1 / Math.sqrt(2);
            dx *= inv;
            dy *= inv;
        }

        x += dx * speed * deltaTime;
        y += dy * speed * deltaTime;

        // Ограничим внутри экрана
        x = Math.max(0, Math.min(x, GameCanvas.WIDTH - size));
        y = Math.max(0, Math.min(y, GameCanvas.HEIGHT - size));
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(Color.BLUE);
        gc.fillRect(x, y, size, size);
    }
}
