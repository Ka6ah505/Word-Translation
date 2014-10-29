package s.mironov3.gmail.com.word_translation;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PairOWordTranslationAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<PairOfWordTranslation> coupe;

    public PairOWordTranslationAdapter(Context ctx, ArrayList<PairOfWordTranslation> coupe) {
        this.ctx = ctx;
        this.lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.coupe = coupe;
    }

    @Override
    public int getCount() {
        return coupe.size();
    }

    @Override
    public Object getItem(int i) {
        return coupe.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    PairOfWordTranslation getPair(int i) {
        return (PairOfWordTranslation) getItem(i);
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        if( view == null ) {
            view = lInflater.inflate(R.layout.word_traslate_list_item, viewGroup, false);
        }
        PairOfWordTranslation pairOfWordTranslation = getPair(i);
        Log.d("MYLOG", "\n word \t"+pairOfWordTranslation.getWord()+"\ntranslate\t"+pairOfWordTranslation.getTranslate());
        ((TextView) view.findViewById(R.id.word)).setText(pairOfWordTranslation.getWord());
        ((TextView) view.findViewById(R.id.translate)).setText(pairOfWordTranslation.getTranslate());
        return view;
    }

    public Context getCtx() {
        return ctx;
    }
}
