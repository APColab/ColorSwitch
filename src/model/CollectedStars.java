package model;

import java.io.*;

public class CollectedStars {
    private Long totalStars;

    public CollectedStars(){
        totalStars = 0L;
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
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./stars.sav"));
        this.totalStars = ois.readLong();
        ois.close();
    }
}
