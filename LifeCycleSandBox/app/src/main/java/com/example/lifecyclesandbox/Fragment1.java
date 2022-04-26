package com.example.lifecyclesandbox;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class Fragment1 extends Fragment implements View.OnClickListener {

    View view;

    public Fragment1() {
        super(R.layout.fragment_1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_1, container, false);
        Button loginButton = (Button) view.findViewById(R.id.login_button);
        loginButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        // Display Fragment2
        new Fragment2().show(getChildFragmentManager(), Fragment2.TAG);
    }
}
