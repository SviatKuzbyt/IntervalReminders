package ua.sviatkuzbyt.intervalreminders.ui.elements

import android.content.Context
import android.widget.Toast

fun makeToast(context: Context?, text: Int){
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}