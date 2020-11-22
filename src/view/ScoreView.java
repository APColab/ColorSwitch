package view;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ScoreView extends Label
{
    //int score = 50;
    public ScoreView()
    {
        setLayoutX(30);
        setLayoutY(20);
        setText("Score: 42");
        try {
            setFont(Font.loadFont(new FileInputStream("src/menupages/resources/eroded.ttf"), 18));
        } catch (FileNotFoundException e) {
            setFont(new Font("Verdana", 18));
        }
        setTextFill(Color.WHITE);
        setPrefHeight(30);
        setPrefWidth(100);
    }
}
