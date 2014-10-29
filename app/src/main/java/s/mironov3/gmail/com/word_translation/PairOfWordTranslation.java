package s.mironov3.gmail.com.word_translation;

/**
 * Created by Sergey on 29.10.2014.
 */
public class PairOfWordTranslation {
    private String word;
    private String translate;

    public PairOfWordTranslation(String word, String translate) {

        this.word = word;
        this.translate = translate;
    }

    public String getWord() {
        return word;
    }

    public String getTranslate() {
        return translate;
    }

}
