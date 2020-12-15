package model;

import javafx.scene.shape.Shape;

import java.util.ArrayList;

public interface Collidable{
    ArrayList<Shape> getCollidables();
    boolean isColliding(Collidable collidable);
    void handleCollision();
}