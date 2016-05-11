package data;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.thienphuoc.tpkaraoke.R;
import com.thienphuoc.tpkaraoke.SongDetailActivity;

import java.util.ArrayList;

import model.MySong;

/**
 * Created by Thien Phuoc on 2/4/2016.
 */
public class CustomListviewAdapter extends ArrayAdapter<MySong> {

    private Activity context;
    private int resource;
    private int tableSong;
    private ArrayList<MySong> lstSong = new ArrayList<>();

    public CustomListviewAdapter(Activity context, int resource, ArrayList<MySong> lstSong,int tableSong) {
        super(context, resource, lstSong);

        this.context=context;
        this.resource= resource;
        this.lstSong=lstSong;
        this.tableSong=tableSong;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return lstSong.size();
    }

    @Override
    public MySong getItem(int position) {
        return lstSong.get(position);
    }

    @Override
    public int getPosition(MySong item) {
        return super.getPosition(item);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        ViewHolder holder = null;

        if(row == null || row.getTag()== null){

            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            row = layoutInflater.inflate(this.resource,null);

            holder =new ViewHolder();

            holder.tvNumberCode = (TextView) row.findViewById(R.id.tvID);
            holder.tvLyricSong = (TextView) row.findViewById(R.id.tvSongLyric);
            holder.tvNameSong = (TextView) row.findViewById(R.id.tvSongName);
            holder.tvVol = (TextView) row.findViewById(R.id.tvVol);

            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }

        holder.mySong = getItem(position);
        holder.tvNumberCode.setText(holder.mySong.getNumberCode());
        holder.tvNameSong.setText(holder.mySong.getSongName());
        holder.tvLyricSong.setText(holder.mySong.getSongLyric());
        holder.tvVol.setText(holder.mySong.getSongVol());

        final ViewHolder finalHolder = holder;
        row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, SongDetailActivity.class);

                    Bundle mBunble = new Bundle();
                    mBunble.putSerializable("mysong", finalHolder.mySong);
                    mBunble.putInt("tableSong", tableSong);

                    i.putExtras(mBunble);
                    context.startActivity(i);

                }
            });

        return row;
    }

    public class ViewHolder{
        MySong   mySong;
        TextView tvNumberCode;
        TextView tvNameSong;
        TextView tvLyricSong;
        TextView tvVol;
    }
}
