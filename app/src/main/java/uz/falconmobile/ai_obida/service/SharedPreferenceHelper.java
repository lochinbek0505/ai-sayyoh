package uz.falconmobile.ai_obida.service;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceHelper {

    private static final String PREF_NAME = "my_shared_preferences";
    private static final String KEY_DATA = "data_key";

    // Save data to SharedPreferences
    public static void saveData(Context context, String data) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_DATA, data);  // Save the data with a key
        editor.apply();  // Commit changes
    }

    // Read data from SharedPreferences
    public static String getData(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_DATA, "default_value");  // Return a default value if data is not found
    }
}
