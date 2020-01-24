package com.momen.myecomarcefairbase.Fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.momen.myecomarcefairbase.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SingInFragment extends Fragment {


    public SingInFragment() {
        // Required empty public constructor
    }


    private TextView dontHaveAnAcount;
    private FrameLayout parentFramelayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v= inflater.inflate(R.layout.fragment_sing_in, container, false);

       dontHaveAnAcount=v.findViewById(R.id.dontalreadyHaveAcountsignInbtn);
       parentFramelayout=getActivity().findViewById(R.id.regester_FrameLayoutId);

       return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dontHaveAnAcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new SingUpFragment());
            }
        });
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();

        fragmentTransaction.setCustomAnimations(R.anim.slide_from_right,R.anim.slideout_from_left);
        fragmentTransaction.replace(parentFramelayout.getId(),fragment);
        fragmentTransaction.commit();


    }
}
