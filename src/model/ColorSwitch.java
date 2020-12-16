package model;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import view.ColorSwitchView;
import view.StarView;

import java.util.ArrayList;

public class ColorSwitch extends Collectable{

    private ColorSwitchView colorSwitchView;
    private Color colorSwitchColor;

    private final String IMAGE_PATH = "resources/colorswitch.png";

    public ColorSwitch(Game game){
        this(game,285,0,Color.RED);
    }

    public ColorSwitch(Game game,Obstacle o){
        this(game,285,0,Color.RED);
        this.findPosition(o);
    }

    public ColorSwitch(Game game,float pos_x, float pos_y){
        this(game,pos_x,pos_y,Color.RED);
    }

    public ColorSwitch(Game game,float pos_x, float pos_y,Color color){
        this.setPos_X(pos_x);
        this.setPos_Y(pos_y);
        this.setGame(game);
        colorSwitchColor = color;
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
    public ImageView getCollectableView() {
        return colorSwitchView;
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
        ArrayList<Shape> shapeList = collidable.getCollidables();
        boolean c = false;
        for(Shape shape:shapeList){
            c = c || colorSwitchView.localToScene(colorSwitchView.getBoundsInLocal()).intersects(shape.localToScene(shape.getBoundsInLocal()));
        }
        return c;
    }

    @Override
    public void handleCollision() {
        getGame().getBall().setBallColor(colorSwitchColor);
    }
}
