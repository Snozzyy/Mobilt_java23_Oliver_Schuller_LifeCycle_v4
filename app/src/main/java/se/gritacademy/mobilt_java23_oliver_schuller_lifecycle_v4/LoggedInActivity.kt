package se.gritacademy.mobilt_java23_oliver_schuller_lifecycle_v4

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
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

        Log.i(TAG, "onCreate: Create")

        firstNameField = findViewById(R.id.inputFirstName)
        lastNameField = findViewById(R.id.inputLastName)
        emailField = findViewById(R.id.inputEmail)
        phoneField = findViewById(R.id.inputPhoneNumber)
        isMale = findViewById(R.id.radioMale)
        isFemale = findViewById(R.id.radioFemale)
        saveBtn = findViewById(R.id.saveBtn)

        restoreState(savedInstanceState)

        saveBtn.setOnClickListener {

        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        if (firstNameField.text.toString() != "") outState.putString("firstName", firstNameField.text.toString())
        if (lastNameField.text.toString() != "") outState.putString("lastName", lastNameField.text.toString())
        if (emailField.text.toString() != "") outState.putString("email", emailField.text.toString())
        if (phoneField.text.toString() != "") outState.putString("phone", phoneField.text.toString())

        if (isMale.isChecked) {
            outState.putBoolean("isMale", true)
            outState.putBoolean("isFemale", false)
        } else if (isFemale.isChecked) {
            outState.putBoolean("isMale", false)
            outState.putBoolean("isFemale", true)
        }

        Log.i(TAG, "onSaveInstanceState: information saved")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        restoreState(savedInstanceState)
        super.onRestoreInstanceState(savedInstanceState)
        Log.i(TAG, "onRestoreInstanceState: " + savedInstanceState.getString("firstName"))
    }

    private fun restoreState(savedInstanceState: Bundle?) {
        savedInstanceState?.let {
            firstNameField.setText(it.getString("firstName"))
            lastNameField.setText(it.getString("lastName"))
            emailField.setText(it.getString("email"))
            phoneField.setText(it.getString("phone"))
            isMale.isChecked = it.getBoolean("isMale")
            isFemale.isChecked = it.getBoolean("isFemale")
        }

        Log.i(TAG, "restoreState: " + if (savedInstanceState?.getString("firstName")
            != null) savedInstanceState.getString("firstName") else ("null"))
    }
}