package com.example.myapplicationrv.activities;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplicationrv.R;
import com.example.myapplicationrv.classes.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivityLogin extends AppCompatActivity { //First main activity, for login, register
    //probably needs to set this as main activity somehow and then use intent to move to the other activity when needed:
    //Intent intent = new intent(this, other_activity_name.class);
    //StartActivity(intent);

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        mAuth = FirebaseAuth.getInstance();
    }


    public void register(View view, String emailString, String passwordString, String phoneString){
        Toast.makeText(MainActivityLogin.this, "inside register function", Toast.LENGTH_LONG).show();
    mAuth.createUserWithEmailAndPassword(emailString, passwordString)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // register success, update UI with the signed-in user's information
                        writeData(emailString,phoneString);
                        Toast.makeText(MainActivityLogin.this, "register successful",Toast.LENGTH_LONG).show();
                        Navigation.findNavController(view).navigate(R.id.action_registerFragment2_to_loginFragment);
                    } else {
                        // If register fails, display a message to the user.
                        Toast.makeText(MainActivityLogin.this,"register failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
}

    public void login (View view, String emailString, String passwordString){
        mAuth.signInWithEmailAndPassword(emailString, passwordString)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(MainActivityLogin.this, "login successful",Toast.LENGTH_LONG).show();
                            // We use intents to move to the other main activity from here
                            Intent intent = new Intent(MainActivityLogin.this, MainActivityGameList.class);
                            intent.putExtra("web_url", "userIDSomething");
                            startActivity(intent);


                            //Navigation.findNavController(view).navigate(R.id.action_fragmentOne_to_fragmentTwo);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivityLogin.this,"login failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });




    }
    public void writeData(String emailString, String phoneString){
// Write a message to the database
        Toast.makeText(MainActivityLogin.this, "inside writeData function",Toast.LENGTH_LONG).show();
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://gameview-10362-default-rtdb.firebaseio.com/");//pipe into database
        FirebaseUser firebaseuser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference myRef = database.getReference("users").child(firebaseuser.getUid().toString());//the "path" for the database
        ArrayList<Integer> favoritesList = new ArrayList<Integer>();
        favoritesList.add(1);
        favoritesList.add(1);
        favoritesList.add(1);
        favoritesList.add(1);
        favoritesList.add(1);
        favoritesList.add(1);
        favoritesList.add(1);
        favoritesList.add(1);
        favoritesList.add(1);
        favoritesList.add(0);
        favoritesList.add(0);
        favoritesList.add(0);
        favoritesList.add(0);
        favoritesList.add(0);
        favoritesList.add(0);
        User user = new User(emailString,phoneString,favoritesList);
        myRef.setValue(user);//the object we're trying to insert to DB
    }
    public void readData(){
// Read from the database
        FirebaseUser firebaseuser = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://gameview-10362-default-rtdb.firebaseio.com/");
        DatabaseReference myRef = database.getReference("users").child(firebaseuser.getUid().toString());
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                User value = dataSnapshot.getValue(User.class);
//           Toast.makeText(MainActivity.this, value.getEmail(), Toast.LENGTH_LONG).show();
                TextView readDataText = findViewById(R.id.gameListFragmentUserTextView);
                readDataText.setText("Hello," + value.getEmail());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    } //%might not be needed in this main activity
    public boolean testEmail(String emailAddress) {
        //String regexPattern = java.util.regex.Pattern.quote("(^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+)");
        //boolean test = java.util.regex.Pattern.compile(regexPattern).matcher(emailAddress).matches();
        //java.lang.Object.org.apache.commons.validator.routines.EmailValidator
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return emailAddress.matches(emailRegex);

// /               EmailValidator
//return test;
    }
}