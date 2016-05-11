package fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.thienphuoc.tpkaraoke.R;

import java.util.ArrayList;

import data.Constants;
import data.CustomListviewAdapter;
import data.DataHolder;
import data.DatabaseHandler;
import model.MySong;

/**
 * Created by Thien Phuoc on 2/9/2016.
 */
public class FragmentDisplaySongFavorite extends Fragment {

    /*Database*/
    private DatabaseHandler databaseHandler;
    private ArrayList<MySong> ArirangSongList =new ArrayList<>();
    private ListView listView;
    private CustomListviewAdapter customListviewAdapter;


    public FragmentDisplaySongFavorite(){

    }

    public static FragmentDisplaySongFavorite newInstance(){
        return new FragmentDisplaySongFavorite();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display_songs_favorite,container,false);

        databaseHandler = new DatabaseHandler(getContext());
        databaseHandler.copyDB();
        if (DataHolder.getInstance().getNumSongType()==0)
            this.ArirangSongList = databaseHandler.getListFavorite(Constants.DB_TABLE_ARIRANG);
        else if (DataHolder.getInstance().getNumSongType()==1)
            this.ArirangSongList = databaseHandler.searchInAllTypeYoung(Constants.DB_TABLE_ARIRANG);
        else if (DataHolder.getInstance().getNumSongType()==2)
            this.ArirangSongList = databaseHandler.searchInAllTypeRomantic(Constants.DB_TABLE_ARIRANG);
        else if (DataHolder.getInstance().getNumSongType()==3)
            this.ArirangSongList = databaseHandler.searchInAllTypeRevolution(Constants.DB_TABLE_ARIRANG);
        else if (DataHolder.getInstance().getNumSongType()==4)
            this.ArirangSongList = databaseHandler.searchInAllTypeTrinh(Constants.DB_TABLE_ARIRANG);
        else if (DataHolder.getInstance().getNumSongType()==5)
            this.ArirangSongList = databaseHandler.searchInAllTypeChildren(Constants.DB_TABLE_ARIRANG);

        listView = (ListView) view.findViewById(R.id.lvFavorite);
        customListviewAdapter = new CustomListviewAdapter(getActivity(),R.layout.song_row,ArirangSongList,0);
        listView.setAdapter(customListviewAdapter);

        return view;
    }
}
