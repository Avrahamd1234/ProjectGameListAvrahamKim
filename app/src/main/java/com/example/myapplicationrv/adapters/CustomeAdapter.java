package com.example.myapplicationrv.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationrv.R;
import com.example.myapplicationrv.models.GameData;

import java.util.ArrayList;

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
        public myViewHolder ( View itemView){
            super(itemView);
           gameName = itemView.findViewById(R.id.cardViewTextViewGameName);
           imageViewItem = itemView.findViewById(R.id.cardViewImageViewGameImage);
           rating = itemView.findViewById(R.id.cardViewTextViewGameRating);
           price = itemView.findViewById(R.id.cardViewTextViewGamePrice);
           genre = itemView.findViewById(R.id.cardViewTextViewGameGenre);
           Button showMoreOrLessButton = itemView.findViewById(R.id.cardViewButtonShowMoreOrLess);
           VideoView videoview = itemView.findViewById(R.id.cardViewVideoViewGameTrailer);

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
//        holder.nameVersion.setText(arr.get(position).getVersion());
//        holder.imageViewItem.setImageResource(arr.get(position).getImage());
        holder.gameName.setText(arr.get(position).getGameName());
        holder.imageViewItem.setImageResource(arr.get(position).getImage());
        holder.rating.setText(arr.get(position).getRating());
        holder.price.setText(arr.get(position).getPrice());
        holder.genre.setText(arr.get(position).getGenre());
       // holder.videoView.setVideoURI(arr.get(position).getVideoURL());
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }


}
