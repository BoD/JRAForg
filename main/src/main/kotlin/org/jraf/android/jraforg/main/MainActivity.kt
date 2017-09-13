package org.jraf.android.jraforg.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.support.annotation.StringRes
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private val mHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        if (savedInstanceState == null) {
            animateLogo()
        } else {
            val txtLogo: TextView = findViewById(R.id.txtLogo)
            txtLogo.text = getText(R.string.logo)
        }

        val txtEmail: TextView = findViewById(R.id.txtEmail)
        txtEmail.movementMethod = LinkMovementMethod.getInstance()
        txtEmail.text = Html.fromHtml(getString(R.string.email))
    }

    private fun animateLogo() {
        val txtLogo: TextView = findViewById(R.id.txtLogo)
        val logoStr: String = getString(R.string.logo)
        for (i in 0 until logoStr.length) {
            mHandler.postDelayed({
                val firstPart = logoStr.subSequence(0..i).toString()
                val secondPart = logoStr.subSequence(i + 1 until logoStr.length).replace(Regex("[^\\n]"), " ")
                val cursor = if (i == logoStr.length - 1) "" else "#"
                txtLogo.text = firstPart + cursor + secondPart
            }, 30L * i)
        }
    }

    fun goTo(view: View) {
        when (view.id) {
            R.id.github -> openLink(R.string.link_github)
            R.id.playStore -> openLink(R.string.link_playStore)
            R.id.resume -> openLink(R.string.link_resume)
            R.id.stackOverflow -> openLink(R.string.link_stackOverflow)
            R.id.gplus -> openLink(R.string.link_gplus)
            R.id.twitter -> openLink(R.string.link_twitter)
        }
    }

    private fun openLink(@StringRes linkResId: Int) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(getString(linkResId))).addCategory(Intent.CATEGORY_BROWSABLE))
    }
}
