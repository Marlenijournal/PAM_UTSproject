package lat.pam.utsproject

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListFoodActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FoodAdapter
    private lateinit var foodList: List<Food>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list_food)


        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        foodList = listOf(
            Food("Batagor", "Batagor asli enak dari Bandung", R.drawable.batagor, 2),
            Food("Black Salad", "Salad segar yang dibuat secara langsung", R.drawable.black_salad, 5 ),
            Food("Cappucino", "Kopi cappucino asli yang dibuat dari Kopi Arabica", R.drawable.cappuchino, 3),
            Food("Cheesecake", "Cheesecake lembut, manisnya ga lebay ", R.drawable.cheesecake, 8),
            Food("Cireng", "Cireng rujak pilihan di sianghari", R.drawable.cireng, 2),
            Food("Donut", "Menu breakfast yang instant", R.drawable.donut, 2),
            Food("Kopi", "Kopi Hitam penambah fokus", R.drawable.kopi_hitam, 3),
            Food("Mie Goreng", "Menu andalan pengganti nasi", R.drawable.mie_goreng, 4),
            Food("Nasi Goreng", "Rasanya seperti buatan Ibu", R.drawable.nasigoreng, 4),
            Food("Sparkling Tea", "Minum yang seger kala panas", R.drawable.sparkling_tea, 3)
            )

        adapter = FoodAdapter(foodList) { selectedFood ->
            val intent = Intent(this, OrderActivity::class.java)
            intent.putExtra("foodName", selectedFood.name)
            intent.putExtra("foodDescription", selectedFood.description)
            intent.putExtra("foodImage", selectedFood.imageResourceId)
            intent.putExtra("foodPrice", selectedFood.price)
            startActivity(intent)
        }

        recyclerView.adapter = adapter


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}