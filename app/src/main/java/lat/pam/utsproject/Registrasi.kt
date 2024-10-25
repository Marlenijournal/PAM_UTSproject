package lat.pam.utsproject

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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

class Registrasi : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registrasi)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inisialisasi elemen UI
        val usernameEditText = findViewById<EditText>(R.id.etUsernameReg)
        val passwordEditText = findViewById<EditText>(R.id.etPasswordReg)
        val btnRegist = findViewById<Button>(R.id.btnRegist)
        val tvRegister: TextView = findViewById(R.id.tvRegister)

        // Mendapatkan SharedPreferences
        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

        btnRegist.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                // Menyimpan username dan password di SharedPreferences
                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString("USERNAME", username)
                editor.putString("PASSWORD", password)
                editor.apply() // Menyimpan perubahan ke SharedPreferences

                Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()

                // Pindah ke halaman login setelah registrasi berhasil
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        // Menambahkan fungsionalitas ke TextView untuk pindah ke halaman login
        val registerText = "Already have an account? Sign In"
        val spannableString = SpannableString(registerText)
        val startIndex = registerText.indexOf("Sign In")
        val endIndex = startIndex + "Sign In".length

        // Membuat bagian "Sign In" dapat diklik
        spannableString.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View) {
                // Pindah ke halaman MainActivity
                val intent = Intent(this@Registrasi, MainActivity::class.java)
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
    }
}
