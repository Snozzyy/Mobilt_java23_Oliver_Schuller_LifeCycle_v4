package se.gritacademy.mobilt_java23_oliver_schuller_lifecycle_v4

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        bottomNav = findViewById(R.id.bottomNav)
        bottomNav.selectedItemId = R.id.homeItem
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.homeItem -> {
                    true
                }
                R.id.settingsItem -> {
                    val settingsIntent = Intent(this, SettingsActivity::class.java)
                    startActivity(settingsIntent)
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
}