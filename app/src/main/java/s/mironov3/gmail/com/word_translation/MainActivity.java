package s.mironov3.gmail.com.word_translation;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends Activity {

    ArrayList<ListItemTheme> listTheme = new ArrayList<ListItemTheme>();
    ListItemAdapter liAdapter;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor cursor;
        Log.d("MYLOG", "----------TABLE THEME------------");
        cursor = db.query(dbHelper.getTableTheme(), null, null, null, null, null, null);
        initArrayList(cursor);
        liAdapter = new ListItemAdapter(this, listTheme);
        cursor.close();
        ListView lvMain = (ListView) findViewById(R.id.listTheme);
        lvMain.setAdapter(liAdapter);
        Log.d("MYLOG", "---------------------------------");
    }

    private void initArrayList(Cursor cursor) {
        if( cursor != null ) {
            if (cursor.moveToFirst()) {
                do {
                    int i = countWord(getTable(cursor.getInt(cursor.getColumnIndex("id"))));

                    listTheme.add(new ListItemTheme(cursor.getInt(cursor.getColumnIndex("id")),
                            cursor.getString(cursor.getColumnIndex("name")),
                            cursor.getInt(cursor.getColumnIndex("rating")),
                            i));
                } while (cursor.moveToNext());
            } else {
                Log.d("MYLOG", "BEDA!!!");
            }
        }
    }

    private Cursor getTable(int i) {
        String sqlQuery = "select * from "+dbHelper.TABLE_WORD+" where "+dbHelper.THEME_TRANSLATE_ID+" = "+i;
        return dbHelper.getReadableDatabase().rawQuery(sqlQuery,null);
    }

    private int countWord(Cursor cursor) {
        int i=0;
        if( cursor != null ) {
            if(cursor.moveToFirst()){
                do {
                    i++;
                    Log.d("MYLOG", "count: "+i);
                } while (cursor.moveToNext());
            }
        } else {
            Log.d("MYLOG", "BEDA!!!");
        }
        return i;
    }

    private void logCursor(Cursor cursor) {
        if( cursor != null ) {
            if(cursor.moveToFirst()){
                String str;
                do {
                    str = "";
                    for( String cn: cursor.getColumnNames() ) {
                        str = str.concat(cn + " = " + cursor.getString(cursor.getColumnIndex(cn)) + "; ");
                    }
                    Log.d("MYLOG", str);
                } while (cursor.moveToNext());
            }
        } else {
            Log.d("MYLOG", "BEDA!!!");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
