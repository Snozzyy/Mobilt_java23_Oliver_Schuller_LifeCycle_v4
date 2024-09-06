package se.gritacademy.mobilt_java23_oliver_schuller_lifecycle_v4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegistrationActivity : AppCompatActivity() {

    private lateinit var usernameField: EditText
    private lateinit var passwordField: EditText
    private lateinit var emailField: EditText
    private lateinit var registerBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registration)

        usernameField = findViewById(R.id.usernameField)
        passwordField = findViewById(R.id.passwordField)
        emailField = findViewById(R.id.emailField)
        registerBtn = findViewById(R.id.registerBtn)

        registerBtn.setOnClickListener {
            val isValidEmail: Boolean = isValidEmail(emailField.text.toString())
            val isValidPassword: Boolean = isValidPassword(passwordField.text.toString())

            if (isValidEmail && isValidPassword) {
                val homeIntent = Intent(this, HomeActivity::class.java)
                startActivity(homeIntent)
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Check if entered email is valid
    private fun isValidEmail(email: String): Boolean {

        // Check if address contains name, the @ character and a domain
        val pattern = Regex("^[A-Za-z0-9+_.-]+@+([A-Za-z0-9]+.)+[A-Za-z]{2,6}")

        if (pattern.containsMatchIn(email))
            return true
        else {
            Toast.makeText(baseContext, "Please write a valid email", Toast.LENGTH_LONG).show()
            return false
        }
    }

    private fun isValidPassword(password: String): Boolean {
        // Check if password contains upper and lowercase, digits and is 8 or more characters
        val pattern = Regex("(?=.*[A-Za-z0-9]{8,}$)")

        if (pattern.containsMatchIn(password))
            return true
        else {
            Toast.makeText(baseContext, "Password doesn't match requirements", Toast.LENGTH_LONG).show()
            return false
        }
    }
}