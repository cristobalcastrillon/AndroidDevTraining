package com.example.lifecyclesandbox;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class Fragment2 extends DialogFragment {

    public static String TAG = "SuccessfulLoginDialog";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(requireContext())
                .setMessage(getString(R.string.dialog_fragment))
                .setPositiveButton(getString(R.string.ok), (dialog, which) -> {
                    Log.d(TAG, Fragment2.class.getCanonicalName());
                } )
                .create();
    }
}
