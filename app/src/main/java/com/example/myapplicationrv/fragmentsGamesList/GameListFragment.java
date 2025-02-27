package com.example.myapplicationrv.fragmentsGamesList;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplicationrv.R;
import com.example.myapplicationrv.Services.DataService;
import com.example.myapplicationrv.activities.MainActivityGameList;
import com.example.myapplicationrv.adapters.CustomeAdapter;
//import com.example.myapplicationrv.classes.myGameData;
import com.example.myapplicationrv.classes.User;
import com.example.myapplicationrv.models.GameData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GameListFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class GameListFragment extends Fragment {

    //private ArrayList<GameData> arr;
    private ArrayList<Integer> userFavorites;
    private ArrayList<GameData> arr2;
    //private ArrayList<GameData> arrSearch;
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
        MainActivityGameList mainActivityGameList = (MainActivityGameList) getActivity();
        View view =  inflater.inflate(R.layout.fragment_game_list, container, false);
        userFavorites = new ArrayList<>();
//        mainActivityGameList.readData(userFavorites);
        recyclerView = view.findViewById(R.id.fragmentGameListRecycleView);
        layoutManager = new LinearLayoutManager(getActivity()); //if problems arise - look into "onAttach (Context context)"
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        DataService dataService = new DataService();
        arr2 = dataService.getAllGames(userFavorites); //add more threads to the function
//        customeAdapter = new CustomeAdapter(arr2,userFavorites);
//        recyclerView.setAdapter(customeAdapter);

        FirebaseUser firebaseuser = FirebaseAuth.getInstance().getCurrentUser();
        //see if we need to check if a user is logged in
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://gameview-10362-default-rtdb.firebaseio.com/");
        DatabaseReference myRef = database.getReference("users").child(firebaseuser.getUid().toString());
        myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        User value = dataSnapshot.getValue(User.class);
                        TextView readDataText = view.findViewById(R.id.gameListFragmentUserTextView);
                        readDataText.setText("Hello," + value.getEmail());
                        userFavorites = value.getFavorites();
                        customeAdapter = new CustomeAdapter(arr2,userFavorites);
                        recyclerView.setAdapter(customeAdapter);
//                      userFavorites = dataSnapshot.getValue(String.class);
//                      for(DataSnapshot ds : dataSnapshot.getChildren()) {
//                      Integer intSnap = ds.getValue(Integer.class);
//                      userFavorites.add(intSnap);
//                    }
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w(TAG, "Failed to read value.", error.toException());
                }
        });

//        ////    });
//                myRef.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        // This method is called once with the initial value and again
//                        // whenever data at this location is updated.
//                        if (dataSnapshot.exists()) {
//                            // Data exists, process it
//        //              Toast.makeText(MainActivity.this, value.getEmail(), Toast.LENGTH_LONG).show();
//
//                            //DatabaseReference myRef = database.getReference("users").child(firebaseuser.getUid().toString());//the "path" for the database
//                        } else {
//                            // Data does not exist at this location
//                            Log.d("Firebase", "No data found at this location.");
//
//                        }
//                    }
//                    @Override
//                    public void onCancelled(DatabaseError error) {
//                        // Failed to read value
//                        Log.w(TAG, "Failed to read value.", error.toException());
//                    }
//                });
//        //
//        ////    }
//        ////    else{
//        ////        Toast.makeText(this, "empty user",Toast.LENGTH_LONG).show();
//        ////    }
//        //        return userFavorites;
//        //
//        //    }


        //if(searchBoxText.isEmpty()) {
            ImageButton button = view.findViewById(R.id.fragmentGameListSearchButton);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                 filterGamesOnClick(v);
                }
            });

            return view;

        //}
//        else {
//
//            return view;
//        }
        //for(int i=0;i<arr2.size();i++)

            //if (arr2.get(i).getGameName().contains())

        }

    public void filterGamesOnClick(View view) {
        MainActivityGameList mainActivityGameList = (MainActivityGameList) getActivity();
        EditText searchBox = mainActivityGameList.findViewById(R.id.fragmentGameListEditTextSearchBox);
        String searchBoxText = searchBox.getText().toString();
        ArrayList<GameData> filteredList = new ArrayList<>();
        for(GameData gameData : arr2)
            if(gameData.getGameName().toLowerCase().contains(searchBoxText.toLowerCase()))
            filteredList.add(gameData);
        customeAdapter = new CustomeAdapter(filteredList,userFavorites);
        recyclerView.setAdapter(customeAdapter);
    }


}
//    public void funcOnClick(View view) {
//        Button button = (Button) view;
//        String searchBoxText = String.valueOf(searchBox.getText());
//        ;//.charAt(0);
//        result.append(buttonCharacter);

