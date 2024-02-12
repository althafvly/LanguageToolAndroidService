/*
 * Copyright (C) 2016 Jordi Mas i Hernàndez <jmas@softcatala.org>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation; either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 * Boston, MA 02111-1307, USA.
 */
package org.softcatala.corrector

import android.R
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceActivity.EXTRA_NO_HEADERS
import android.preference.PreferenceActivity.EXTRA_SHOW_FRAGMENT
import androidx.appcompat.app.AppCompatActivity


/**
 * Spell checker preference screen.
 */
open class SpellCheckerSettingsActivity : AppCompatActivity() {
    override fun getIntent(): Intent? {
        val modIntent = Intent(super.getIntent())
        Configuration.SettingsActivity = this
        modIntent.putExtra(EXTRA_SHOW_FRAGMENT, SpellCheckerSettingsFragment::class.java.name)
        modIntent.putExtra(EXTRA_NO_HEADERS, true)
        return modIntent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.content, SpellCheckerSettingsFragment())
                .commit()
        }
    }
}
