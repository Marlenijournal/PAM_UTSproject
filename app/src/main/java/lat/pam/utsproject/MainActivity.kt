package lat.pam.utsproject

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val usernameEditText = findViewById<EditText>(R.id.etUsername)
        val passwordEditText = findViewById<EditText>(R.id.etPassword)
        val loginButton = findViewById<Button>(R.id.btnLogin)
        
        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            val registeredUsername = sharedPreferences.getString("USERNAME", null)
            val registeredPassword = sharedPreferences.getString("PASSWORD", null)

            if (username == "admin" && password == "1234") {
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ListFoodActivity::class.java)
                startActivity(intent)
                finish()
            } else if (username == registeredUsername && password == registeredPassword) {
                // Akun default
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ListFoodActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Invalid Username or Password", Toast.LENGTH_SHORT).show()
            }
        }

        val tvRegister: TextView = findViewById(R.id.tvRegister)
        val registerText = "Don't have an account? Sign Up"

        val spannableString = SpannableString(registerText)
        val startIndex = registerText.indexOf("Sign Up")
        val endIndex = startIndex + "Sign Up".length

        // Membuat bagian "Sign Up" dapat diklik
        spannableString.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View) {
                // Pindah ke halaman RegistActivity
                val intent = Intent(this@MainActivity, Registrasi::class.java)
                startActivity(intent)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = true // Menambahkan garis bawah untuk menunjukkan link
                ds.color = Color.BLUE // Mengubah warna teks
            }
        }, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        // Set teks pada TextView dan aktifkan klik
        tvRegister.text = spannableString
        tvRegister.movementMethod = LinkMovementMethod.getInstance() // Mengaktifkan interaksi klik

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
