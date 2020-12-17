package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class CustomObstacleLoader {
    private ArrayList< ArrayList<Line> > obstacleList;

    public CustomObstacleLoader(){
        this.obstacleList = new ArrayList<>();

        File folder = new File("./src/savedObstacles");
        ArrayList<File> obstacles = new ArrayList<>(Arrays.asList(folder.listFiles()));
        for(File o:obstacles){
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(o));
                ArrayList<Line> lineArrayList = new ArrayList<>();
                for(int i=0;i<4;i++){
                    Line line = new Line(ois.readDouble(),ois.readDouble(),ois.readDouble(),ois.readDouble());
                    switch(i){
                        case 0->{
                            line.setStroke(Color.RED);
                        }
                        case 1->{
                            line.setStroke(Color.GREEN);
                        }
                        case 2->{
                            line.setStroke(Color.BLUE);
                        }
                        case 3->{
                            line.setStroke(Color.YELLOW);
                        }
                    }
                    lineArrayList.add(line);
                }
                this.obstacleList.add(lineArrayList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Obstacle getObstacle(float pos_x, float pos_y){
        if(obstacleList.isEmpty()){
            return new CircularObstacle(pos_x,pos_y);
        }
        Random rand = new Random();
        int n = rand.nextInt(obstacleList.size());
        return new CustomObstacle(pos_x,pos_y,obstacleList.get(n));
    }

}
