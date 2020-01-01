package com.example.quizzit.screens.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import kotlin.system.exitProcess

class NoInternetFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder
                .setTitle("Geen verbinding mogelijk")
                .setMessage("Er kon geen verbinding worden gemaakt probeer het later opnieuw")
                .setPositiveButton("Sluiten") { _, _ ->
                    activity?.finishAffinity()
                    exitProcess(-1)
                }

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    companion object {
        fun newInstance(): NoInternetFragment {
            return NoInternetFragment()
        }
    }
}
