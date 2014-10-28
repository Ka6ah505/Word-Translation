package s.mironov3.gmail.com.word_translation;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;


public class PracticeActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);
        TableLayout tableLayout = (TableLayout) findViewById(R.id.table_word);

        TableRow tableRow = new TableRow(this);
        /*for(int i = 0; i < 8; i++) {
            TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
            tableRow.setLayoutParams(params);

            TableRow.LayoutParams param = new TableRow.LayoutParams();
            param.setMargins(0, 0, 0, 0);
            TextView textViewWord = new TextView(this);
            textViewWord.setText("sas");
            textViewWord.setLayoutParams(param);

            TableRow.LayoutParams param1 = new TableRow.LayoutParams();
            param.setMargins(0, 2, 0, 0);
            TextView textViewWord1 = new TextView(this);
            textViewWord.setText("сас");
            textViewWord.setLayoutParams(param1);

            tableRow.addView(textViewWord);
            tableRow.addView(textViewWord1);
            tableLayout.addView(tableRow);
        }*/

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
