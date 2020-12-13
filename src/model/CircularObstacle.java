package model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.scene.shape.Arc;
import view.CircularObstacleView;

public class CircularObstacle extends Obstacle{
    private final FloatProperty radius;

    public CircularObstacle(float pos_x, float pos_y){
        this.setPos_X(pos_x);
        this.setPos_Y(pos_y);
        this.radius = new SimpleFloatProperty(getHeight()/2);
        setObstacleView(new CircularObstacleView(this));
        setBindings();
    }



    public CircularObstacle() {
        this(200,100);
    }

    public float getRadius() {
        return radius.getValue();
    }






    public CircularObstacleView getCircularObstacleView() {
        return (CircularObstacleView)getObstacleView() ;
    }

    @Override
    public void setBindings() {
        this.pos_XProperty().bindBidirectional(this.getObstacleView().layoutXProperty());
        this.pos_YProperty().bindBidirectional(this.getObstacleView().layoutYProperty());
        for(int i=0;i<4;i++){
            ((Arc)getObstacleView().getShapeList().get(i)).centerXProperty().bind(radius);
            ((Arc)getObstacleView().getShapeList().get(i)).centerYProperty().bind(radius);
            ((Arc)getObstacleView().getShapeList().get(i)).radiusXProperty().bind(radius);
            ((Arc)getObstacleView().getShapeList().get(i)).radiusYProperty().bind(radius);
        }
        this.getObstacleView().prefHeightProperty().bind(radius.multiply(2.0f));
        this.getObstacleView().prefWidthProperty().bind(radius.multiply(2.0f));
    }
}
