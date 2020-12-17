package view;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import model.CrossObstacle;
import model.CustomObstacle;

import java.util.ArrayList;

public class CustomObstacleView extends ObstacleView{

    public CustomObstacleView(CustomObstacle c, ArrayList<Line> lines){
        super(c,new RotateTransition(Duration.millis(3000)));
        getShapeList().addAll(lines);
        for(int i=0;i<4;i++){
            getShapeList().get(i).setStrokeWidth(c.getStroke()+3);
        }
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
