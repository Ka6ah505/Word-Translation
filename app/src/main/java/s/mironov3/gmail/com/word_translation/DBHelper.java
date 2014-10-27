package s.mironov3.gmail.com.word_translation;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    // Поля и название таблицы с темами набора слов
    public static final String TABLE_THEME = "listItemTheme";
    public static final String THEME_ID = "id";
    public static final String THEME_NAME = "name";
    public static final String THEME_RATING = "rating";

    // Поля и название таблицы с парами слов "слово-первод"
    public static final String TABLE_WORD = "translate";
    public static final String TRANSLATE_ID = "id";
    public static final String WORD = "word";
    public static final String TRANSLATE = "translate";
    public static final String THEME_TRANSLATE_ID = "thId";

    // временные данные
    String[] nameTheme = { "FAMILY", "CITY", "COLORS", "SPORT" };

    String[] wordEnglish = { "baby", "brother", "red", "soccer", "street", "pavement" };
    String[] wordTranslate = { "ребёнок", "брат", "красный", "футбол", "улица", "рекламный щит" };
    int[] word_posid = { 1, 1, 3, 4, 2, 2 };

    public DBHelper(Context context) {
        // конструктор суперкласса
        super(context, "myDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        ContentValues cv = new ContentValues();

        // создаем таблицу с названием темы
        db.execSQL("create table " + TABLE_THEME + " ("
                + THEME_ID + " integer primary key autoincrement,"
                + THEME_NAME + " text,"
                + THEME_RATING + " integer"
                +  ");");
        // заполняем таблицу
        for(int i = 0; i < nameTheme.length; i++ ) {
            cv.clear();
            cv.put(THEME_NAME, nameTheme[i]);
            cv.put(THEME_RATING, 0);
            db.insert(TABLE_THEME, null, cv);
        }

        // создаём таблицу с парой слово-перевод
        db.execSQL("create table " + TABLE_WORD + " ("
                + TRANSLATE_ID + " integer primary key autoincrement,"
                + WORD + " text,"
                + TRANSLATE + " text,"
                + THEME_TRANSLATE_ID + " integer"
                +  ");");
        // заполнение таблицы
        for(int i = 0; i < wordEnglish.length; i++) {
            cv.clear();
            cv.put(WORD, wordEnglish[i]);
            cv.put(TRANSLATE, wordTranslate[i]);
            cv.put(THEME_TRANSLATE_ID, word_posid[i]);
            db.insert(TABLE_WORD, null, cv);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static String getTableTheme() {
        return TABLE_THEME;
    }
    public static String getThemeId() {
        return THEME_ID;
    }
    public static String getThemeName() {
        return THEME_NAME;
    }
    public static String getTableWord() {
        return TABLE_WORD;
    }
    public static String getTranslateId() {
        return TRANSLATE_ID;
    }
    public static String getWord() {
        return WORD;
    }
    public static String getTranslate() {
        return TRANSLATE;
    }
    public static String getThemeTranslateId() {
        return THEME_TRANSLATE_ID;
    }
    public static String getThemeRating() {
        return THEME_RATING;
    }
}
