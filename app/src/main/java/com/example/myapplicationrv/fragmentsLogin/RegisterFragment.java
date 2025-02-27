package com.example.myapplicationrv.fragmentsLogin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplicationrv.R;
import com.example.myapplicationrv.activities.MainActivityLogin;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        Button button = view.findViewById(R.id.RegisterButtonFragmentRegister);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //here we grab the texts inside the text fields, then convert to string
                MainActivityLogin mainActivityLogin = (MainActivityLogin) getActivity();
                EditText emailResult = view.findViewById(R.id.EmailEditTextBoxRegisterFragment);
                EditText passwordResult = view.findViewById(R.id.PasswordEditTextRegisterFragment);
                EditText rePasswordResult = view.findViewById(R.id.ConfirmPasswordEditTextRegisterFragment);
                //EditText phoneResult = view.findViewById(R.id.PhoneNumberEditTextFragmentRegister);
                String emailString = emailResult.getText().toString();
                String passwordString = passwordResult.getText().toString();
                String repasswordString = rePasswordResult.getText().toString();
                //String phoneString = phoneResult.getText().toString();

                if(!emailString.isEmpty() && !passwordString.isEmpty() && !repasswordString.isEmpty()){// && !phoneString.isEmpty()){//fields should not be empty
                    if (mainActivityLogin.testEmail(emailString)){ //Email should match Firebase's standards
                        if (passwordString.length() >= 6 ){ //password should be at least 6 characters long to conform to FireBase rules
                           if(passwordString.equals(repasswordString)){//password should be equal to password confirmation
                                mainActivityLogin.register(view,emailString,passwordString);//,phoneString);
                              //success
                            }
                           else {
                            //passwords not equal failure
                            Toast.makeText(getContext(), "passwords are not equal",Toast.LENGTH_LONG).show();
                        }
                        }
                        else{
                            //password length failure
                            Toast.makeText(getContext(), "password is not long enough, at least 6 characters",Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        //Email failure
                        Toast.makeText(getContext(), "email address is not valid",Toast.LENGTH_LONG).show();
                    }
                }
                    else{
                        //failure
                        Toast.makeText(getContext(), "one field is empty",Toast.LENGTH_LONG).show();
                    }
            }
        });
        return view;
    }
}