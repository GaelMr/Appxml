package com.example.mascoxml
import android.content.Intent
import com.example.mascoxml.R
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
class MainActivity : AppCompatActivity() {
    lateinit var usernameInput: EditText
    lateinit var passwordInput: EditText
    lateinit var loginBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        usernameInput = findViewById(R.id.username_id)
        passwordInput = findViewById(R.id.passwordID)
        loginBtn = findViewById(R.id.button)
        loginBtn.setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()
            Log.i("Test Credentials", "Username: $username and password: $password")
            if ((username == "hola") && (password == "1234")) {
                val intent=Intent(this,SecondActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this, "no jalo", Toast.LENGTH_SHORT).show()

            }
        }
    }
}