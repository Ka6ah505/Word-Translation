package s.mironov3.gmail.com.word_translation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListItemAdapter extends BaseAdapter {

    Context ctx;
    LayoutInflater lInflater;
    ArrayList<ListItemTheme> objects;

    public ListItemAdapter(Context ctx, ArrayList<ListItemTheme> objects) {
        this.ctx = ctx;
        this.lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.objects = objects;
    }
    // количество элементов списка(тем для обучения)
    @Override
    public int getCount() {
        return objects.size();
    }
    // элемент по позиции в списке
    @Override
    public Object getItem(int i) {
        return objects.get(i);
    }
    // элемент по id
    @Override
    public long getItemId(int i) {
        return i;
    }
    // тема по позиции
    ListItemTheme getTheme(int position) {
        return (ListItemTheme) getItem(position);
    }
    // объект списка
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        if( view == null ) {
            view = lInflater.inflate(R.layout.item, viewGroup, false);
        }
        ListItemTheme liTheme = getTheme(i);

        ((TextView) view.findViewById(R.id.Texttheme)).setText(liTheme.getName());
        ((TextView) view.findViewById(R.id.Textrating)).setText("rating: "+liTheme.getId());
        //((TextView) view.findViewById(R.id.Textrating)).setText("rating: "+liTheme.getRating());
        ((TextView) view.findViewById(R.id.TextcountWord)).setText("total word: "+liTheme.getTotalWord());
        //((ImageView) view.findViewById(R.id.ImageTheme)).setImageBitmap(liTheme.getImage());


        ((ImageView) view.findViewById(R.id.ImageTheme)).setImageDrawable(liTheme.getImage());
        return view;
    }

    public Context getCtx() {
        return ctx;
    }
}
