package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Paint.Style
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.example.myapplication.databinding.ContentMainBinding
import java.io.FileNotFoundException


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ContentMainBinding
    private lateinit var photo: Bitmap
    private lateinit var style: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ContentMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageViewPhoto.setOnClickListener { PickPhoto() }
        binding.imageView2Style.setOnClickListener { StylePhoto() }
        binding.buttonTakePhotos.setOnClickListener { PickPhoto() }
        binding.buttonPhotoStyle.setOnClickListener { StylePhoto() }


    }

    fun PickPhoto() {
        var photoPickerIntent = Intent(Intent.ACTION_PICK)
        photoPickerIntent.setType("image/*")
        startActivityForResult(photoPickerIntent, 1)

    }


    fun StylePhoto() {
        var photoPickerIntent = Intent(Intent.ACTION_PICK)
        photoPickerIntent.setType("image/*")
        startActivityForResult(photoPickerIntent, 2)

        DoResult.getResult(photo, style)
    }

        fun ShowResult(bitmap: Bitmap) {
            binding.imageViewResult.setImageBitmap(bitmap)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, imageReturnedIntent: Intent?) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        when (requestCode) {
            1 ->
                if (resultCode == RESULT_OK) {
                    try {
                        val imageUri = imageReturnedIntent?.getData()
                        val imageStream = imageUri?.let { getContentResolver().openInputStream(it) }
                        val selectedImage = BitmapFactory.decodeStream(imageStream)
                        binding.imageViewPhoto.setImageBitmap(selectedImage)
                        photo = selectedImage
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            2 ->
                if (resultCode == RESULT_OK) {
                    try {
                        val imageUri = imageReturnedIntent?.getData()
                        val imageStream = imageUri?.let { getContentResolver().openInputStream(it) }
                        val selectedImage = BitmapFactory.decodeStream(imageStream)
                        binding.imageView2Style.setImageBitmap(selectedImage)
                        style = selectedImage
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
        }
    }
}