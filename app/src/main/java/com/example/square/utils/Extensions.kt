package com.example.square.utils


import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.square.R

inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> T
) = lazy(LazyThreadSafetyMode.SYNCHRONIZED) { bindingInflater.invoke(layoutInflater) }

fun AppCompatActivity.showAlertDialog(message: String?) {
    val errorMessage = if (message.isNullOrEmpty().not()) message
    else getString(R.string.unexpected)

    AlertDialog.Builder(this)
        .setTitle(R.string.error)
        .setMessage(errorMessage)
        .setPositiveButton(R.string.ok) { dialog, _ -> dialog.dismiss() }
        .show()
}