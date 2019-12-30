package com.example.quizzit

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.quizzit.activities.LoadingActivity
import com.example.quizzit.screens.dialog.NoInternetFragment

private const val ID: Int = 0

class StartupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startUp()
    }

    private fun startUp() {
        startActivityForResult(Intent(this, LoadingActivity::class.java), ID)
    }

    private fun startApp() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            startApp()
        }
        else {
            NoInternetFragment
                .newInstance()
                .show(supportFragmentManager, "dialog")
        }
        }
    }
