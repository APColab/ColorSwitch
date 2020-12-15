package model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.scene.paint.Color;
import view.TriangleObstacleView;

public class TriangleObstacle extends Obstacle{

    private FloatProperty centerToVertex;

    public TriangleObstacle(float pos_x, float pos_y, Color c){
        this.setPos_X(pos_x);
        this.setPos_Y(pos_y);
        this.centerToVertex = new SimpleFloatProperty(this.getHeight()*0.707f);
        setObstacleView(new TriangleObstacleView(this,c));
        setBindings();
    }

    public float getCenterToVertex() {
        return centerToVertex.get();
    }

    public FloatProperty centerToVertexProperty() {
        return centerToVertex;
    }

    public void setCenterToVertex(float centerToVertex) {
        this.centerToVertex.set(centerToVertex);
    }

    @Override
    public void setBindings() {
        this.pos_XProperty().bindBidirectional(this.getObstacleView().layoutXProperty());
        this.pos_YProperty().bindBidirectional(this.getObstacleView().layoutYProperty());
    }


    @Override
    public void handleCollision() {

    }
}
