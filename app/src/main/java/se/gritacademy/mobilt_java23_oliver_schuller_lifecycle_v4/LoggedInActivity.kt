package se.gritacademy.mobilt_java23_oliver_schuller_lifecycle_v4

import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.log

class LoggedInActivity : AppCompatActivity() {
    
    val TAG = "oliver"

    // UI components
    private lateinit var firstNameField: EditText
    private lateinit var lastNameField: EditText
    private lateinit var emailField: EditText
    private lateinit var phoneField: EditText
    private lateinit var isMale: RadioButton
    private lateinit var isFemale: RadioButton
    private lateinit var saveBtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_logged_in)

        var sharedPref: SharedPreferences = this.getSharedPreferences("preference", MODE_PRIVATE)
        var editor: SharedPreferences.Editor = sharedPref.edit()

        Log.i(TAG, "onCreate: Create")

        firstNameField = findViewById(R.id.inputFirstName)
        lastNameField = findViewById(R.id.inputLastName)
        emailField = findViewById(R.id.inputEmail)
        phoneField = findViewById(R.id.inputPhoneNumber)
        isMale = findViewById(R.id.radioMale)
        isFemale = findViewById(R.id.radioFemale)
        saveBtn = findViewById(R.id.saveBtn)

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