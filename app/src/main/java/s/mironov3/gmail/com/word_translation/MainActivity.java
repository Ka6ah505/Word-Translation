package s.mironov3.gmail.com.word_translation;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends Activity {

    ArrayList<ListItemTheme> listItemTheme = new ArrayList<ListItemTheme>();
    ArrayList<Drawable> imageList = new ArrayList<Drawable>();
    ListItemAdapter liAdapter;
    DBHelper dbHelper;
    ListView lvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        liAdapter = new ListItemAdapter(this, listItemTheme);

        Resources contextResources = liAdapter.getCtx().getResources();

        imageList.add(contextResources.getDrawable(R.drawable.family));
        imageList.add(contextResources.getDrawable(R.drawable.city));
        imageList.add(contextResources.getDrawable(R.drawable.color));
        imageList.add(contextResources.getDrawable(R.drawable.sport));

        /*dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(dbHelper.getTableTheme(), null, null, null, null, null, null);
        initArrayList(cursor);
        cursor.close();*/

        lvMain = (ListView) findViewById(R.id.listTheme);
        lvMain.setAdapter(liAdapter);
        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, MonitoringModeActivity.class);
                intent.putExtra("idTheme", adapterView.getItemIdAtPosition(i + 1) + "");
                intent.putExtra("nameTheme", liAdapter.getTheme(i).getName());
                Log.d("MYLOG", "передаю name темы в активи выбора режима: " + liAdapter.getTheme(i).getName());
                Log.d("MYLOG", "передаю id темы в активи выбора режима: " + adapterView.getItemIdAtPosition(i + 1));
                startActivity(intent);
            }
        });
        Log.d("ACT", "onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        listItemTheme.clear();
        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(dbHelper.getTableTheme(), null, null, null, null, null, null);
        initArrayList(cursor);
        cursor.close();
        liAdapter.notifyDataSetChanged();
        Log.d("ACT", "onStart()");
    }

    private void initArrayList(Cursor cursor) {
        if( cursor != null ) {
            if (cursor.moveToFirst()) {
                do {
                    int i = countWord(getTable(cursor.getInt(cursor.getColumnIndex("id"))));
                    int id = cursor.getInt(cursor.getColumnIndex("id"));

                    listItemTheme.add(new ListItemTheme(cursor.getInt(cursor.getColumnIndex("id")),
                            cursor.getString(cursor.getColumnIndex("name")),
                            cursor.getInt(cursor.getColumnIndex("rating")),
                            i, imageList.get(id-1) ));
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
                } while (cursor.moveToNext());
            }
        } else {
            Log.d("MYLOG", "BEDA!!!");
        }
        return i;
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
