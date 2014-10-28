package s.mironov3.gmail.com.word_translation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MonitoringModeActivity extends Activity {

    ListView lsMode;
    String [] items = {"Practice", "Self-monitoring", "Examination"};
    String idTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoring_mode);
        lsMode = (ListView) findViewById(R.id.lvMode);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.text_item_view, items);
        lsMode.setAdapter(adapter);

        Intent intent = getIntent();
        idTheme = intent.getStringExtra("idTheme");
        Integer i = Integer.valueOf(idTheme);
        Log.d("MYLOG", "принял ID темы: №" + i);

        lsMode.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Intent intent1 = new Intent(MonitoringModeActivity.class, );
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.monitoring_mode, menu);
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
