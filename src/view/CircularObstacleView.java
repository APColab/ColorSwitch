package view;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import model.CircularObstacle;

public class CircularObstacleView extends Pane {
    private CircularObstacle circle;
    private Arc[] arcs;
    private float startAngle;

    public CircularObstacleView(){
        this.startAngle = 0.0f;
        this.circle = new CircularObstacle();
        arcs = new Arc[4];
        for(int i =0;i<4;i++){
            arcs[i] = new Arc(circle.getPos_X()+circle.getRadius(),circle.getPos_Y()+circle.getRadius(),circle.getRadius(),circle.getRadius(),startAngle+i*90,90);
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
        for(int i=0;i<4;i++){
            this.getChildren().add(arcs[i]);
        }
    }
}
