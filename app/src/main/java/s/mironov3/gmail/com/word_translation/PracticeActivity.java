package s.mironov3.gmail.com.word_translation;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;


public class PracticeActivity extends Activity {

    ArrayList<PairOfWordTranslation> powtlList = new ArrayList<PairOfWordTranslation>();
    PairOfWordTranslationAdapter powtAdapter;
    ListView liPair;
    DBHelper dbHelper;
    String idTheme;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        final Intent intent = getIntent();
        idTheme = intent.getStringExtra("idTheme");
        i = Integer.valueOf(idTheme);
        Log.d("MYLOG", "принял ID темы в Practice: №" + i);

        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        initArrayList(db);

        liPair = (ListView) findViewById(R.id.list_pair);
        powtAdapter = new PairOfWordTranslationAdapter(this, powtlList);
        liPair.setAdapter(powtAdapter);
    }

    private void initArrayList(SQLiteDatabase db) {
        Cursor cursor = db.rawQuery("select * from " + dbHelper.TABLE_WORD
                + " where " + dbHelper.THEME_TRANSLATE_ID
                + " = " + i, null);
        int b=0;
        if( cursor != null ) {
            if (cursor.moveToFirst()) {
                do {
                    b++;
                    PairOfWordTranslation p = new PairOfWordTranslation(cursor.getString(cursor.getColumnIndex(dbHelper.WORD)),
                            cursor.getString(cursor.getColumnIndex(dbHelper.TRANSLATE)));
                    powtlList.add(p);
                    //Log.d("MYLOG", "\n word \t"+p.getWord()+"\ntranslate\t"+p.getTranslate());
                } while (cursor.moveToNext());
            } else {
                Log.d("MYLOG", "BEDA!!!");
            }
        }
        cursor.close();
        Log.d("MYLOG", "пар слов найдено " + b);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.practice, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
