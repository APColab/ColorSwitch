package model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;

public abstract class Obstacle implements Collidable {
    private FloatProperty pos_X;
    private FloatProperty pos_Y;

    public float getPos_X() {
        return pos_X.floatValue();
    }

    public FloatProperty getPos_XProperty(){
        return pos_X;
    }
    public void setPos_X(float pos_X) {
        if(this.pos_X==null){
            this.pos_X = new SimpleFloatProperty();
        }
        this.pos_X.setValue(pos_X);
    }

    public float getPos_Y() {
        return pos_Y.floatValue();
    }

    public FloatProperty getPos_YProperty(){
        return pos_Y;
    }

    public void setPos_Y(float pos_Y) {
        if(this.pos_Y==null){
            this.pos_Y = new SimpleFloatProperty();
        }
        this.pos_Y.setValue(pos_Y);
    }

    public abstract void setBindings();
}
