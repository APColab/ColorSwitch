package model;

import view.CrossObstacleView;

public class CrossObstacle extends Obstacle{

    public CrossObstacle(float pos_x,float pos_y){
        this.setPos_X(pos_x);
        this.setPos_Y(pos_y);
        setObstacleView(new CrossObstacleView(this));
        setBindings();
    }



    @Override
    public void setBindings() {
        this.pos_XProperty().bindBidirectional(this.getObstacleView().layoutXProperty());
        this.pos_YProperty().bindBidirectional(this.getObstacleView().layoutYProperty());
    }
}
