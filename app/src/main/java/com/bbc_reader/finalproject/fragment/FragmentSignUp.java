package com.bbc_reader.finalproject.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.bbc_reader.finalproject.AuthActivity;
import com.bbc_reader.finalproject.R;

@SuppressLint("ValidFragment")
public class FragmentSignUp extends Fragment {

    private static final Integer RES_ID = R.layout.fragment_sign_up;
    private AuthActivity context;
    private View view;

    private EditText etUsername, etPassword, etRePassword, etFullName, etPhone, etMail;
    private Button btnSignIn, btnSignUp;

    @SuppressLint("ValidFragment")
    public FragmentSignUp(AuthActivity mContext){
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
        this.etRePassword = this.view.findViewById(R.id.et_re_password);
        this.etFullName = this.view.findViewById(R.id.et_full_name);
        this.etPhone = this.view.findViewById(R.id.et_phone);
        this.etMail = this.view.findViewById(R.id.et_mail);
        this.btnSignIn = this.view.findViewById(R.id.btn_login);
        this.btnSignUp = this.view.findViewById(R.id.btn_register);
    }

    public void actionView(){
        this.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.setFragmentSignIn();
            }
        });

        this.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String rePassword = etRePassword.getText().toString().trim();
                String fullName = etFullName.getText().toString().trim();
                String phone = etPhone.getText().toString().trim();
                String mail = etMail.getText().toString().trim();

            }
        });
    }
}
