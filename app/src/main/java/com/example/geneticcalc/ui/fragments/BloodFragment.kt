package com.example.geneticcalc.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.geneticcalc.R
import com.example.geneticcalc.databinding.FragmentBloodBinding
import com.squareup.picasso.Picasso


class BloodFragment : Fragment() {
    private var _binding: FragmentBloodBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBloodBinding.inflate(inflater, container, false)
        val root = binding.root
        val urlEditText = binding.urlEditText
        val imageView = binding.image
        val downloadButton = binding.downloadButton
        downloadButton.setOnClickListener {
            val url = urlEditText.text.toString()
            Picasso.with(context)
                .load(url)
                .placeholder(R.drawable.baseline_hourglass_empty_24)
                .error(R.drawable.baseline_close_24)
                .into(imageView)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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