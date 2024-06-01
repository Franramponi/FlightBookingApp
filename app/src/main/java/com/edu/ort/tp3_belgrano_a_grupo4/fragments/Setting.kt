package com.edu.ort.tp3_belgrano_a_grupo4.fragments

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import androidx.preference.SwitchPreference
import com.edu.ort.tp3_belgrano_a_grupo4.R
class Setting : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {
    private val NIGHT_MODE_KEY = "night_mode"

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.setting, rootKey)

        val switchNightMode = findPreference<SwitchPreference>(NIGHT_MODE_KEY)

        // Establece el escuchador de cambios para la preferencia
        switchNightMode?.onPreferenceChangeListener = object : Preference.OnPreferenceChangeListener {
            override fun onPreferenceChange(preference: Preference, newValue: Any?): Boolean {
                val isNightModeEnabled = newValue as? Boolean ?: false // Maneja valores null

                // Guarda el estado actual del modo nocturno
                saveNightModeState(isNightModeEnabled)

                if (isNightModeEnabled) {
                    enableDarkMode()
                } else {
                    disableDarkMode()
                }
                return true
            }
        }
    }
    private fun saveNightModeState(isNightModeEnabled: Boolean) {
        // Obtiene las SharedPreferences y guarda el estado del modo nocturno
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        sharedPreferences.edit().putBoolean(NIGHT_MODE_KEY, isNightModeEnabled).apply()
    }


    private fun enableDarkMode() {
        val activity = requireActivity() as AppCompatActivity
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        // Aplica el modo nocturno actual a la actividad
        activity.delegate.applyDayNight()
    }

    private fun disableDarkMode() {
        val activity = requireActivity() as AppCompatActivity

        // Establece el modo nocturno predeterminado como "no"
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        activity.delegate.applyDayNight()
    }

    override fun onResume() {
        super.onResume()

        // Registra el escuchador de cambios en las SharedPreferences
        preferenceScreen.sharedPreferences?.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()

        // Desregistra el escuchador de cambios en las SharedPreferences
        preferenceScreen.sharedPreferences?.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String?) {
        // Si la clave cambiada es "night_mode", actualiza el modo oscuro
        if (key == NIGHT_MODE_KEY) {
            val isNightModeEnabled = sharedPreferences.getBoolean(NIGHT_MODE_KEY, false)
            if (isNightModeEnabled) {
                enableDarkMode()
            } else {
                disableDarkMode()
            }
        }
    }
}