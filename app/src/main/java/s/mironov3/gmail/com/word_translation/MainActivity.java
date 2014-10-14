package s.mironov3.gmail.com.word_translation;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor cursor;
        Log.d("MYLOG", "----------TABLE THEME------------");
        cursor = db.query(dbHelper.getTableTheme(), null, null, null, null, null, null);
        logCursor(cursor);
        cursor.close();
        Log.d("MYLOG", "---------------------------------");

        Log.d("MYLOG", "----------TABLE WORD------------");
        cursor = db.query(dbHelper.getTableWord(), null, null, null, null, null, null);
        logCursor(cursor);
        cursor.close();
        Log.d("MYLOG", "---------------------------------");
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
