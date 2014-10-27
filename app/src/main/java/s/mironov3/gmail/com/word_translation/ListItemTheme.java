package s.mironov3.gmail.com.word_translation;

import android.graphics.drawable.Drawable;

public class ListItemTheme {
    int id;
    String Name;
    int rating;
    int totalWord;
    //ImageView image;
    Drawable image;

    public ListItemTheme(int id, String name, int rating, int totalWord, Drawable image) {
        this.id = id;
        Name = name;
        this.rating = rating;
        this.totalWord = totalWord;
        this.image = image;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public int getRating() {
        return rating;
    }

    public int getTotalWord() {
        return totalWord;
    }

    public Drawable getImage() {
        return image;
    }
}
