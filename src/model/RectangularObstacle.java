package model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import view.RectangularObstacleView;

public class RectangularObstacle extends Obstacle {
    private FloatProperty length;
    private FloatProperty width;
    private RectangularObstacleView rectangularObstacleView;

    public RectangularObstacle(float pos_x, float pos_y){
        rectangularObstacleView = new RectangularObstacleView(this);
        this.setPos_X(pos_x);
        this.setPos_Y(pos_y);
        this.setLength(200);
        this.setWidth(200);
    }

    public RectangularObstacleView getRectangularObstacleView() {
        return rectangularObstacleView;
    }

    @Override
    public void setBindings() {
        this.getPos_XProperty().bindBidirectional(this.rectangularObstacleView.layoutXProperty());
        this.getPos_YProperty().bindBidirectional(this.rectangularObstacleView.layoutYProperty());
    }

    public float getLength() {
        return length.get();
    }

    public FloatProperty lengthProperty() {
        return length;
    }

    public void setLength(float length) {
        if(this.length==null){
            this.length = new SimpleFloatProperty();
        }
        this.length.set(length);
    }

    public float getWidth() {
        return width.get();
    }

    public FloatProperty widthProperty() {
        return width;
    }

    public void setWidth(float width) {
        if(this.width==null){
            this.width = new SimpleFloatProperty();
        }
        this.width.set(width);
    }
}
