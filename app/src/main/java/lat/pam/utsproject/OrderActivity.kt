package lat.pam.utsproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class OrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_order)

        val foodName = intent.getStringExtra("foodName")

        val foodNameTextView: TextView = findViewById(R.id.etFoodName)
        foodNameTextView.text = foodName

        val servingsEditText: EditText = findViewById(R.id.etServings)
        val nameEditText: EditText = findViewById(R.id.etName)
        val notesEditText: EditText = findViewById(R.id.etNotes)

        val placeOrderButton: Button = findViewById(R.id.btnOrder)
        placeOrderButton.setOnClickListener {
            val servings = servingsEditText.text.toString()
            val name = nameEditText.text.toString()
            val notes = notesEditText.text.toString()

            val intent = Intent(this, ConfirmationActivity::class.java)
            intent.putExtra("foodName", foodName)
            intent.putExtra("servings", servings)
            intent.putExtra("name", name)
            intent.putExtra("notes", notes)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
