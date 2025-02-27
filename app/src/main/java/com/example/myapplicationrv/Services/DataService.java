package com.example.myapplicationrv.Services;

import android.net.Uri;
import android.os.StrictMode;

import com.example.myapplicationrv.models.GameData;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

public class DataService {
    private ArrayList <GameData> arrGames;
    private String genreString;
    private String metaCriticScore;
    private Uri movieUri;
    private ArrayList<String> idStrings;
    public DataService(){
        this.arrGames = new ArrayList<>();
    }

    public ArrayList<GameData> getAllGames(ArrayList<Integer> userFavorites){ //need to call this function at application start to ease loading time
        Integer [] idNumbers= {291690, 291710, 291750 ,291770, 291840, 291860, 291910, 291930, 291960, 292000, 292030, 292060, 292090, 292120, 292140, 292160, 292180, 292200, 292240, 292260, 292280, 292300, 292330, 292370, 292380, 292390, 292400, 292410, 292420, 292480, 292500, 292570, 292600, 292620, 292630, 292660, 292670, 292730, 292760, 292780, 292800, 292820, 292840, 292860, 292880, 292910, 292930, 292990, 293160, 293180};
        for (int k = 0; k<15;k++) {
            URL url;
            //Uri movieUri;
            //String genreString;
            String movieString;
            String gameId = idNumbers[k].toString();
            String sUrl = "https://store.steampowered.com/api/appdetails?appids=" + gameId;
            //String appListUrl = "https://api.steampowered.com/IStoreService/GetAppList/v1/?key=A2B69151A591F9F6DCB012600548D23A&include_games=true";
            //asking the system to create a single thread, if we need more than one we need threadUI or something else
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            try {
                url = new URL(sUrl);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            try {
                HttpURLConnection request = (HttpURLConnection) url.openConnection();
                request.connect();
                JsonParser jp = new JsonParser();
                JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
                JsonObject obj = root.getAsJsonObject();
                JsonObject dataE = obj.getAsJsonObject(gameId);
                dataE = dataE.getAsJsonObject("data");
                String nameE = dataE.get("name").toString().replace("\"", "").trim();
                String shortDescriptionE = dataE.get("short_description").toString().replace("\"", "").trim();
                int price;
                try {
    JsonObject priceOverviewObj = dataE.getAsJsonObject("price_overview");
    JsonElement priceE = priceOverviewObj.get("final");
    price = priceE.getAsInt();
}
catch(Exception e) {
price = 0;
}
                //see if we need to put an "if" here
                try {
                    JsonObject metacriticScoreObj = dataE.getAsJsonObject("metacritic");
                    JsonElement metaCriticScoreE = metacriticScoreObj.get("score");
                    metaCriticScore = metaCriticScoreE.getAsString().replace("\"", "").trim();;

                }
                catch(Exception e){ //funneh
                    metaCriticScore = "69";
                }

                JsonElement headerImageUrl = dataE.get("header_image");
                String imageUrlString = headerImageUrl.toString();
                String formattedImageUrl = java.net.URLEncoder.encode(imageUrlString, "UTF-8");
                Uri imageUri = Uri.parse(imageUrlString);
                JsonArray genreArr = dataE.getAsJsonArray("genres");
                int i = 0;
                ;
                for (JsonElement je : genreArr) {//didn't find a way to get only the first element so we're iterating, the genre lists are short
                    if (i == 0) { //currently we're only taking the first genre
                        JsonObject genreObj = je.getAsJsonObject();
                        JsonElement genreE = genreObj.get("description");
                        genreString = genreE.getAsString().replace("\"", "").trim();
                    }
                    i++;
                }
try {
    JsonArray movieArr = dataE.getAsJsonArray("movies");
    i = 0;
    for (JsonElement je : movieArr) {//didn't find a way to get only the first element so we're iterating, the genre lists are short
        if (i == 0) { //currently we're only taking the first genre
            JsonObject movieObj = je.getAsJsonObject();
            JsonElement movieE = movieObj.get("webm");
            JsonObject webmObj = movieE.getAsJsonObject();
            JsonElement webmE = webmObj.get("480");
            movieString = webmE.getAsString();
            String formattedMovieString = java.net.URLEncoder.encode(movieString, "UTF-8");
            movieUri = Uri.parse(formattedMovieString);
        }
        i++;
    }
}
catch(Exception e){
    movieUri = Uri.parse("");
}
                double priceDouble = (double) price /100;
                int metaCriticScoreInt = Integer.parseInt(metaCriticScore);
//                for(GameData gameData : arr2)
//            if(gameData.getGameName().toLowerCase().contains(searchBoxText.toLowerCase()))
//            filteredList.add(gameData);
                boolean isFav = false;
//                for(Integer intFav : userFavorites)
//                    if(intFav.equals(idNumbers[k])) {
//                        isFav = true;
//                        break;
//                    }
                arrGames.add(new GameData(nameE, metaCriticScoreInt, k, priceDouble, genreString, shortDescriptionE, movieUri,idNumbers[k],isFav));



            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    return arrGames;
    }
}
