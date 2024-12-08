package com.example.recyclerview_2

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SecondActivity : AppCompatActivity() {

    private lateinit var recyclerViewRV : RecyclerView
    private lateinit var toolbar : Toolbar
    private val wardrobe = mutableListOf(
        Wardrobe("Рубашка", "На лето", R.drawable.shirt_2),
        Wardrobe("Рубашка", "На осень", R.drawable.shirt_1),
        Wardrobe("Рубашка", "На лето", R.drawable.shirt_3),
        Wardrobe("Рубашка", "На лето", R.drawable.shirt_2),
        Wardrobe("Рубашка", "На лето", R.drawable.shirt_4),
        Wardrobe("Куртка", "Кожанная, на осень", R.drawable.jacket),
        Wardrobe("Ботинки", "На зиму", R.drawable.shoes_2),
        Wardrobe("Ботинки", "На осень", R.drawable.shoes_1),
        Wardrobe("Ботинки", "На осень", R.drawable.shoes_3),
        Wardrobe("Ботинки", "По бездарожью", R.drawable.shoes_4),
        Wardrobe("Шапка", "На осень", R.drawable.cap_1),
        Wardrobe("Шапка", "На зиму", R.drawable.cap_2),
        Wardrobe("Кепка", "На лето", R.drawable.cap_3),
        Wardrobe("Худи", "На работу", R.drawable.hoodi_1),
        Wardrobe("Худи", "На отдых", R.drawable.hoodi_2),
        Wardrobe("Худи", "Ходить дома", R.drawable.hoodi_3),
        Wardrobe("Кросовки", "Всё для спорта", R.drawable.sneakers),
        Wardrobe("Футболка", "На свидание", R.drawable.t_shirt_1),
        Wardrobe("Футболка", "На работу", R.drawable.t_shirt_2),
        Wardrobe("Шорты", "На отдых", R.drawable.shorts_1),
        Wardrobe("Шорты", "За пивом", R.drawable.shorts_2),
        Wardrobe("Штаны", "На прогулку", R.drawable.trousers_1),
        Wardrobe("Штаны", "На работу", R.drawable.trousers_2),
        Wardrobe("Штаны", "На свидание", R.drawable.trousers_3),
        Wardrobe("Штаны", "На рыбалку", R.drawable.trousers_4),

        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        title = ""

        recyclerViewRV = findViewById(R.id.recyclerViewRV)
        recyclerViewRV.layoutManager = LinearLayoutManager(this)
        val adapter = CustomAdapter(wardrobe)
        recyclerViewRV.adapter = adapter

        recyclerViewRV.setHasFixedSize(true)
        adapter.setOnWardrobeClickListener(object :
            CustomAdapter.OnWardrobeClickListener{
            override fun onWardrobeClick(wardrobe: Wardrobe, position: Int) {
                val intent = Intent(this@SecondActivity, DetailActivity::class.java)
                intent.putExtra("wardrobe", wardrobe)
                startActivity(intent)
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.context_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.exitMenuMain ->{
                finishAffinity()
                Toast.makeText(this,"Приложение завершено", Toast.LENGTH_LONG).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}