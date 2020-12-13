package view;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import model.Obstacle;

import java.util.ArrayList;
import java.util.List;

public abstract class ObstacleView extends Pane {
    private final Obstacle obstacle;
    private final List<Shape> shapeList;
    private final Transition transition;


    protected ObstacleView(Obstacle obstacle, Transition transition) {
        this.obstacle = obstacle;
        this.shapeList = new ArrayList<>();
        this.transition = transition;
    }

    public List<Shape> getShapeList() {
        return shapeList;
    }

    public Transition getTransition() {
        return transition;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }
}
