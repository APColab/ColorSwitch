package view;


import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import model.RectangularObstacle;

public class RectangularObstacleView extends ObstacleView {


    public RectangularObstacleView(RectangularObstacle rect){
        super(rect,new RotateTransition(Duration.millis(3000)));
        getShapeList().add(new Line(0,0,0,rect.getHeight()));
        getShapeList().add(new Line(0,0,rect.getBreadth(),0));
        getShapeList().add(new Line(0,rect.getHeight(),rect.getBreadth(),rect.getHeight()));
        getShapeList().add(new Line(rect.getBreadth(),0,rect.getBreadth(),rect.getHeight()));
        for(int i=0;i<4;i++){
            getShapeList().get(i).setStrokeWidth(rect.getStroke());
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
