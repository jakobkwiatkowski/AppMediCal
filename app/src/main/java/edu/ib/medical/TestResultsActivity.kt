package edu.ib.medical

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class TestResultsActivity : AppCompatActivity() {

    lateinit var photoPath: String
    val REQUEST_IMAGE_CAPTURE = 1
    val cameraRequest = 1888

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.testresults)
        val takePictureBTN = findViewById<Button>(R.id.takePicture)
        val folderBTN = findViewById<Button>(R.id.openFolder)
        goMenu()

        takePictureBTN.setOnClickListener {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                cameraRequest
            )
            goTakePicture()
        }
        folderBTN.setOnClickListener { openFolder() }
    }

    private fun goTakePicture() {

        val camerabutton = findViewById<Button>(R.id.takePicture)
        var photoFile: File? = null

        if (ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_DENIED
        )
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                cameraRequest
            )

        camerabutton.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            try {
                photoFile = createImageFile()
                startActivityForResult(intent, 1)
            } catch (e: IOException) {
            }
            if (photoFile != null) {
                val photoUri = FileProvider.getUriForFile(
                    this, "com.example.android.fileprovider1", photoFile!!
                )
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            val picture = findViewById<ImageView>(R.id.picture)
            //picture.rotation = 90f
            picture.setImageURI(Uri.parse(photoPath))
        }
    }

    private fun createImageFile(): File? {
        val time = SimpleDateFormat("dd-MM-yyyy_HH:mm").format(Date())
        val fileName = "Badanie_$time"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val image = File(storageDir, fileName + ".jpg")
        photoPath = image.absolutePath
        return image
    }

    fun openFolder() {
        val intent = Intent(Intent.ACTION_VIEW)
        val uri = Uri.parse("/storage/emulated/0/Android/data/edu.ib.medical/files/Pictures")
        intent.setDataAndType(uri, "*/*")
        startActivity(intent)

    }

    private fun goMenu() {
        val menuButton = findViewById<ImageView>(R.id.menuButton3)
        menuButton.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }
}