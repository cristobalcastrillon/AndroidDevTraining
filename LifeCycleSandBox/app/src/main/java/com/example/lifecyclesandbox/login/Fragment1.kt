package com.example.lifecyclesandbox.login

import com.example.lifecyclesandbox.R
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.lifecyclesandbox.login.Fragment2

class Fragment1 : Fragment(R.layout.fragment_1), View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_1, container, false)
        val loginButton = view.findViewById<View>(R.id.login_button) as Button
        loginButton.setOnClickListener(this)
        return view
    }

    override fun onClick(v: View) {
        // Display Fragment2
        Fragment2().show(childFragmentManager, Fragment2.TAG)
    }
}