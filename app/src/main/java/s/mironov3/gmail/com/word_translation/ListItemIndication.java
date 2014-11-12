package s.mironov3.gmail.com.word_translation;

import android.graphics.drawable.Drawable;

/**
 * Created by Sergey on 10.11.2014.
 */
public class ListItemIndication {
    int number;
    Drawable image;
    String word;
    String translate;

    public String getWord() {
        return word;
    }

    public String getTraslate() {
        return translate;
    }

    public ListItemIndication(int number, Drawable image, String word, String translate) {

        this.number = number;
        this.image = image;
        this.word = word;
        this.translate = translate;
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

    public void setImage(Drawable image) {
        this.image = image;
    }
}
