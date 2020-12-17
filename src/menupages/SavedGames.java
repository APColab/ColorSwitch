package menupages;

import model.Game;

import java.io.File;
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
        LoadGameMenuView load = new LoadGameMenuView(savedgames);
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
