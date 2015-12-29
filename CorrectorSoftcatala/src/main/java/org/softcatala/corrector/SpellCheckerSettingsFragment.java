/*
 * Copyright (C) 2015 Jordi Mas i Hernàndez <jmas@softcatala.org>
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

package org.softcatala.corrector;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.util.Log;
import java.util.Date;


/**
 * Preference screen.
 */

public class SpellCheckerSettingsFragment extends PreferenceFragment {

    private static final String TAG = SpellCheckerSettingsFragment.class
            .getSimpleName();

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate");

        addPreferencesFromResource(R.xml.spell_checker_settings);

        setHttpConnections();
        setVersion();
        //setDialect();
    }

    private void setDialect() {
        /*Boolean dialect = Configuration.getInstance().getDialect();
        CheckBoxPreference cb = (CheckBoxPreference) findPreference("dialect");
        cb.setChecked(dialect);

        cb.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                Boolean dialect = Configuration.getInstance().getDialect();
                Configuration.getInstance().setDialect(!dialect);
                Log.d(TAG, "Pref " + preference.getKey() + " changed to " + newValue.toString());
                return true;
            }
        });*/
    }

    private void setVersion() {
        Date buildDate = BuildConfig.buildTime;
        Preference version = findPreference("version");
        String v = String.format("%s (built on %s)", getVersion(), buildDate);
        version.setSummary(v);
    }

    private String getVersion() {
        try {
            String versionName =
                    getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0).versionName;
            return versionName;
        } catch (Exception e) {
            return null;
        }
    }

    private void setHttpConnections() {
        Preference http = findPreference("http");
        Date lastConnection = Configuration.getInstance().getLastConnection();
        String status = String.format("%d (darrera %s)", Configuration.getInstance().getHttpConnections(),
                lastConnection == null ? "cap" : lastConnection.toString());

        http.setSummary(status);
    }
}