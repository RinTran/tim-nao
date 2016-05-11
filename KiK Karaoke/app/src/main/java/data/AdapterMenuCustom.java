package data;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.thienphuoc.tpkaraoke.R;

import java.util.List;

/**
 * Created by Thien Phuoc on 11/17/2015.
 */
public class AdapterMenuCustom extends ArrayAdapter<MenuCustom> {

    Context context;
    int rsId;
    List<MenuCustom> objects;

    public AdapterMenuCustom(Context context,  int rsId, List<MenuCustom> objects) {
        super(context, rsId, objects);

        if(context!=null){
            this.context = context;
            this.rsId = rsId;
            this.objects = objects;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context,rsId,null);
        ImageView img= (ImageView) view.findViewById(R.id.imageViewmenu);
        TextView txv = (TextView) view.findViewById(R.id.textViewmenu);

        MenuCustom item = objects.get(position);
        img.setImageResource(item.getImage());
        txv.setText(item.getMenu());
        return view;
    }
}
