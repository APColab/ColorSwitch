package model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import view.CircularObstacleView;

public class CircularObstacle extends Obstacle{
    private FloatProperty radius;
    private FloatProperty width;
    private CircularObstacleView circularObstacleView;

    public CircularObstacle(float pos_x, float pos_y, float r, float w){
        this.setPos_X(pos_x);
        this.setPos_Y(pos_y);
        this.radius = new SimpleFloatProperty(r);
        this.width = new SimpleFloatProperty(w);
        circularObstacleView = new CircularObstacleView(this);
        setBindings();
    }

    public CircularObstacle() {
        this(200,100,100.0f,10.0f);
    }

    public float getRadius() {
        return radius.getValue();
    }

    public void setRadius(float radius) {
        this.radius.setValue(radius);
    }

    public float getWidth() {
        return width.getValue();
    }

    public void setWidth(float width) {
        this.width.setValue(width);
    }

    public CircularObstacleView getCircularObstacleView() {
        return circularObstacleView;
    }

    @Override
    public void setBindings() {
        this.getPos_XProperty().bindBidirectional(this.circularObstacleView.layoutXProperty());
        this.getPos_YProperty().bindBidirectional(this.circularObstacleView.layoutYProperty());
        for(int i=0;i<4;i++){
            circularObstacleView.getArcs()[i].centerXProperty().bind(radius);
            circularObstacleView.getArcs()[i].centerYProperty().bind(radius);
            circularObstacleView.getArcs()[i].radiusXProperty().bind(radius);
            circularObstacleView.getArcs()[i].radiusYProperty().bind(radius);
            circularObstacleView.getArcs()[i].strokeWidthProperty().bind(width);
        }
        circularObstacleView.prefHeightProperty().bind(radius.multiply(2.0f));
        circularObstacleView.prefWidthProperty().bind(radius.multiply(2.0f));
    }
}
