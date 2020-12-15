package model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.scene.paint.Color;
import view.DoubleCircleObstacleView;

public class DoubleCircleObstacle extends Obstacle{
    private final CircularObstacle outer;
    private FloatProperty innerRadius;

    public DoubleCircleObstacle(float pos_x, float pos_y, Color color){
        this.outer = new CircularObstacle(pos_x,pos_y);
        this.setPos_X(pos_x);
        this.setPos_Y(pos_y);
        this.innerRadius = new SimpleFloatProperty(outer.getRadius()-15);
        setObstacleView(new DoubleCircleObstacleView(this, color));
        setBindings();
    }

    public CircularObstacle getOuter() {
        return outer;
    }

    public float getInnerRadius() {
        return innerRadius.get();
    }

    public FloatProperty innerRadiusProperty() {
        return innerRadius;
    }

    public void setInnerRadius(float innerRadius) {
        this.innerRadius.set(innerRadius);
    }

    @Override
    public void setBindings() {
        this.pos_XProperty().bindBidirectional(this.getObstacleView().layoutXProperty());
        this.pos_YProperty().bindBidirectional(this.getObstacleView().layoutYProperty());
    }

    @Override
    public boolean isColliding(Collidable collidable) {
        return super.isColliding(collidable) || outer.isColliding(collidable);
    }

    @Override
    public void handleCollision() {

    }
}
