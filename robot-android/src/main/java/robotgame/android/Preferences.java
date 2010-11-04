package robotgame.android;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * @author tomekk
 * @since 2010-11-04, 23:21:47
 */
public class Preferences extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
