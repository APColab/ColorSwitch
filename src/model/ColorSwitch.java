package model;

import javafx.scene.shape.Shape;
import view.ColorSwitchView;
import view.StarView;

import java.util.ArrayList;

public class ColorSwitch extends Collectable{

    private ColorSwitchView colorSwitchView;

    private final String IMAGE_PATH = "resources/colorswitch.png";

    public ColorSwitch(Obstacle o){
        this(285,0);
        this.findPosition(o);
    }

    public ColorSwitch(float pos_x, float pos_y){
        this.setPos_X(pos_x);
        this.setPos_Y(pos_y);
        colorSwitchView = new ColorSwitchView(this);
        setbindings();
    }

    public ColorSwitchView getColorSwitchView() {
        return colorSwitchView;
    }

    public String getIMAGE_PATH() {
        return IMAGE_PATH;
    }

    @Override
    public void setbindings() {
        this.pos_XProperty().bindBidirectional(colorSwitchView.layoutXProperty());
        this.pos_YProperty().bindBidirectional(colorSwitchView.layoutYProperty());
    }

    @Override
    public void findPosition(Obstacle o) {
        this.setPos_Y(o.getPos_Y()-115);
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
