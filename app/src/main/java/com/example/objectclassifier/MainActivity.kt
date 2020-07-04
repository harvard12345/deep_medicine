package com.example.objectclassifier

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val mInputSize = 224
    private val mModelPath = "converted_model.tflite"
    private val mLabelPath = "label.txt"
    private lateinit var classifier: Classifier
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        classifier = Classifier(assets, mModelPath, mLabelPath, mInputSize)
        button_choose_image?.setOnClickListener { openFileChooser() }
        classify_button?.setOnClickListener { classify() }
    }

    private fun classify() {
        if (image_view.drawable != null) {
            val bitmap = (image_view.drawable as BitmapDrawable).bitmap
            val result = classifier.recognizeImage(bitmap)
            val intent = Intent(this@MainActivity, Result::class.java)
            intent.putExtra("label1", result[0].title)
            intent.putExtra("probability1", result[0].confidence)
            intent.putExtra("label2", result[1].title)
            intent.putExtra("probability2", result[1].confidence)
            intent.putExtra("imageUri", imageUri);
            startActivity(intent)
        } else {
            Toast.makeText(this, "Select an image!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openFileChooser() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_PICK
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            imageUri = data.data
            Picasso.with(this).load(imageUri).into(image_view)
        }
    }
}