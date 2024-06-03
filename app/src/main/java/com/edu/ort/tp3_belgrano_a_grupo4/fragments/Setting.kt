package com.edu.ort.tp3_belgrano_a_grupo4.fragments


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.edu.ort.tp3_belgrano_a_grupo4.R
import com.edu.ort.tp3_belgrano_a_grupo4.database.AppDatabase
import com.edu.ort.tp3_belgrano_a_grupo4.database.dao.NightModeSettingsDao
import com.edu.ort.tp3_belgrano_a_grupo4.database.entities.NightModeSettings
import kotlinx.coroutines.launch

class Setting : PreferenceFragmentCompat(){

    private val NIGHT_MODE_KEY_SWITCH = "night_mode"
    private lateinit var appDatabase: AppDatabase
    private lateinit var nightModeSettingsDao: NightModeSettingsDao

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.setting, rootKey)

        // Inicializa la  database y el DAO
        appDatabase = AppDatabase.getInstance(requireContext())
        nightModeSettingsDao = appDatabase.nightModeSettingsDao()

        // Carga la configuración del modo nocturno desde la base de datos
        loadNightModeSettings()
    }

    private fun loadNightModeSettings() {
        lifecycleScope.launch {
            try {
                val isNightModeEnabled = nightModeSettingsDao.getNightModeEnabled() ?: false
                val switchPreference = findPreference<SwitchPreference>(NIGHT_MODE_KEY_SWITCH)

                // Actualiza el switch según la info recuperada de la base de datos
                switchPreference?.isChecked = isNightModeEnabled

                // Establece el listener para el cambio en el switch
                switchPreference?.setOnPreferenceChangeListener { _, newValue ->
                    val newIsNightModeEnabled = newValue as Boolean
                    lifecycleScope.launch {
                        updateNightModeSettings(newIsNightModeEnabled)
                    }
                    true
                }

            } catch (e: Exception) {
                Log.e("SettingFragment", "Error al cargar el modo oscuro en la database", e)
            }
        }
    }

    private suspend fun updateNightModeSettings(isNightModeEnabled: Boolean) {
        try {
            // Inserta o actualiza el modo nocturno en la base de datos
            nightModeSettingsDao.insertNightModeSettings(NightModeSettings(isNightModeEnabled = isNightModeEnabled))

            //Dependiendo el valor que llegue por parametro activa el modo oscuro o no
            if (isNightModeEnabled) {
                enableDarkMode()
            } else {
                disableDarkMode()
            }
        } catch (e: Exception) {
            Log.e("SettingFragment", "Error al guardar el modo oscuro en la database", e)
        }
    }

    private fun enableDarkMode() {
        // Habilita el modo oscuro
        val activity = requireActivity() as AppCompatActivity
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        activity.delegate.applyDayNight()
    }

    private fun disableDarkMode() {
        // Deshabilita el modo oscuro
        val activity = requireActivity() as AppCompatActivity
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        activity.delegate.applyDayNight()

    }

}