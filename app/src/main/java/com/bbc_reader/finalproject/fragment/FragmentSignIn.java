package com.bbc_reader.finalproject.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bbc_reader.finalproject.AuthActivity;
import com.bbc_reader.finalproject.R;


@SuppressLint("ValidFragment")
public class FragmentSignIn extends Fragment {

    private static final Integer RES_ID = R.layout.fragment_sign_in;
    private AuthActivity context;
    private View view;

    private EditText etUsername, etPassword;
    private Button btnLogin, btnRegister;

    @SuppressLint("ValidFragment")
    public FragmentSignIn(AuthActivity mContext){
        this.context = mContext;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(RES_ID, container, false);
        //init view
        this.init();
        this.actionView();
        return this.view;
    }

    public void init(){
        this.etUsername = this.view.findViewById(R.id.et_cmt);
        this.etPassword = this.view.findViewById(R.id.et_password);
        this.btnLogin = this.view.findViewById(R.id.btn_login);
        this.btnRegister = this.view.findViewById(R.id.btn_register);
    }

    public void actionView(){
        this.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                if(username.length() == 0 || password.length() == 0){
                    Toast.makeText(context, "Username and password not empty", Toast.LENGTH_SHORT).show();
                }else{
                    context.finish();
                }
            }
        });

        this.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.setFragmentSignUp();
            }
        });
    }
}
