package com.example.farmus_application.ui.farm

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout.LayoutParams
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.farmus_application.R
import com.example.farmus_application.databinding.FragmentFarmFirstRegistrationBinding
import com.example.farmus_application.ui.MainActivity
import com.example.farmus_application.ui.farm.adapter.FirstFarmRegistrationRVAdapter
import com.example.farmus_application.viewmodel.farm.FarmRegistrationViewModel
import java.io.File
import java.lang.Math.min

class FirstFarmRegistrationFragment: Fragment() {
    private val TAG = "FirstFarmRegistrationFragment"
    private val PICK_IMAGE_REQUEST = 333

    private lateinit var binding: FragmentFarmFirstRegistrationBinding
    private val farmRegistrationViewModel : FarmRegistrationViewModel by activityViewModels()

    private lateinit var imageView: ImageView
    private val imageUris = mutableListOf<Uri>()
    private lateinit var imageAdapter: FirstFarmRegistrationRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_farm_first_registration, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkNext()

        setupImageButton()
        setupRecyclerView()
        setupUI()

        (activity as MainActivity).hideBottomNavigation(true)

        //뒤로가기 버튼
        binding.farmFirstRegistrationToolbar.toolbarWithoutTitleBackButton.setOnClickListener{
            (activity as MainActivity).changeFragment(FarmFragment())
        }

        binding.farmFirstRegistrationToolbarNextButton.setOnClickListener {
            val farmTitle = binding.farmFirstRegistrationFarmTitleEdit.text.toString()
            val farmIntroduction = binding.farmFirstRegistrationFarmIntroductionEdit.text.toString()
            val farmPictures = imageAdapter.getFiles()
            val farmPicturesUri = imageUris

            farmRegistrationViewModel.saveFirstFragmentData(
                farmTitle = farmTitle,
                farmIntroduction = farmIntroduction,
                farmPictures = farmPictures,
                farmPicturesUri = farmPicturesUri
            )

            (activity as MainActivity).changeFragment(SecondFarmRegistrationFragment())
        }

    }

    private fun checkNext() {
        binding.farmFirstRegistrationFarmTitleEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                val titleEdit = binding.farmFirstRegistrationFarmTitleEdit.text.toString()
                val introductionEdit = binding.farmFirstRegistrationFarmIntroductionEdit.text.toString()
                val pictureItemsCount = imageAdapter.getUriCount()
                binding.farmFirstRegistrationToolbarNextButton.isEnabled =
                    titleEdit.isNotEmpty() && introductionEdit.isNotEmpty() && pictureItemsCount > 0
            }
        })

        binding.farmFirstRegistrationFarmIntroductionEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val introductionEdit = binding.farmFirstRegistrationFarmIntroductionEdit
                val introductionLength = introductionEdit.text.toString().length
                binding.introductionEditCount.text = introductionLength.toString()
                if (introductionLength > 150) {
                    introductionEdit.setText(introductionEdit.toString().substring(0, 150))
                    introductionEdit.setSelection(introductionLength)
                }
            }
            override fun afterTextChanged(p0: Editable?) {
                val titleEdit = binding.farmFirstRegistrationFarmTitleEdit.text.toString()
                val introductionEdit = binding.farmFirstRegistrationFarmIntroductionEdit.text.toString()
                val pictureItemsCount = imageAdapter.getUriCount()
                binding.farmFirstRegistrationToolbarNextButton.isEnabled =
                    titleEdit.isNotEmpty() && introductionEdit.isNotEmpty() && pictureItemsCount > 0
            }
        })

        farmRegistrationViewModel.urisCount.observe(viewLifecycleOwner) {
            val titleEdit = binding.farmFirstRegistrationFarmTitleEdit.text.toString()
            val introductionEdit = binding.farmFirstRegistrationFarmIntroductionEdit.text.toString()
            val pictureItemsCount = it
            binding.farmFirstRegistrationToolbarNextButton.isEnabled =
                titleEdit.isNotEmpty() && introductionEdit.isNotEmpty() && pictureItemsCount > 0
        }

    }


    private fun setupUI() {
        binding.farmFirstRegistrationFarmTitleEdit.setText(farmRegistrationViewModel.getFarmTitle())
        binding.farmFirstRegistrationFarmIntroductionEdit.setText(farmRegistrationViewModel.getFarmIntroduction())
        farmRegistrationViewModel.getFarmPicturesUri().forEach {
            imageUris.add(it)
        }

        imageAdapter.notifyDataSetChanged()
        farmRegistrationViewModel.notifyRecyclerviewChange(imageAdapter.getUriCount())
    }

    private fun setupImageButton() {
        imageView = ImageView(context).apply {
            setImageResource(R.drawable.add_a_photo)
            setBackgroundResource(R.drawable.camera_green_background_vector_image)
            layoutParams = ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            scaleType = ImageView.ScaleType.CENTER
            setOnClickListener {
                selectImages()
            }
        }
    }

    private fun setupRecyclerView() {
        imageAdapter = FirstFarmRegistrationRVAdapter(imageUris, imageView, object : FirstFarmRegistrationRVAdapter.ItemClickListener {
            override fun onItemDelete(urisCount: Int) {
                farmRegistrationViewModel.notifyRecyclerviewChange(urisCount)
            }
        })
        binding.imageRecyclerView.adapter = imageAdapter
    }

    private fun selectImages() {
        val intent = Intent().apply {
            type = "image/*"
            action = Intent.ACTION_GET_CONTENT
            putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        }

        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
            if (data == null) {
                Log.d(TAG,"data is null")
                return
            }

            val clipData = data.clipData
            if (clipData != null) {
                for (i in 0 until min(clipData.itemCount, 5 - imageUris.size)) {
                    val imageUri = clipData.getItemAt(i).uri
                    imageUris.add(imageUri)
                }
            } else {
                data.data?.let { imageUri ->
                    if (imageUris.size < 5) {
                        imageUris.add(imageUri)
                    }
                }
            }
            imageAdapter.notifyDataSetChanged()
            farmRegistrationViewModel.notifyRecyclerviewChange(imageAdapter.getUriCount())
        }
    }


}