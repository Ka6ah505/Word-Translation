package s.mironov3.gmail.com.word_translation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
    String nameTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoring_mode);
        lsMode = (ListView) findViewById(R.id.lvMode);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item_monitoring, items);
        lsMode.setAdapter(adapter);

        final Intent intent = getIntent();
        idTheme = intent.getStringExtra("idTheme");
        nameTheme = intent.getStringExtra("nameTheme");

        lsMode.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        Intent intentPractice = new Intent(MonitoringModeActivity.this, PracticeActivity.class);
                        intentPractice.putExtra("idTheme", idTheme);
                        startActivity(intentPractice);
                        break;
                    case 1:
                        Intent intentSMitring = new Intent(MonitoringModeActivity.this, SelfMonitoringActivity.class);
                        intentSMitring.putExtra("idTheme", idTheme);
                        startActivity(intentSMitring);
                        break;
                    case 2:
                        Intent intentExamination = new Intent(MonitoringModeActivity.this, ExaminationActivity.class);
                        intentExamination.putExtra("idTheme", idTheme);
                        intentExamination.putExtra("nameTheme", nameTheme);
                        startActivity(intentExamination);
                        break;
                }
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
