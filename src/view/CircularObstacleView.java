package view;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.util.Duration;
import model.CircularObstacle;

public class CircularObstacleView extends Pane {
    private final CircularObstacle circle;
    private final Arc[] arcs;
    private final RotateTransition rt;

    public CircularObstacle getCircle() {
        return circle;
    }

    public CircularObstacleView(){
        this(new CircularObstacle());
    }

    public CircularObstacleView(CircularObstacle c){
        this.circle = c;
        arcs = new Arc[4];
        for(int i =0;i<4;i++){
            arcs[i] = new Arc(circle.getRadius(),circle.getRadius(),circle.getRadius(),circle.getRadius(),i*90,90);
            arcs[i].setType(ArcType.OPEN);
            arcs[i].setStrokeWidth(circle.getWidth());
            arcs[i].setFill(Color.TRANSPARENT);
        }
        arcs[0].setStroke(Color.RED);
        arcs[1].setStroke(Color.BLUE);
        arcs[2].setStroke(Color.GREEN);
        arcs[3].setStroke(Color.YELLOW);
        this.setPrefHeight(2*circle.getRadius());
        this.setPrefWidth(2*circle.getRadius());
        this.setLayoutX(this.circle.getPos_X());
        this.setLayoutY(this.circle.getPos_Y());
        for(int i=0;i<4;i++){
            this.getChildren().add(arcs[i]);
        }
        rt = new RotateTransition(Duration.millis(3000),this);
        rt.setCycleCount(Animation.INDEFINITE);
        rt.setByAngle(360);
        rt.setInterpolator(Interpolator.LINEAR);
        rt.play();
    }

    public Arc[] getArcs() {
        return arcs;
    }
}
