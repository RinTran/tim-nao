package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.thienphuoc.tpkaraoke.R;

import java.util.ArrayList;

import data.CustomListviewAdapter;
import data.DatabaseHandler;
import model.MySong;

/**
 * Created by Thien Phuoc on 2/4/2016.
 */
public class FragmentDisplaySongs extends Fragment {

    /*Database*/
    private DatabaseHandler databaseHandler;
    private ArrayList<MySong> ArirangSongList =new ArrayList<>();
    private ListView listView;
    private CustomListviewAdapter customListviewAdapter;


    public FragmentDisplaySongs(){

    }

    public static FragmentDisplaySongs newInstance(){
        return new FragmentDisplaySongs();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display_songs,container,false);

        databaseHandler = new DatabaseHandler(getContext());
        databaseHandler.copyDB();

        this.ArirangSongList = databaseHandler.getArirangSongList();
        listView = (ListView) view.findViewById(R.id.lvDisplaySongs);
        customListviewAdapter = new CustomListviewAdapter(getActivity(),R.layout.song_row,ArirangSongList,0);
        listView.setAdapter(customListviewAdapter);

        return view;
    }
}
