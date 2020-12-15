package model;

import javafx.scene.shape.Shape;
import view.StarView;

import java.util.ArrayList;

public class Star extends Collectable{

    private StarView starView;

    private final String IMAGE_PATH = "resources/star.png";

    public Star(Obstacle o){
        this(285,0);
        this.findPosition(o);
    }

    public Star(float pos_x, float pos_y){
        this.setPos_X(pos_x);
        this.setPos_Y(pos_y);
        starView = new StarView(this);
        setbindings();
    }

    public String getIMAGE_PATH() {
        return IMAGE_PATH;
    }

    public StarView getStarView() {
        return starView;
    }

    @Override
    public void setbindings() {
        this.pos_XProperty().bindBidirectional(starView.layoutXProperty());
        this.pos_YProperty().bindBidirectional(starView.layoutYProperty());
    }

    @Override
    public void findPosition(Obstacle o) {
        this.setPos_Y(o.getPos_Y()+85);
    }

    @Override
    public ArrayList<Shape> getCollidables() {
        return null;
    }

    @Override
    public boolean isColliding(Collidable collidable) {
        return false;
    }

    @Override
    public void handleCollision() {

    }
}
