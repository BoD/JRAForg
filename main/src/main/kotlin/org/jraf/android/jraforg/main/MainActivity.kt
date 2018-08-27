/*
 * This source is part of the
 *      _____  ___   ____
 *  __ / / _ \/ _ | / __/___  _______ _
 * / // / , _/ __ |/ _/_/ _ \/ __/ _ `/
 * \___/_/|_/_/ |_/_/ (_)___/_/  \_, /
 *                              /___/
 * repository.
 *
 * Copyright (C) 2016-2018 Benoit 'BoD' Lubek (BoD@JRAF.org)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.jraf.android.jraforg.main

import android.annotation.SuppressLint
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
    private val handler = Handler()

    @SuppressLint("SetTextI18n")
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

        val txtVersion: TextView = findViewById(R.id.txtVersion)
        txtVersion.text = "v" + packageManager.getPackageInfo(packageName, 0).versionName
    }

    @SuppressLint("SetTextI18n")
    private fun animateLogo() {
        val txtLogo: TextView = findViewById(R.id.txtLogo)
        val logoStr = getString(R.string.logo)
        for (i in 0 until logoStr.length) {
            handler.postDelayed({
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
