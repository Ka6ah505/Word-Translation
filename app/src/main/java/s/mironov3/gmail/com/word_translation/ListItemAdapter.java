package s.mironov3.gmail.com.word_translation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class ListItemAdapter extends BaseAdapter {

    Context ctx;
    LayoutInflater lInflater;
    ArrayList<ListItemTheme> objects;

    public ListItemAdapter(Context ctx, LayoutInflater lInflater, ArrayList<ListItemTheme> objects) {
        this.ctx = ctx;
        this.lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.objects = objects;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int i) {
        return objects.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
