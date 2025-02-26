package com.example.myapplicationrv.fragmentsGamesList;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.myapplicationrv.R;
import com.example.myapplicationrv.Services.DataService;
import com.example.myapplicationrv.activities.MainActivityGameList;
import com.example.myapplicationrv.adapters.CustomeAdapter;
//import com.example.myapplicationrv.classes.myGameData;
import com.example.myapplicationrv.models.GameData;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GameListFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class GameListFragment extends Fragment {

    //private ArrayList<GameData> arr;
    private ArrayList<GameData> arr2;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private CustomeAdapter customeAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GameListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GameListFragment newInstance(String param1, String param2) {
        GameListFragment fragment = new GameListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public GameListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //fix copying MainActivity to this Fragment
        MainActivityGameList mainActivityGameList = (MainActivityGameList) getActivity();
        View view =  inflater.inflate(R.layout.fragment_game_list, container, false);
        mainActivityGameList.readData();
        recyclerView = view.findViewById(R.id.fragmentGameListRecycleView);
        layoutManager = new LinearLayoutManager(getActivity()); //if problems arise - look into "onAttach (Context context)"
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        EditText searchBox = mainActivityGameList.findViewById(R.id.fragmentGameListEditTextSearchBox);


        DataService dataService = new DataService();
        arr2 = dataService.getAllGames();
        //String searchBoxText = searchBox.getText();

        if(searchBox.getText() == null) {
            customeAdapter = new CustomeAdapter(arr2);
            recyclerView.setAdapter(customeAdapter);
            return view;
        }
        else {
        for(int i=0;i<arr2.size();i++)
        {
            if (arr2.get(i).getGameName().contains())

        }

            return view;
        }
    }

}