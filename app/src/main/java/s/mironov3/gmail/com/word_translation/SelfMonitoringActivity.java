package s.mironov3.gmail.com.word_translation;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewAnimator;


public class SelfMonitoringActivity extends Activity {

    ViewAnimator viewAnimator;
    Button buttonNext, buttonPrev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_monitrig);

        viewAnimator = (ViewAnimator) findViewById(R.id.viewAnimator);
        LayoutInflater inflater = getLayoutInflater();

        for (int i=0; i<4; i++ ){
            View view = inflater.inflate(R.layout.card_word, viewAnimator, false);
            TextView tv = (TextView) view.findViewById(R.id.checking_word);
            tv.setText("Animation: " + i);
            TextView tv1 = (TextView) view.findViewById(R.id.checking_word_translate);
            tv1.setText("Анимация: " + i);
            tv1.setVisibility(View.VISIBLE);
            ViewGroup.LayoutParams lp = view.getLayoutParams();
            viewAnimator.addView(view, lp);
        }

        viewAnimator.setInAnimation(this, R.anim.translate_next);
        viewAnimator.setOutAnimation(this, R.anim.translate_prev);

        buttonNext = (Button) findViewById(R.id.remember);
        buttonPrev = (Button) findViewById(R.id.not_remember);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Resources contextResources = getResources();
                viewAnimator.getCurrentView().setBackground(contextResources.getDrawable(R.drawable.fon_true));
                //EditText et = (EditText) viewAnimator.getCurrentView().findViewById(R.id.checking_word_translate);

                //et.setVisibility(View.VISIBLE);
                viewAnimator.showNext();
            }
        });
        buttonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Resources contextResources = getResources();
                viewAnimator.getCurrentView().setBackground(contextResources.getDrawable(R.drawable.fon_false));
                //EditText et = (EditText) viewAnimator.getCurrentView().findViewById(R.id.checking_word_translate);
                //et.setVisibility(View.VISIBLE);
                viewAnimator.showNext();
            }
        });
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
