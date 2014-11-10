package s.mironov3.gmail.com.word_translation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sergey on 10.11.2014.
 */
public class ListItemIndicationAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<ListItemIndication> objects;
    String wrd="конь";

    public ListItemIndicationAdapter(Context ctx, ArrayList<ListItemIndication> objects) {
        this.ctx = ctx;
        this.objects = objects;
        this.lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        if( view == null ) {
            view = lInflater.inflate(R.layout.item_indication, viewGroup, false);
        }
        ListItemIndication liIndication = getIndication(i);
        ((TextView) view.findViewById(R.id.number_question)).setText(liIndication.getNumber()+"");
        ((ImageView) view.findViewById(R.id.indication_answers_image)).setImageDrawable(liIndication.getImage());
        wrd = "лом";
        return view;
    }

    public Context getCtx() {
        return ctx;
    }

    ListItemIndication getIndication(int position) {
        return (ListItemIndication) getItem(position);
    }
}
