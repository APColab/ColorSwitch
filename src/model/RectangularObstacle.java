package model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import view.RectangularObstacleView;

public class   RectangularObstacle extends Obstacle {
    private FloatProperty length;
    private FloatProperty breadth;

    public RectangularObstacle(float pos_x, float pos_y){
        this.setPos_X(pos_x);
        this.setPos_Y(pos_y);
        this.length = new SimpleFloatProperty(getHeight());
        this.breadth = new SimpleFloatProperty(getWidth());
        setObstacleView(new RectangularObstacleView(this));
        setBindings();
    }

    public RectangularObstacle(){
        this(200,100);
    }




    @Override
    public void setBindings() {
        this.pos_XProperty().bindBidirectional(this.getObstacleView().layoutXProperty());
        this.pos_YProperty().bindBidirectional(this.getObstacleView().layoutYProperty());
    }


    public float getLength() {
        return length.get();
    }

    public FloatProperty lengthProperty() {
        return length;
    }

    public void setLength(float length) {
        this.length.set(length);
    }

    public float getBreadth() {
        return breadth.get();
    }

    public FloatProperty breadthProperty() {
        return breadth;
    }

    public void setBreadth(float breadth) {
        this.breadth.set(breadth);
    }


    @Override
    public void handleCollision() {

    }
}
