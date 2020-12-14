package view;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.util.Duration;
import model.DoubleCircleObstacle;

public class DoubleCircleObstacleView extends ObstacleView{

    public DoubleCircleObstacleView(DoubleCircleObstacle dc,Color  color){
        super(dc,new RotateTransition(Duration.millis(3000)));
        if(color==Color.RED || color== Color.GREEN) {
            for (int i = 0; i < 4; i++) {
                Arc a = new Arc(dc.getOuter().getRadius(), dc.getOuter().getRadius(), dc.getInnerRadius(), dc.getInnerRadius(), i * 90 + 45, 90);
                a.setType(ArcType.OPEN);
                a.setStrokeWidth(dc.getStroke());
                a.setFill(Color.TRANSPARENT);
                ((Arc) dc.getOuter().getObstacleView().getShapeList().get(i)).setStartAngle(i * 90 + 45);
                a.setStroke(dc.getOuter().getObstacleView().getShapeList().get(i).getStroke());
                getShapeList().add(a);
            }
        }else{
            for (int i = 0; i < 4; i++) {
                Arc a = new Arc(dc.getOuter().getRadius(), dc.getOuter().getRadius(), dc.getInnerRadius(), dc.getInnerRadius(), i * 90 - 45, 90);
                a.setType(ArcType.OPEN);
                a.setStrokeWidth(dc.getStroke());
                a.setFill(Color.TRANSPARENT);
                ((Arc) dc.getOuter().getObstacleView().getShapeList().get(i)).setStartAngle(i * 90 - 45);
                a.setStroke(dc.getOuter().getObstacleView().getShapeList().get(i).getStroke());
                getShapeList().add(a);
            }
        }


        this.setPrefHeight(getObstacle().getHeight());
        this.setPrefWidth(getObstacle().getWidth());
        this.setLayoutX(getObstacle().getPos_X());
        this.setLayoutY(getObstacle().getPos_Y());
        this.getChildren().addAll(getShapeList());
        ((RotateTransition)dc.getOuter().getObstacleView().getTransition()).stop();
        ((RotateTransition)dc.getOuter().getObstacleView().getTransition()).setByAngle(720);
        ((RotateTransition)dc.getOuter().getObstacleView().getTransition()).play();
        this.getChildren().add(dc.getOuter().getObstacleView());

        ((RotateTransition)getTransition()).setNode(this);
        ((RotateTransition)getTransition()).setCycleCount(Animation.INDEFINITE);
        ((RotateTransition)getTransition()).setByAngle(-360);
        ((RotateTransition)getTransition()).setInterpolator(Interpolator.LINEAR);
        ((RotateTransition)getTransition()).play();

    }

}
