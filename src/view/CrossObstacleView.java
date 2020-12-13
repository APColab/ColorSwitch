package view;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import model.CrossObstacle;

public class CrossObstacleView extends ObstacleView{

    public CrossObstacleView(CrossObstacle c){
        super(c,new RotateTransition(Duration.millis(3000)));
        getShapeList().add(new Line(c.getWidth()/2,c.getHeight()/2,c.getWidth()/2,0));
        getShapeList().add(new Line(c.getWidth()/2,c.getHeight()/2,c.getWidth(),c.getHeight()/2));
        getShapeList().add(new Line(c.getWidth()/2,c.getHeight()/2,c.getWidth()/2,c.getHeight()));
        getShapeList().add(new Line(c.getWidth()/2,c.getHeight()/2,0,c.getHeight()/2));
        for(int i=0;i<4;i++){
            getShapeList().get(i).setStrokeWidth(c.getStroke());
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
