package se.gritacademy.mobilt_java23_oliver_schuller_lifecycle_v4

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class SettingsActivity : AppCompatActivity() {

    // UI components
    private lateinit var firstNameField: EditText
    private lateinit var lastNameField: EditText
    private lateinit var emailField: EditText
    private lateinit var phoneField: EditText
    private lateinit var isMale: RadioButton
    private lateinit var isFemale: RadioButton
    private lateinit var saveBtn: Button
    private lateinit var bottomNav: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_settings)

        val sharedPref: SharedPreferences = this.getSharedPreferences("preference", MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPref.edit()

        firstNameField = findViewById(R.id.inputFirstName)
        lastNameField = findViewById(R.id.inputLastName)
        emailField = findViewById(R.id.inputEmail)
        phoneField = findViewById(R.id.inputPhoneNumber)
        isMale = findViewById(R.id.radioMale)
        isFemale = findViewById(R.id.radioFemale)
        saveBtn = findViewById(R.id.saveBtn)
        bottomNav = findViewById(R.id.bottomNav)

        setFields(sharedPref)

        saveBtn.setOnClickListener {
            editor.putString("firstName", firstNameField.text.toString()).apply()
            editor.putString("lastName", lastNameField.text.toString()).apply()
            editor.putString("email", emailField.text.toString()).apply()
            editor.putString("phone", phoneField.text.toString()).apply()
            editor.putBoolean("isMale", isMale.isChecked).apply()
            editor.putBoolean("isFemale", isFemale.isChecked).apply()

            Toast.makeText(this, "Data saved", Toast.LENGTH_LONG).show()
        }

        bottomNav.selectedItemId = R.id.settingsItem
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.homeItem -> {
                    val homeIntent = Intent(this, HomeActivity::class.java)
                    startActivity(homeIntent)
                    true
                }
                R.id.settingsItem -> {
                    true
                }
                R.id.logoutItem -> {
                    val logoutIntent = Intent(this, MainActivity::class.java)
                    startActivity(logoutIntent)
                    true
                }
                else -> false
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setFields(sharedPref: SharedPreferences) {
        firstNameField.setText(sharedPref.getString("firstName", ""))
        lastNameField.setText(sharedPref.getString("lastName", ""))
        emailField.setText(sharedPref.getString("email", ""))
        phoneField.setText(sharedPref.getString("phone", ""))
        isMale.isChecked = sharedPref.getBoolean("isMale", false)
        isFemale.isChecked = sharedPref.getBoolean("isFemale", false)
    }
}