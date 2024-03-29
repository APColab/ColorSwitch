package view;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.util.Duration;
import model.CircularObstacle;

public class CircularObstacleView extends ObstacleView {


    public CircularObstacleView(CircularObstacle c){
        super(c,new RotateTransition(Duration.millis(3000)));
        for(int i =0;i<4;i++){
            Arc a = new Arc(c.getRadius(),c.getRadius(),c.getRadius(),c.getRadius(),i*90,90);
            a.setType(ArcType.OPEN);
            a.setStrokeWidth(c.getStroke());
            a.setFill(Color.TRANSPARENT);
            getShapeList().add(a);
        }
        getShapeList().get(0).setStroke(Color.RED);
        getShapeList().get(1).setStroke(Color.BLUE);
        getShapeList().get(2).setStroke(Color.GREEN);
        getShapeList().get(3).setStroke(Color.YELLOW);
        this.setPrefHeight(getObstacle().getHeight());
        this.setPrefWidth(getObstacle().getWidth());
        this.setLayoutX(getObstacle().getPos_X());
        this.setLayoutY(getObstacle().getPos_Y());
        this.getChildren().addAll(getShapeList());

        ((RotateTransition)getTransition()).setNode(this);
        ((RotateTransition)getTransition()).setCycleCount(Animation.INDEFINITE);
        ((RotateTransition)getTransition()).setByAngle(360);
        ((RotateTransition)getTransition()).setInterpolator(Interpolator.LINEAR);
        ((RotateTransition)getTransition()).play();
    }


}
