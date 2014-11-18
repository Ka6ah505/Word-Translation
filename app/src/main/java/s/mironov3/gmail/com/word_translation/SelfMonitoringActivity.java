package s.mironov3.gmail.com.word_translation;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewAnimator;
import android.view.View.OnClickListener;


public class SelfMonitoringActivity extends Activity implements OnClickListener {

    ViewAnimator viewAnimator;
    Button buttonNext, buttonPrev;
    private DBHelper dbHelper;
    String idTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_monitrig);

        viewAnimator = (ViewAnimator) findViewById(R.id.viewAnimator);
        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        final Intent intent = getIntent();
        idTheme = intent.getStringExtra("idTheme");

        initItemsViewAnimator(db);
        db.close();

        viewAnimator.setInAnimation(this, R.anim.translate_next);
        viewAnimator.setOutAnimation(this, R.anim.translate_prev);

        buttonNext = (Button) findViewById(R.id.remember);
        buttonPrev = (Button) findViewById(R.id.not_remember);

        buttonNext.setOnClickListener(this);
        buttonPrev.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Resources contextResources = getResources();
        switch (v.getId()) {
            case R.id.remember:
                viewAnimator.getCurrentView().setBackground(contextResources.getDrawable(R.drawable.fon_true));
                TextView et = (TextView) viewAnimator.getCurrentView().findViewById(R.id.checking_word_translate);
                et.setVisibility(View.VISIBLE);
                viewAnimator.showNext();
                break;
            case R.id.not_remember:
                viewAnimator.getCurrentView().setBackground(contextResources.getDrawable(R.drawable.fon_false));
                TextView tv = (TextView) viewAnimator.getCurrentView().findViewById(R.id.checking_word_translate);
                tv.setVisibility(View.VISIBLE);
                viewAnimator.showNext();
                break;
        }
    }

    private void initItemsViewAnimator(SQLiteDatabase database) {
        Cursor cursor = database.rawQuery("select * from " + dbHelper.TABLE_WORD
                + " where " + dbHelper.THEME_TRANSLATE_ID
                + " = " + idTheme, null);
        LayoutInflater inflater = getLayoutInflater();
        if( cursor != null ) {
            if (cursor.moveToFirst()) {
                do {
                    View view = inflater.inflate(R.layout.card_word, viewAnimator, false);
                    TextView tv = (TextView) view.findViewById(R.id.checking_word);
                    tv.setText(cursor.getString(cursor.getColumnIndex(dbHelper.WORD)));
                    TextView tv1 = (TextView) view.findViewById(R.id.checking_word_translate);
                    tv1.setText(cursor.getString(cursor.getColumnIndex(dbHelper.TRANSLATE)));
                    tv1.setVisibility(View.INVISIBLE);
                    Resources contextResources = getResources();
                    view.setBackground(contextResources.getDrawable(R.drawable.fon_default));
                    ViewGroup.LayoutParams lp = view.getLayoutParams();
                    viewAnimator.addView(view, lp);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.self_monitrig, menu);
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
