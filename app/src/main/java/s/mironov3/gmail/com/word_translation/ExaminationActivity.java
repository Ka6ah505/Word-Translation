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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class ExaminationActivity extends Activity {

    ListItemIndicationAdapter indicationAdapter;
    ArrayList<ListItemIndication> arrListIndication = new ArrayList<ListItemIndication>();
    ArrayList<Drawable> imageList = new ArrayList<Drawable>();
    ListView listView;
    DBHelper dbHelper;
    String idTheme;
    TextView textView;
    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examination);
        indicationAdapter = new ListItemIndicationAdapter(this, arrListIndication);
        Resources contextResources = indicationAdapter.getCtx().getResources();
        imageList.add(contextResources.getDrawable(R.drawable.def));
        imageList.add(contextResources.getDrawable(R.drawable.tr));
        imageList.add(contextResources.getDrawable(R.drawable.er));

        final Intent intent = getIntent();
        idTheme = intent.getStringExtra("idTheme");

        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        initArrayList(db);

        listView = (ListView) findViewById(R.id.list_indication_answers);
        listView.setAdapter(indicationAdapter);

        textView = (TextView) findViewById(R.id.english_word);
        editText = (EditText) findViewById(R.id.input_answer);
        button = (Button) findViewById(R.id.click_check);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = editText.getText().toString();
                if(str.equals("lom")) {
                    //indicationAdapter.getItemId(4).;
                    Log.d("MYLOG", "VOT I V'SE");
                } else {
                    Log.d("MYLOG", "VOT I NE V'SE");
                }
            }
        });
    }

    private void initArrayList(SQLiteDatabase db) {
        Cursor cursor = db.rawQuery("select * from " + dbHelper.TABLE_WORD
                + " where " + dbHelper.THEME_TRANSLATE_ID
                + " = " + idTheme, null);
        int b=0;
        if( cursor != null ) {
            if (cursor.moveToFirst()) {
                do {
                    b++;
                    arrListIndication.add(new ListItemIndication(b, imageList.get(0)));
                } while (cursor.moveToNext());
            } else {
                Log.d("MYLOG", "BEDA!!!");
            }
        }
        cursor.close();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.examination, menu);
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
