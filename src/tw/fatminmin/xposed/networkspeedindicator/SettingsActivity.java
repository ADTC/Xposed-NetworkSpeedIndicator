package tw.fatminmin.xposed.networkspeedindicator;

import android.os.Bundle;

import android.preference.PreferenceActivity;
import android.util.Log;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;

import java.util.HashSet;

import tw.fatminmin.xposed.networkspeedindicator.R;

public class SettingsActivity extends PreferenceActivity implements OnSharedPreferenceChangeListener {

	private static final String TAG = SettingsActivity.class.getSimpleName();
	private SharedPreferences mPrefs;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getPreferenceManager().setSharedPreferencesMode(Context.MODE_WORLD_READABLE);
		addPreferencesFromResource(R.xml.settings);
		mPrefs = getPreferenceScreen().getSharedPreferences();
		
	
	}

	@Override
	public void onResume() {
		super.onResume();

		mPrefs.registerOnSharedPreferenceChangeListener(this);
	}

	@Override
	public void onPause() {
		mPrefs.unregisterOnSharedPreferenceChangeListener(this);

		super.onPause();
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
		Intent intent = new Intent();
		Log.i(TAG, "onSharedPreferenceChanged "+key);
		
		
		if(key.equals(Common.KEY_SHOW_UPLOAD_SPEED)) {
		    
		    intent.setAction(Common.ACTION_SETTINGS_CHANGED);
		    intent.putExtra(Common.KEY_SHOW_UPLOAD_SPEED, 
		            prefs.getBoolean(Common.KEY_SHOW_UPLOAD_SPEED, Common.DEF_SHOW_UPLOAD_SPEED));
		    
		}
		else if(key.equals(Common.KEY_SHOW_DOWNLOAD_SPEED)) {
		    
		    intent.setAction(Common.ACTION_SETTINGS_CHANGED);
		    intent.putExtra(Common.KEY_SHOW_DOWNLOAD_SPEED, 
                    prefs.getBoolean(Common.KEY_SHOW_DOWNLOAD_SPEED, Common.DEF_SHOW_DOWNLOAD_SPEED));
		    
		}
		else if(key.equals(Common.KEY_SHOW_DOWNLOAD_SPEED)) {
		    
		    intent.setAction(Common.ACTION_SETTINGS_CHANGED);
		}
		else if (key.equals(Common.KEY_FORCE_UNIT)) {
		    
			intent.setAction(Common.ACTION_SETTINGS_CHANGED);
			intent.putExtra(Common.KEY_FORCE_UNIT,
					Common.getPrefInt(prefs, Common.KEY_FORCE_UNIT, Common.DEF_FORCE_UNIT));
			
		}
		else if (key.equals(Common.KEY_HIDE_UNIT)) {
		    
			intent.setAction(Common.ACTION_SETTINGS_CHANGED);
			intent.putExtra(Common.KEY_HIDE_UNIT, prefs.getBoolean(Common.KEY_HIDE_UNIT, Common.DEF_HIDE_UNIT));
			
		}
		else if (key.equals(Common.KEY_HIDE_INACTIVE)) {
			
		    intent.setAction(Common.ACTION_SETTINGS_CHANGED);
			intent.putExtra(Common.KEY_HIDE_INACTIVE,
					prefs.getBoolean(Common.KEY_HIDE_INACTIVE, Common.DEF_HIDE_INACTIVE));
			
		}
		else if (key.equals(Common.KEY_HIDE_NETWORK_TYPE)) {
			
		    intent.setAction(Common.ACTION_SETTINGS_CHANGED);
			HashSet<String> value = (HashSet<String>) prefs.getStringSet(Common.KEY_HIDE_NETWORK_TYPE,
					Common.DEF_HIDE_NETWORK_STATE);
			intent.putExtra(Common.KEY_HIDE_NETWORK_TYPE, value);
			
		}
		else if(key.equals(Common.KEY_FONT_SIZE)) {
		    
		    intent.setAction(Common.ACTION_SETTINGS_CHANGED);
		    intent.putExtra(Common.KEY_FONT_SIZE,
		            Common.getPrefInt(prefs, Common.KEY_FONT_SIZE, Common.DEF_FONT_SIZE));
		    
		}

		if (intent.getAction() != null) {
			sendBroadcast(intent);
			Log.i(TAG, "sendBroadcast");
		}
	}
	
	
	
}
