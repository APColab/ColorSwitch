package model;

import java.io.*;

public class CollectedStars {
    private Long totalStars;

    public CollectedStars(){
        totalStars = 0L;
        try {
            this.loadStars();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long getTotalStars() {
        return totalStars;
    }

    public void setTotalStars(long totalStars) {
        this.totalStars = totalStars;
    }

    public void saveStars() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./stars.sav"));
        oos.writeLong(this.totalStars);
        oos.close();
    }

    public void loadStars() throws IOException{
        FileInputStream fis;
        try {
            fis = new FileInputStream("./stars.sav");
        } catch (FileNotFoundException e) {
            totalStars = 0L;
            return;
        }
        ObjectInputStream ois = new ObjectInputStream(fis);
        this.totalStars = ois.readLong();
        ois.close();
    }
}
