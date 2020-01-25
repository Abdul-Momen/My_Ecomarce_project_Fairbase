package com.momen.myecomarcefairbase.Fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.momen.myecomarcefairbase.Main2Activity;
import com.momen.myecomarcefairbase.MainActivity;
import com.momen.myecomarcefairbase.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SingUpFragment extends Fragment {

    public SingUpFragment() {
        // Required empty public constructor
    }


    //firbase auth
    FirebaseAuth firebaseAuth;




    private TextView alrealHaveAnAcount;
    private FrameLayout parentFramelayout;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
    private EditText emailText, fullNameText, passwordText, confirmPasswordtext;
    private Button signUpbtn;
    private ImageButton closeBtn;
    private ProgressBar progressBar;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v=inflater.inflate(R.layout.fragment_sing_up, container, false);
       alrealHaveAnAcount=v.findViewById(R.id.alreadyHaveAcountsignInbtn);
       parentFramelayout=getActivity().findViewById(R.id.regester_FrameLayoutId);



        emailText = v.findViewById(R.id.signUpEmailId);
        fullNameText = v.findViewById(R.id.signUpNameId);
        passwordText = v.findViewById(R.id.signUpPasswordId);
        confirmPasswordtext = v.findViewById(R.id.signUpConfirmPassid);
        signUpbtn = v.findViewById(R.id.signUpBtnId);
        closeBtn = v.findViewById(R.id.signUpCloseBtnId);
        progressBar = v.findViewById(R.id.signUpprogressBarId);



        firebaseAuth=FirebaseAuth.getInstance();

       return v;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        alrealHaveAnAcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serFragment(new SingInFragment());

            }
        });


        emailText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // when use typing then onTextChanged is called
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        fullNameText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // when use typing then onTextChanged is called
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        passwordText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // when use typing then onTextChanged is called
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        confirmPasswordtext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // when use typing then onTextChanged is called
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        signUpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                  checkEmailAndPassword();

            }
        });

    }


    private void serFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_left,R.anim.slideout_from_right);

        fragmentTransaction.replace(parentFramelayout.getId(),fragment);
        fragmentTransaction.commit();

    }


    private void checkInputs() {

        if(!TextUtils.isEmpty(emailText.getText())){
            if(!TextUtils.isEmpty(fullNameText.getText())){
               if(!TextUtils.isEmpty(passwordText.getText())&& passwordText.length()>=8){
                   if(!TextUtils.isEmpty(confirmPasswordtext.getText())){
                       signUpbtn.setEnabled(false);
                       signUpbtn.setTextColor(Color.rgb( 255, 255, 255));
                   }else {
                       signUpbtn.setEnabled(false);
                       signUpbtn.setTextColor(Color.argb(50, 255, 255, 255));
                   }
               }else {
                   signUpbtn.setEnabled(false);
                   signUpbtn.setTextColor(Color.argb(50, 255, 255, 255));
               }
            }else {
                signUpbtn.setEnabled(false);
                signUpbtn.setTextColor(Color.argb(50, 255, 255, 255));
            }

        }else {
            signUpbtn.setEnabled(false);
            signUpbtn.setTextColor(Color.argb(50, 255, 255, 255));
        }
    }

    private void checkEmailAndPassword() {

        if(emailText.getText().toString().matches(emailPattern)){
            if(passwordText.getText().toString().equals(confirmPasswordtext.getText().toString())){

                progressBar.setVisibility(View.VISIBLE);

                signUpbtn.setEnabled(false);
                signUpbtn.setTextColor(Color.argb(50, 255, 255, 255));

                 firebaseAuth.createUserWithEmailAndPassword(emailText.getText().toString(),passwordText.getText().toString())
                         .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                             @Override
                             public void onComplete(@NonNull Task<AuthResult> task) {
                                 if(task.isSuccessful()){
                                     Intent i= new Intent(getActivity(), Main2Activity.class);
                                     startActivity(i);
                                     getActivity().finish();

                                 }else {
                                     progressBar.setVisibility(View.INVISIBLE);
                                     signUpbtn.setEnabled(false);
                                     signUpbtn.setTextColor(Color.rgb( 255, 255, 255));

                                     String error=task.getException().getMessage();
                                     Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                                 }
                             }
                         });
            }else {
                confirmPasswordtext.setError("password does't matched");

            }
        }else {

            emailText.setError("Invalid Email");

        }



    }




}
