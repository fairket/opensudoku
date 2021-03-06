/* 
 * Copyright (C) 2009 Roman Masek
 * 
 * This file is part of OpenSudoku.
 * 
 * OpenSudoku is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * OpenSudoku is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with OpenSudoku.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package cz.romario.opensudoku.gui;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;

import com.fairket.app.opensudoku.R;
import com.fairket.sdk.android.FairketApiClient;
import com.fairket.sdk.android.FairketAppTimeHelper;

public class GameSettingsActivity extends PreferenceActivity {

	private FairketApiClient mFairket;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.xml.game_settings);

		findPreference("show_hints").setOnPreferenceChangeListener(
				mShowHintsChanged);
		// FairketApiClient Integration
		mFairket = FairketAppTimeHelper.onCreate(this, FolderListActivity.FAIRKET_APP_PUB_KEY, FolderListActivity.FAIRKET_LOG);
	}

	@Override
	protected void onResume() {
		super.onResume();

		FairketAppTimeHelper.onResume(mFairket);
	}

	@Override
	protected void onPause() {
		super.onPause();

		FairketAppTimeHelper.onPause(mFairket);
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		FairketAppTimeHelper.onDestroy(mFairket);

	}

	private OnPreferenceChangeListener mShowHintsChanged = new OnPreferenceChangeListener() {

		@Override
		public boolean onPreferenceChange(Preference preference, Object newValue) {
			boolean newVal = (Boolean) newValue;

			HintsQueue hm = new HintsQueue(GameSettingsActivity.this);
			if (newVal) {
				hm.resetOneTimeHints();
			}
			;
			return true;
		}

	};

}
