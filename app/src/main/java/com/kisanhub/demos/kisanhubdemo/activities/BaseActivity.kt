package com.kisanhub.demos.kisanhubdemo.activities

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.kisanhub.demos.kisanhubdemo.R
import com.kisanhub.demos.kisanhubdemo.util.KProgress

abstract class BaseActivity : AppCompatActivity() {

    private lateinit var progress: KProgress

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }

    private fun initialize() {
        progress = KProgress(this)
        progress.setCancelable(false)
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun showErrorDialog(error: String) {
        val dialogBuilder = AlertDialog.Builder(this, R.style.AppTheme_Alert_Dialog)
        dialogBuilder.setTitle(getString(R.string.app_name))
        dialogBuilder.setMessage(error)
        dialogBuilder.setPositiveButton("Ok") { dialog, _ -> dialog.dismiss() }

        val alertDialog = dialogBuilder.create()
        alertDialog.show()
    }

    fun showProgress() {
        progress.show()
    }

    fun hideProgress(){
        progress.dismiss()
    }

}