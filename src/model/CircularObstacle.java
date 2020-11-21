package model;

public class CircularObstacle extends Obstacle{
    private float radius;
    private float width;
    public CircularObstacle(float pos_x, float pos_y, float r, float w){
        this.setPos_X(pos_x);
        this.setPos_Y(pos_y);
        this.radius = r;
        this.width = w;
    }

    public CircularObstacle() {
        this.setPos_X(100);
        this.setPos_Y(100);
        this.radius = 60.0f;
        this.width = 10.0f;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }
}
