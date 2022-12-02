package com.bbc_reader.finalproject;

import android.app.FragmentTransaction;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bbc_reader.finalproject.fragment.FragmentSignIn;
import com.bbc_reader.finalproject.fragment.FragmentSignUp;

public class AuthActivity extends AppCompatActivity {


    private FragmentSignIn fragmentSignIn;
    private FragmentSignUp fragmentSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        setFragmentSignIn();
    }


    public void setFragmentSignIn(){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if(fragmentSignIn == null){
            fragmentSignIn = new FragmentSignIn(AuthActivity.this);
        }
        transaction.replace(R.id.layout_content, fragmentSignIn);
        transaction.commitAllowingStateLoss();
    }

    public void setFragmentSignUp(){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if(fragmentSignUp == null){
            fragmentSignUp = new FragmentSignUp(AuthActivity.this);
        }
        transaction.replace(R.id.layout_content, fragmentSignUp);
        transaction.commitAllowingStateLoss();
    }
}