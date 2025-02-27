package com.example.myapplicationrv.activities;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplicationrv.R;
import com.example.myapplicationrv.classes.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivityGameList extends AppCompatActivity {
//This is the prepared recycle view main code, we transferred it to a fragment

//    private ArrayList<Data> arr;
//    private RecyclerView recyclerView;
//    private LinearLayoutManager layoutManager;
//    private CustomeAdapter customeAdapter;
    public ArrayList<Integer> userFavorites;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
//old recycleView Code
//        recyclerView = findViewById(R.id.gameListFragmentRvcon);
//        layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());

//        arr = new ArrayList<Data>();
//
//        for (int i =0 ; i < myData.nameArray.length ; i++ ){
//                arr.add ( new Data(
//                        myData.nameArray[i],
//                        myData.versionArray[i],
//                        myData.drawableArray[i],
//                        myData.id_[i]
//                ) );
        }
//    public ArrayList<Integer> readData(ArrayList<Integer> favorites){
//// Read from the database
//        FirebaseUser firebaseuser = FirebaseAuth.getInstance().getCurrentUser();
////    see if we need to check if a user is logged in
//        FirebaseDatabase database = FirebaseDatabase.getInstance("https://gameview-10362-default-rtdb.firebaseio.com/");
//        DatabaseReference myRef = database.getReference("users").child(firebaseuser.getUid().toString());
////    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
////        @Override
////        public void onDataChange(DataSnapshot dataSnapshot) {
//////            userFavorites = dataSnapshot.getValue(String.class);
////            for(DataSnapshot ds : dataSnapshot.getChildren()) {
////                Integer intSnap = ds.getValue(Integer.class);
////                userFavorites.add(intSnap);
////            }
////            //do what you want with the email
////
////        }
////
////        @Override
////        public void onCancelled(@NonNull DatabaseError error) {
////
////        }
////    });
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                if (dataSnapshot.exists()) {
//                    // Data exists, process it
//                    User value = dataSnapshot.getValue(User.class);
////              Toast.makeText(MainActivity.this, value.getEmail(), Toast.LENGTH_LONG).show();
//                    TextView readDataText = findViewById(R.id.gameListFragmentUserTextView);
//                    readDataText.setText("Hello," + value.getEmail());
//                    //DatabaseReference myRef = database.getReference("users").child(firebaseuser.getUid().toString());//the "path" for the database
//                    userFavorites = value.getFavorites();
//                } else {
//                    // Data does not exist at this location
//                    Log.d("Firebase", "No data found at this location.");
//
//                }
//            }
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });
//
////    }
////    else{
////        Toast.makeText(this, "empty user",Toast.LENGTH_LONG).show();
////    }
//        return userFavorites;
//    }

}

//        customeAdapter = new CustomeAdapter(arr);
//        recyclerView.setAdapter(customeAdapter);
//    }
