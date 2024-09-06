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

class MainActivity : AppCompatActivity() {

    // UI components
    private lateinit var loginBtn: Button
    private lateinit var registerBtn: Button
    private lateinit var usernameInput: EditText
    private lateinit var passwordInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        usernameInput = findViewById(R.id.usernameInput)
        passwordInput = findViewById(R.id.passwordInput)

        loginBtn = findViewById(R.id.loginBtn)
        loginBtn.setOnClickListener {
            if (usernameInput.text.toString() == "oliver" && passwordInput.text.toString() == "oliver") {
                val homeIntent = Intent(this, HomeActivity::class.java)
                startActivity(homeIntent)
            } else {
                Toast.makeText(this, "Wrong username or password, try again",
                    Toast.LENGTH_LONG).show()
            }
        }

        registerBtn = findViewById(R.id.registerBtn)
        registerBtn.setOnClickListener {
            val registerIntent = Intent(this, RegistrationActivity::class.java)
            startActivity(registerIntent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}