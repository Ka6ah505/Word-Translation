package s.mironov3.gmail.com.word_translation;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
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
    String nameTheme;
    TextView textView;
    EditText editText;
    Button button;
    int i=0;
    AlertDialog.Builder adb;
    int trueAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examination);
        indicationAdapter = new ListItemIndicationAdapter(this, arrListIndication);
        initListImage();
        final Intent intent = getIntent();
        idTheme = intent.getStringExtra("idTheme");
        nameTheme = intent.getStringExtra("nameTheme");
        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        initArrayList(db);
        db.close();
        trueAnswers = 0;
        listView = (ListView) findViewById(R.id.list_indication_answers);
        listView.setAdapter(indicationAdapter);
        textView = (TextView) findViewById(R.id.english_word);
        editText = (EditText) findViewById(R.id.input_answer);
        textView.setText(indicationAdapter.getIndication(0).getWord());
        button = (Button) findViewById(R.id.click_check);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswerIndicator();
                i++;
                if(arrListIndication.size() > i ) {
                    textView.setText(indicationAdapter.getIndication(i).getWord());
                } else if(arrListIndication.size() == i ) {
                    writeRatingInBaseData(calculationRating());
                }
            }
        });
    }

    private void initListImage() {
        Resources contextResources = indicationAdapter.getCtx().getResources();
        imageList.add(contextResources.getDrawable(R.drawable.def));
        imageList.add(contextResources.getDrawable(R.drawable.tr));
        imageList.add(contextResources.getDrawable(R.drawable.er));
    }

    private void writeRatingInBaseData(int i) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        //cv.put(dbHelper.THEME_ID, idTheme);
        cv.put(dbHelper.THEME_NAME, nameTheme);
        cv.put(dbHelper.THEME_RATING, i);

        db.update(dbHelper.TABLE_THEME, cv, dbHelper.THEME_ID+"="+idTheme,null);
    }

    private int calculationRating() {
        double d = (double)trueAnswers;
        double c = (double)arrListIndication.size();
        double i = d/c;
        Log.d("MYLOG", ""+i);
        Log.d("MYLOG", ""+trueAnswers);
        Log.d("MYLOG", ""+arrListIndication.size());
        int r;
        if( 0.85 <= i && i == 1) {
            r=5;
        }else if( 0.7 <= i && i < 0.85 ) {
            r=4;
        }else if(0.55 <= i && i < 0.7) {
            r=3;
        }else {
            r=2;
        }
        createDialogCancel(r);
        return r;
    }

    private void checkAnswerIndicator() {
        String str = editText.getText().toString();
        if(str.equals(indicationAdapter.getIndication(i).getTraslate())) {
            arrListIndication.get(i).setImage(imageList.get(1));
            trueAnswers = trueAnswers+1;
        } else {
            arrListIndication.get(i).setImage(imageList.get(2));
        }
        editText.setText("");
        indicationAdapter.notifyDataSetChanged();
    }

    private void createDialogCancel(int rating) {
        Log.d("MYLOG", "RATING:  "+rating);
        adb = new AlertDialog.Builder(this);
        adb.setTitle(R.string.title_dialog_cancel);
        adb.setMessage("You rating is "+rating);
        //adb.setNegativeButton(R.string.negative_button_exit, clickListener);
        adb.setPositiveButton(R.string.positive_button, clickListener);
        adb.show();
    }

    OnClickListener clickListener = new OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int which) {
            switch (which) {
                case Dialog.BUTTON_POSITIVE:
                    finish();
                    break;
                case Dialog.BUTTON_NEGATIVE:
                    //finish();
                    break;
            }
        }
    };

    private void initArrayList(SQLiteDatabase db) {
        Cursor cursor = db.rawQuery("select * from " + dbHelper.TABLE_WORD
                + " where " + dbHelper.THEME_TRANSLATE_ID
                + " = " + idTheme, null);
        int b=0;
        if( cursor != null ) {
            if (cursor.moveToFirst()) {
                do {
                    b++;
                    arrListIndication.add(new ListItemIndication(b, imageList.get(0),
                            cursor.getString(cursor.getColumnIndex(dbHelper.WORD)),
                            cursor.getString(cursor.getColumnIndex(dbHelper.TRANSLATE)))
                    );

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

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        adb = new AlertDialog.Builder(this);
        adb.setTitle(R.string.title_dialog_exit);
        adb.setMessage(R.string.message_dialog_exit);
        adb.setNegativeButton(R.string.negative_button_exit, clickListener);
        adb.setPositiveButton(R.string.positive_button_exit, clickListener);
        adb.show();
    }
}
