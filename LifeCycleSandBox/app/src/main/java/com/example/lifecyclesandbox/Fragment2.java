package com.example.lifecyclesandbox;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.lifecyclesandbox.movie_list.MovieListActivity;

public class Fragment2 extends DialogFragment {

    public static String TAG = "SuccessfulLoginDialog";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(requireContext())
                .setMessage(getString(R.string.dialog_fragment))
                .setPositiveButton(getString(R.string.ok), (dialog, which) -> {
                    Log.d(TAG, Fragment2.class.getCanonicalName());
                    onContinueButtonClicked(this.getView());
                } )
                .create();
    }

    private void onContinueButtonClicked (View view) {
        Intent intentActivity2 = new Intent(this.getContext(), MovieListActivity.class);
        startActivity(intentActivity2);
    }
}
