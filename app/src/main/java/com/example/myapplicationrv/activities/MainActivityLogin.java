package com.example.myapplicationrv.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

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
        //temp until FireBase is implemented
        Toast.makeText(MainActivityLogin.this,"register successful",Toast.LENGTH_LONG).show();
    Navigation.findNavController(view).navigate(R.id.action_registerFragment2_to_loginFragment);
}

    public void login (View view){
        String emailString = ((EditText) findViewById(R.id.emailEditTextBoxLoginFragment)).getText().toString();
        String passwordString = ((EditText) findViewById(R.id.passwordEditTextFragmentLogin)).getText().toString();
        //check intents to move to the other main activity
        //Navigation.findNavController(view).navigate(R.id.actionloginfragmentto);
        mAuth.createUserWithEmailAndPassword(emailString, passwordString)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // register success, update UI with the signed-in user's information
                            //writeData(emailString,phoneString);
                            Toast.makeText(MainActivityLogin.this, "register successful",Toast.LENGTH_LONG).show();
                            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_aboutFragment);
                        } else {
                            // If register fails, display a message to the user.
                            Toast.makeText(MainActivityLogin.this,"register failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}