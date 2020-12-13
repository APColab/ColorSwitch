package view;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import model.TriangleObstacle;

public class TriangleObstacleView extends ObstacleView{

    public TriangleObstacleView(TriangleObstacle t, Color c){
        super(t,new RotateTransition(Duration.millis(3000)));
        for(int i=0;i<3;i++){
            getShapeList().add(new Line());
            getShapeList().get(i).setStrokeWidth(t.getStroke());
        }
        float ctov = t.getCenterToVertex();
        ((Line)getShapeList().get(0)).setStartX(0);((Line)getShapeList().get(0)).setStartY(0);
        ((Line)getShapeList().get(1)).setStartX(ctov*0.4482);((Line)getShapeList().get(1)).setStartY(ctov*1.673);
        ((Line)getShapeList().get(2)).setStartX(ctov*1.673);((Line)getShapeList().get(2)).setStartY(ctov*0.4482);

        ((Line)getShapeList().get(2)).setEndX(0);((Line)getShapeList().get(2)).setEndY(0);
        ((Line)getShapeList().get(0)).setEndX(ctov*0.4482);((Line)getShapeList().get(0)).setEndY(ctov*1.673);
        ((Line)getShapeList().get(1)).setEndX(ctov*1.673);((Line)getShapeList().get(1)).setEndY(ctov*0.4482);

        getShapeList().get(0).setStroke(Color.RED);
        getShapeList().get(1).setStroke(Color.BLUE);
        getShapeList().get(2).setStroke(Color.GREEN);
        if(c==Color.YELLOW){
            getShapeList().get(0).setStroke(Color.YELLOW);
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
