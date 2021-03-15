package com.bkapps.news.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.snackbar.Snackbar


fun <A : Activity> Activity.startNewActivity(activity: Class<A>) {
    Intent(this, activity).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }
}

fun Context.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}

fun Fragment.toast(msg: String) {
    requireActivity().toast(msg)
}

fun View.enable(enable: Boolean) {
    alpha = if (enable) 1f else 0.4f
    isClickable = enable
}

fun View.visible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun Fragment.showKeyboard(view: View, flag: Boolean) = requireContext().showKeyboard(view, flag)

fun Context.showKeyboard(view: View, flag: Boolean) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    if (flag)
        inputMethodManager.showSoftInput(view, 0)
    else
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun View.showSnackbar(snackbarText: String, timeLength: Int) {
    Snackbar.make(this, snackbarText, timeLength).run { show() }
}

fun TextView.createSubLinkableText(links: List<Pair<String, View.OnClickListener>>) {
    val spannableString = SpannableString(this.text.toString())
    for ((first, second) in links) {
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(view: View) {
                val myTextView = view as TextView
                Selection.setSelection(myTextView.text as Spannable, 0)
                view.invalidate()
                second.onClick(view)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.parseColor("#07BCD4")
            }
        }
        val startIndexOfLink = this.text.toString().indexOf(first)
        spannableString.setSpan(
            clickableSpan,
            startIndexOfLink,
            startIndexOfLink + first.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
    //without LinkMovementMethod, link can not click
    this.movementMethod = LinkMovementMethod.getInstance()
    this.setText(spannableString, TextView.BufferType.SPANNABLE)
}


fun ImageView.loadImage(url: String) {
    Glide.with(this)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .skipMemoryCache(true)
        .into(this)
}
