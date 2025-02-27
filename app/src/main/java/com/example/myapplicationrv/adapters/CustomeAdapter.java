package com.example.myapplicationrv.adapters;

import static androidx.browser.customtabs.CustomTabsClient.getPackageName;
import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplicationrv.R;
import com.example.myapplicationrv.activities.MainActivityGameList;
import com.example.myapplicationrv.activities.MainActivityLogin;
import com.example.myapplicationrv.classes.User;
import com.example.myapplicationrv.classes.myGameData;
import com.example.myapplicationrv.models.GameData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CustomeAdapter extends RecyclerView.Adapter<CustomeAdapter.myViewHolder> {

    private ArrayList<GameData> arr;
    private ArrayList<Integer> arrFav;
    private Boolean isFav;
    public CustomeAdapter(ArrayList<GameData> arr, ArrayList<Integer> arrFav) {

        this.arr = arr;
        this.arrFav = arrFav;

    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        ImageButton favButton;
        //ImageButton trailerButton;
        TextView gameName;
        ImageView imageViewItem;
        TextView rating;
        TextView price;
        TextView genre;
        TextView description;
        TextView id;
       // Button showMoreOrLessButton;
       // VideoView videoView;

        public myViewHolder(View itemView) {
            super(itemView);
            gameName = itemView.findViewById(R.id.cardViewTextViewGameName);
            imageViewItem = itemView.findViewById(R.id.cardViewImageViewGameImage);
            rating = itemView.findViewById(R.id.cardViewTextViewGameRating);
            price = itemView.findViewById(R.id.cardViewTextViewGamePrice);
            genre = itemView.findViewById(R.id.cardViewTextViewGameGenre);
            favButton = itemView.findViewById(R.id.cardViewImageButtonFavButton);
            id = itemView.findViewById((R.id.cardViewTextViewSteamId));
//            trailerButton = itemView.findViewById((R.id.cardViewImageButtonTrailerIcon));
            // Button showMoreOrLessButton = itemView.findViewById(R.id.cardViewButtonShowMoreOrLess);
            //VideoView videoview = itemView.findViewById(R.id.cardViewVideoViewGameTrailer);
            description = itemView.findViewById(R.id.cardViewTextViewGameDescription);
        }

    }

    @NonNull
    @Override
    public CustomeAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);

        myViewHolder MyViewHolder = new myViewHolder(view);

        return MyViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomeAdapter.myViewHolder holder, int position) {
//        MainActivityGameList mainActivityGameList = MainActivityGameList
        //holder.username.setText(arr.get(position).getName());
        //holder.nameVersion.setText(arr.get(position).getVersion());

//        int imageId = arr.get(position).getId();
//        String imageIdString = imageId+"";
//        String imagePath = "i"+imageIdString;
        //int id = getResources().getIdentifier(imagePath , "drawable", getPackageName());
        holder.imageViewItem.setImageResource(myGameData.drawableArray[arr.get(position).getImage()]);
        holder.gameName.setText(arr.get(position).getGameName());
        //Glide.with(holder.itemView.getContext()).load(arr.get(position).getImage()).into(holder.imageViewItem);
        holder.rating.setText(String.format(Locale.getDefault(), "%d", arr.get(position).getRating()));
        holder.price.setText(String.format(Locale.getDefault(), "%f", arr.get(position).getPrice()));
        holder.genre.setText(arr.get(position).getGenre());
        holder.description.setText(arr.get(position).getDescription());
        holder.id.setText(arr.get(position).getId()+"");
//        int currentId = arr.get(position).getId();
        //trailer code: intent didn't work, cba'd.
//        holder.trailerButton.setOnClickListener(new View.OnClickListener() { attempt to open Uri
//            @Override
//            public void onClick(View v) {
//                Uri uri = (arr.get(position).getVideoURL());
//                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);
//                    startActivity(intent);
//                }
//
//            }
//        });

        holder.favButton.setImageResource(R.drawable.favempty);
        for (Integer favTest : arrFav) {
            if (arr.get(position).getId() == favTest) {
                holder.favButton.setImageResource(R.drawable.favfull);
            }
            holder.favButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isFav = false;
                    for (Integer favTestInButton : arrFav) {
                        if (Integer.parseInt((String) holder.id.getText()) == favTestInButton) {
                            isFav = true;
                            //remove the id from firebase
                            FirebaseDatabase database = FirebaseDatabase.getInstance("https://gameview-10362-default-rtdb.firebaseio.com/");//pipe into database
                            FirebaseUser firebaseuser = FirebaseAuth.getInstance().getCurrentUser();
                            DatabaseReference myRef = database.getReference("users").child(firebaseuser.getUid().toString());//the "path" for the database
                            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    User tempUser = snapshot.getValue(User.class);
                                    arrFav.remove(favTestInButton);
                                    tempUser.setFavorites(arrFav);
                                    String tempEmail = tempUser.getEmail();
                                    myRef.setValue(tempUser);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }

//                        User user = new User(emailString,phoneString,favoritesList);
//                        myRef.setValue(user);//the object we're trying to insert to DB

//FirebaseUser firebaseuser = FirebaseAuth.getInstance().getCurrentUser();
//        //see if we need to check if a user is logged in
//        FirebaseDatabase database = FirebaseDatabase.getInstance("https://gameview-10362-default-rtdb.firebaseio.com/");
//        DatabaseReference myRef = database.getReference("users").child(firebaseuser.getUid().toString());
//        myRef.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    if (dataSnapshot.exists()) {
//                        User value = dataSnapshot.getValue(User.class);
//                        TextView readDataText = view.findViewById(R.id.gameListFragmentUserTextView);
//                        readDataText.setText("Hello," + value.getEmail());
//                        userFavorites = value.getFavorites();
//                        customeAdapter = new CustomeAdapter(arr2,userFavorites);
//                        recyclerView.setAdapter(customeAdapter);
////                      userFavorites = dataSnapshot.getValue(String.class);
////                      for(DataSnapshot ds : dataSnapshot.getChildren()) {
////                      Integer intSnap = ds.getValue(Integer.class);
////                      userFavorites.add(intSnap);
////                    }
//                    }
//                }
//                @Override
//                public void onCancelled(DatabaseError error) {
//                    // Failed to read value
//                    Log.w(TAG, "Failed to read value.", error.toException());
//                }
//        });
                            //        favoritesList.add(291690);
                            //        favoritesList.add(1);
                            //        favoritesList.add(1);
                            //        favoritesList.add(1);
                            //        favoritesList.add(1);
                            //        favoritesList.add(1);
                            //        favoritesList.add(1);
                            //        favoritesList.add(1);
                            //        favoritesList.add(1);
                            //        favoritesList.add(0);
                            //        favoritesList.add(0);
                            //        favoritesList.add(0);
                            //        favoritesList.add(0);
                            //        favoritesList.add(0);
                            //        favoritesList.add(0);
                            //        User user = new User(emailString,phoneString,favoritesList);
                            //        myRef.setValue(user);//the object we're trying to insert to DB
                            //    }

                    }
                    if(isFav != true) {//we know that we can write !isFav but this is more readable.
                        FirebaseDatabase database = FirebaseDatabase.getInstance("https://gameview-10362-default-rtdb.firebaseio.com/");//pipe into database
                        FirebaseUser firebaseuser = FirebaseAuth.getInstance().getCurrentUser();
                        DatabaseReference myRef = database.getReference("users").child(firebaseuser.getUid().toString());//the "path" for the database
                        myRef.addListenerForSingleValueEvent(new ValueEventListener(){
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                Toast.makeText(v.getContext(), "inside false fav",Toast.LENGTH_LONG).show();
                                User tempUser = snapshot.getValue(User.class);
                                arrFav.add(arr.get(position).getId());
                                tempUser.setFavorites(arrFav);
                                myRef.setValue(tempUser);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }});
        }

//            if(gameData.getGameName().toLowerCase().contains(searchBoxText.toLowerCase()))
//                filteredList.add(gameData);
//
//        if(arr.get(position).getId())
//        holder.favButton.setImageResource();
            //holder.videoView.setVideoURI(arr.get(position).getVideoURL());
        }

        @Override
        public int getItemCount () {
            return arr.size();
        }

    }
////public void writeFavoriteData(Integer id){
////// Write a message to the database
////    //Toast.makeText(MainActivityLogin.this, "inside writeData function",Toast.LENGTH_LONG).show();
////    FirebaseDatabase database = FirebaseDatabase.getInstance("https://gameview-10362-default-rtdb.firebaseio.com/");//pipe into database
////    FirebaseUser firebaseuser = FirebaseAuth.getInstance().getCurrentUser();
////    DatabaseReference myRef = database.getReference("users").child(firebaseuser.getUid().toString());//the "path" for the database
////    myRef.
////    User user = new User(emailString,phoneString,favoritesList);
////    myRef.setValue(user);//the object we're trying to insert to DB
////}

