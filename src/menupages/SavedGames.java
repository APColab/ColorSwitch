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
    File folder = new File("./src/savedGames");
    public static ArrayList<File> savedgames;

    public SavedGames()
    {
        savedgames = new ArrayList<>(Arrays.asList(folder.listFiles()));
    }

    public static void loadSavedGames()
    {
        System.out.println("load");
        ArrayList<Game> games = new ArrayList<>();
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

    }

    public static void addSavedGames()
    {

    }

    public static void openGame()
    {

    }

    public static ArrayList<File> getSavedgames() {
        return savedgames;
    }

    public static void setSavedgames(ArrayList<File> savedgames) {
        SavedGames.savedgames = savedgames;
    }
}
