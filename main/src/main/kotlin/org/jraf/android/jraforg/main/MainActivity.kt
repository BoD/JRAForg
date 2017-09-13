package org.jraf.android.jraforg.main

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.text.method.LinkMovementMethod
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private val mHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        animateLogo()

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

}
