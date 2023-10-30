package com.farm.farmus_application.ui.farm.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.farm.farmus_application.MyApplication
import com.farm.farmus_application.databinding.RvFarmModifyItemBinding
import kotlinx.coroutines.Dispatchers
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.net.URL
import java.util.UUID
import kotlin.concurrent.thread
import kotlinx.coroutines.withContext

class FarmModifyRVAdapter(var uris: MutableList<Uri>,
                          private val imageView: ImageView,
                          private val itemClickListener: ItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_IMAGE_BUTTON = 0
        private const val VIEW_TYPE_IMAGE = 1
    }

    interface ItemClickListener {
        fun onItemDelete(urisCount: Int)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) VIEW_TYPE_IMAGE_BUTTON else VIEW_TYPE_IMAGE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_IMAGE_BUTTON) {
            object : RecyclerView.ViewHolder(imageView) {}
        } else {
            val binding = RvFarmModifyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            FarmModifyViewHolder(binding) { position ->
                uris.removeAt(position - 1)
                notifyDataSetChanged()
                itemClickListener.onItemDelete(getUriCount())
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position != 0) {
            (holder as FarmModifyViewHolder).bind(uris[position - 1])
        }
    }

    override fun getItemCount(): Int {
        return uris.size + 1
    }

    fun getUriCount(): Int {
        return uris.size
    }

    suspend fun getFiles(): List<File> {
        val files = urisToFileList(MyApplication.getApplicationContext(), uris)
        Log.e("xdxdxd", files.toString())
        return files
    }

    suspend fun urisToFileList(context: Context, uris: List<Uri>): List<File> = withContext(Dispatchers.IO) {
        val fileList = mutableListOf<File>()

        for (uri in uris) {
            try {
                val inputStream = context.contentResolver.openInputStream(uri)
                val fileName = getFileName(context, uri)
                val tempFile = File(context.cacheDir, fileName)

                val outputStream = FileOutputStream(tempFile)
                inputStream?.copyTo(outputStream)
                inputStream?.close()
                outputStream.close()

                fileList.add(tempFile)
            } catch (e: FileNotFoundException) {
                // 웹 URL로부터 이미지를 가져올 경우 FileNotFoundException이 발생하여 비트맵으로 별도 저장해야함

                val bitmap = getBitmapFromWebURL(uri.toString())
                if (bitmap != null) {
                    val tempFile = saveBitmapToFile(context, bitmap)
                    fileList.add(tempFile)
                }
            }
        }

        return@withContext fileList
    }

    fun getBitmapFromWebURL(url: String): Bitmap? {
        var bitmap: Bitmap? = null
        try {
            val connection = URL(url).openConnection()
            connection.doInput = true
            connection.connect()
            val inputStream = connection.getInputStream()
            bitmap = BitmapFactory.decodeStream(inputStream)
        } catch (e: Exception) {
            // 웹 URL로부터 이미지를 가져오는 중 에러 발생
            e.printStackTrace()
        }
        return bitmap
    }

    fun saveBitmapToFile(context: Context, bitmap: Bitmap): File {
        var file = File("null")

        try {
            val timestamp = System.currentTimeMillis()
            val randomUUID = UUID.randomUUID().toString().substring(0, 8) // 처음 8자리의 랜덤 UUID
            val fileName = "tempImage_$timestamp" + "_$randomUUID.jpg"
            file = File(context.cacheDir, fileName)

            Log.e("xxxxxxx", file.toString())

            val outStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream)
            outStream.flush()
            outStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return file
    }

    private fun getFileName(context: Context, uri: Uri): String {
        var fileName = "unknown_file"
        context.contentResolver.query(uri, null, null, null, null)?.use { cursor ->
            val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            if (cursor.moveToFirst()) {
                fileName = cursor.getString(nameIndex)
            }
        }
        return fileName
    }


    inner class FarmModifyViewHolder(
        private val binding: RvFarmModifyItemBinding,
        private val onDelete: (Int) -> Unit) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(uri: Uri) {
            Glide.with(binding.root.context)
                .load(uri)
                .circleCrop()
                .into(binding.ivItemImg)

            binding.ivItemDelete.setOnClickListener {
                onDelete(adapterPosition)
            }
        }
    }

}