package s.mironov3.gmail.com.word_translation;

import android.graphics.drawable.Drawable;

/**
 * Created by Sergey on 10.11.2014.
 */
public class ListItemIndication {
    int number;
    Drawable image;

    public ListItemIndication(int number) {
        this.number = number;
    }

    public ListItemIndication(int number, Drawable image) {
        this.number = number;
        this.image = image;

    }

    public int getNumber() {
        return number;
    }

    public Drawable getImage() {
        return image;
    }
}
