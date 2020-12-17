package menupages;

import model.Game;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class SavedGames
{

    public static void loadSavedGames()
    {
        System.out.println("load");
        ArrayList<File> savedgames;
        ArrayList<Game> games = new ArrayList<>();
        savedgames = new ArrayList<>(Arrays.asList(new File("./src/savedGames").listFiles()));
        for(File f:savedgames){
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
                try {
                    games.add((Game) ois.readObject());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        LoadGameMenuView load = new LoadGameMenuView(games);
    }

    public static void addSavedGames()
    {

    }

    public static void openGame()
    {

    }


}
