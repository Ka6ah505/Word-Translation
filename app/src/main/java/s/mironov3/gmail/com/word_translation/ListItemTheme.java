package s.mironov3.gmail.com.word_translation;

public class ListItemTheme {
    int id;
    String Name;
    int rating;
    int totalWord;

    public ListItemTheme(int id, String name, int rating, int totalWord) {
        this.id = id;
        Name = name;
        this.rating = rating;
        this.totalWord = totalWord;
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
}
