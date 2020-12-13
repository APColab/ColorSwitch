package model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.scene.shape.Arc;
import view.CircularObstacleView;

public class CircularObstacle extends Obstacle{
    private final FloatProperty radius;
    private final FloatProperty width;

    public CircularObstacle(float pos_x, float pos_y, float w){
        this.setPos_X(pos_x);
        this.setPos_Y(pos_y);
        this.radius = new SimpleFloatProperty(getHeight()/2);
        this.width = new SimpleFloatProperty(w);
        setObstacleView(new CircularObstacleView(this));
        setBindings();
    }



    public CircularObstacle() {
        this(200,100,10.0f);
    }

    public float getRadius() {
        return radius.getValue();
    }



    public float getWidth() {
        return width.getValue();
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
            ((Arc)getObstacleView().getShapeList().get(i)).strokeWidthProperty().bind(width);
        }
        this.getObstacleView().prefHeightProperty().bind(radius.multiply(2.0f));
        this.getObstacleView().prefWidthProperty().bind(radius.multiply(2.0f));
    }
}
