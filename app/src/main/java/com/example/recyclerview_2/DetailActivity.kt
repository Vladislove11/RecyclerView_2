package com.example.recyclerview_2

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailActivity : AppCompatActivity() {
    private val GALLARY_REQUEST = 302
    private var photoUri: Uri? = null

    private lateinit var displayNameTV: TextView
    private lateinit var displayDescriptionTV: TextView
    private lateinit var toolbar: Toolbar
    private lateinit var layoutCl : ConstraintLayout
    private lateinit var imageViewIV: ImageView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)

        layoutCl = findViewById(R.id.layoutCL)
        toolbar = findViewById(R.id.toolbar)
        displayDescriptionTV = findViewById(R.id.displayDescriptionTV)
        displayNameTV = findViewById(R.id.displayNameTV)
        imageViewIV = findViewById(R.id.imageViewIV)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        var wardrobe: Wardrobe? = null
        if (intent.hasExtra("wardrobe")) {
            wardrobe = intent.getSerializableExtra("wardrobe") as Wardrobe
        }
        if (wardrobe != null) {
            displayNameTV.text = wardrobe.name
            displayDescriptionTV.text = wardrobe.description
            imageViewIV.setImageResource(wardrobe.image)
        }

        layoutCl.setOnLongClickListener{
            val dialog = AlertDialog.Builder(this)
            val inflater = this.layoutInflater
            val dialogView = inflater.inflate(R.layout.update_dialog, null)
            dialog.setView(dialogView)
            val editName = dialogView.findViewById<EditText>(R.id.updateName)
            val editDescription: EditText = dialogView.findViewById(R.id.updateDescription)

            dialog.setTitle("обновить запись")
            dialog.setMessage("Введите данные")
            dialog.setPositiveButton("Обновить") {_, _, ->
                displayNameTV.text = editName.text.toString()
                displayDescriptionTV.text = editDescription.text.toString()
                val photoPickerIntent = Intent(Intent.ACTION_PICK)
                photoPickerIntent.type = "image/*"
                startActivityForResult(photoPickerIntent, GALLARY_REQUEST)
                val waedrobe = Wardrobe(displayNameTV.text.toString(), displayDescriptionTV.text.toString(), 3423)
            }
            dialog.setNegativeButton("Отмена") {_, _ ->

            }
            dialog.create().show()

            false
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        imageViewIV = findViewById(R.id.imageViewIV)
        when(requestCode) {
            GALLARY_REQUEST -> if (resultCode == RESULT_OK){
                photoUri = data?.data
                imageViewIV.setImageURI(photoUri)
            }
        }
    }
}