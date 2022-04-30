package com.example.lifecyclesandbox.login

import android.app.Dialog
import android.os.Bundle
import com.example.lifecyclesandbox.R
import android.content.DialogInterface
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.lifecyclesandbox.movie_list.MovieListActivity

class Fragment2 : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
                .setMessage(getString(R.string.dialog_fragment))
                .setPositiveButton(getString(R.string.ok)) { dialog: DialogInterface?, which: Int ->
                    Log.d(TAG, Fragment2::class.java.canonicalName)
                    onContinueButtonClicked(this.view)
                }
                .create()
    }

    private fun onContinueButtonClicked(view: View?) {
        val intentActivity2 = Intent(this.context, MovieListActivity::class.java)
        Log.d("onContinueButtonClicked", Fragment2::class.java.canonicalName)
        startActivity(intentActivity2)
    }

    companion object {
        var TAG = "SuccessfulLoginDialog"
    }
}