package model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;

public abstract class Collectable implements Collidable{
    private FloatProperty pos_X;
    private FloatProperty pos_Y;

    public float getPos_X() {
        return pos_X.get();
    }

    public FloatProperty pos_XProperty() {
        return pos_X;
    }

    public void setPos_X(float pos_X) {
        if(this.pos_X==null){
            this.pos_X = new SimpleFloatProperty();
        }
        this.pos_X.set(pos_X);
    }

    public float getPos_Y() {
        return pos_Y.get();
    }

    public FloatProperty pos_YProperty() {
        return pos_Y;
    }

    public void setPos_Y(float pos_Y) {
        if(this.pos_Y==null){
            this.pos_Y = new SimpleFloatProperty();
        }
        this.pos_Y.set(pos_Y);
    }

    public abstract void setbindings();
    public abstract void findPosition(Obstacle o);
}