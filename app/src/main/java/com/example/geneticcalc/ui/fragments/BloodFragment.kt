package com.example.geneticcalc.ui.fragments

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.geneticcalc.databinding.FragmentBloodBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class BloodFragment : Fragment() {
    private var _binding: FragmentBloodBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBloodBinding.inflate(inflater, container, false)
        val urlEditText = binding.urlEditText
        val imageView = binding.image
        val downloadButton = binding.downloadButton
        downloadButton.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val url = urlEditText.text.toString()
//            Picasso.with(context)
//                .load(url)
//                .placeholder(R.drawable.baseline_hourglass_empty_24)
//                .error(R.drawable.baseline_close_24)
//                .into(imageView)
                val bitmap = Glide.with(requireContext())
                    .asBitmap()
                    .load(url)
                    .submit()
                    .get()
                saveImage(bitmap)

                withContext(Dispatchers.Main) {
                    binding.image.setImageBitmap(bitmap)
                }
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun saveImage(bitmap: Bitmap) {
        val imagesFolder = File(requireContext().cacheDir, "images")
        try {
            imagesFolder.mkdirs()
            val file = File(imagesFolder, "test")
            val stream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)
            stream.flush()
            stream.close()
        } catch (_: IOException) {
        }
    }
}


//    @OptIn(DelicateCoroutinesApi::class)
//    private fun downloadImage(imageUrl: String) {
//        GlobalScope.launch(Dispatchers.IO) {
//            val bitmap = downloadBitmap(imageUrl)
//            if (bitmap != null) {
//                saveImageToInternalStorage(bitmap)
//            }
//        }
//    }
//
//    private fun downloadBitmap(imageUrl: String): Bitmap? {
//        var connection: HttpURLConnection? = null
//        var inputStream: InputStream? = null
//
//        try {
//            val url = URL(imageUrl)
//            connection = url.openConnection() as HttpURLConnection
//            connection.connect()
//
//            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
//                inputStream = connection.inputStream
//                return BitmapFactory.decodeStream(inputStream)
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//        } finally {
//            connection?.disconnect()
//            inputStream?.close()
//        }
//
//        return null
//    }
//
//    private fun saveImageToInternalStorage(bitmap: Bitmap?) {
//        bitmap?.let {
//            val fileName = "image.jpg"
//            val fileOutputStream: FileOutputStream
//            try {
//                fileOutputStream = requireContext().openFileOutput(fileName, 0)
//                it.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
//                fileOutputStream.close()
//
//                Handler(Looper.getMainLooper()).post {
//                    imageView.setImageBitmap(bitmap)
//                }
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//    }
//}