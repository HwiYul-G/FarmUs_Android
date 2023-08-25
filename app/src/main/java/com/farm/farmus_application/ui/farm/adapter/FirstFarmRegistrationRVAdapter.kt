package com.farm.farmus_application.ui.farm.adapter

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.farm.farmus_application.MyApplication
import com.farm.farmus_application.databinding.RvFarmRegistrationItemBinding
import java.io.File
import java.io.FileOutputStream

class FirstFarmRegistrationRVAdapter(
    var uris: MutableList<Uri>,
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
            val binding = RvFarmRegistrationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            FirstFarmRegistrationViewHolder(binding) { position ->
                uris.removeAt(position - 1)
                notifyDataSetChanged()
                itemClickListener.onItemDelete(getUriCount())
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position != 0) {
            (holder as FirstFarmRegistrationViewHolder).bind(uris[position - 1])
        }
    }

    override fun getItemCount(): Int {
        return uris.size + 1
    }

    fun getUriCount(): Int {
        return uris.size
    }

    fun getFiles(): List<File> {
        return urisToFileList(MyApplication.getApplicationContext(), uris)
    }

    fun urisToFileList(context: Context, uris: List<Uri>): List<File> {
        val fileList = mutableListOf<File>()
        for (uri in uris) {
            val inputStream = context.contentResolver.openInputStream(uri)
            val fileName = getFileName(context, uri)
            val tempFile = File(context.cacheDir, fileName)

            val outputStream = FileOutputStream(tempFile)
            inputStream?.copyTo(outputStream)
            inputStream?.close()
            outputStream.close()

            fileList.add(tempFile)
        }
        return fileList
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


    inner class FirstFarmRegistrationViewHolder(
        private val binding: RvFarmRegistrationItemBinding,
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