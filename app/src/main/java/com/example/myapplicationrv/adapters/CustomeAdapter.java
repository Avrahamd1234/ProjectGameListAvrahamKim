package com.example.myapplicationrv.adapters;

import static androidx.browser.customtabs.CustomTabsClient.getPackageName;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplicationrv.R;
import com.example.myapplicationrv.classes.myGameData;
import com.example.myapplicationrv.models.GameData;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CustomeAdapter extends RecyclerView.Adapter<CustomeAdapter.myViewHolder> {

    private ArrayList<GameData> arr;

    public CustomeAdapter(ArrayList<GameData> arr) {

        this.arr = arr;
    }
    public class myViewHolder extends RecyclerView.ViewHolder {

        TextView gameName;
        ImageView imageViewItem;
        TextView rating;
        TextView price;
        TextView genre;
        TextView description;
        Button showMoreOrLessButton;
        VideoView videoView;
        public myViewHolder (View itemView){
            super(itemView);
           gameName = itemView.findViewById(R.id.cardViewTextViewGameName);
           imageViewItem = itemView.findViewById(R.id.cardViewImageViewGameImage);
           rating = itemView.findViewById(R.id.cardViewTextViewGameRating);
           price = itemView.findViewById(R.id.cardViewTextViewGamePrice);
           genre = itemView.findViewById(R.id.cardViewTextViewGameGenre);
          // Button showMoreOrLessButton = itemView.findViewById(R.id.cardViewButtonShowMoreOrLess);
           //VideoView videoview = itemView.findViewById(R.id.cardViewVideoViewGameTrailer);
           description = itemView.findViewById(R.id.cardViewTextViewGameDescription);
        }

    }
    @NonNull
    @Override
    public CustomeAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview , parent , false ) ;

        myViewHolder MyViewHolder = new myViewHolder(view);

       return MyViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomeAdapter.myViewHolder holder, int position) {
        //holder.username.setText(arr.get(position).getName());
        //holder.nameVersion.setText(arr.get(position).getVersion());

//        int imageId = arr.get(position).getId();
//        String imageIdString = imageId+"";
//        String imagePath = "i"+imageIdString;
        //int id = getResources().getIdentifier(imagePath , "drawable", getPackageName());
        holder.imageViewItem.setImageResource(myGameData.drawableArray[arr.get(position).getImage()]);
        //ImageView myImageView = (ImageView)v.findViewById(R.id.img_play);
        //// supossing to have an image called ic_play inside my drawables.
        //myImageView.setImageResource(R.drawable.ic_play);
        holder.gameName.setText(arr.get(position).getGameName());
        //Glide.with(holder.itemView.getContext()).load(arr.get(position).getImage()).into(holder.imageViewItem);
        holder.rating.setText(String.format(Locale.getDefault(), "%d", arr.get(position).getRating()));
        holder.price.setText(String.format(Locale.getDefault(), "%f", arr.get(position).getPrice()));
        holder.genre.setText(arr.get(position).getGenre());
        holder.description.setText(arr.get(position).getDescription());
        //holder.videoView.setVideoURI(arr.get(position).getVideoURL());
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }


}
