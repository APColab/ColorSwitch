package model;

import javafx.scene.paint.Color;

public class ColorIterator {

    public static Color next(Color color){
        if(color.equals(Color.RED)){
            return Color.GREEN;
        }else if(color.equals(Color.GREEN)){
            return Color.BLUE;
        }else if(color.equals(Color.BLUE)){
            return Color.YELLOW;
        }
        return Color.RED;
    }

    public static Color nextN(Color color,int index){
        index = index%4;
        if(index==0){
            return color;
        }
        if(index==1){
            return next(color);
        }
        return nextN(next(color),index-1);
    }
}
