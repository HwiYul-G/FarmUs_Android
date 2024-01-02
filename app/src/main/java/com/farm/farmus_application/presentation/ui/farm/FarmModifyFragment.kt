package com.farm.farmus_application.presentation.ui.farm

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.farm.farmus_application.R
import com.farm.farmus_application.databinding.FragmentFarmModifyBinding
import com.farm.farmus_application.model.farm.list.Pictures
import com.farm.farmus_application.ui.MainActivity
import com.farm.farmus_application.ui.farm.adapter.FarmModifyRVAdapter
import com.farm.farmus_application.presentation.viewmodel.farm.FarmModifyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@AndroidEntryPoint
class FarmModifyFragment: Fragment() {
    private val TAG = "FarmModifyFragment"
    private val PICK_IMAGE_REQUEST = 333

    private lateinit var binding: FragmentFarmModifyBinding
    private val farmModifyViewModel: FarmModifyViewModel by viewModels()

    private lateinit var imageView: ImageView
    private val imageUris = mutableListOf<Uri>()
    private lateinit var imageAdapter: FarmModifyRVAdapter

    private var isTotalConversionFirstText = true
    private var isTotalConversionSecondText = true

    private var isRentalConversionFirstText = true
    private var isRentalConversionSecondText = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_farm_modify,
            container,
            false
        )

        checkNext()

        setupImageButton()
        setupRecyclerView()
        getFarmDetail()

        binding.farmModifyToolbar.toolbarMainTitleText.text = "글 수정"
        binding.farmModifyToolbar.toolbarMainTitleText.setTextColor(resources.getColor(R.color.text_2))

        //뒤로가기 버튼
        binding.farmModifyToolbar.toolbarWithTitleBackButton.setOnClickListener{
            (activity as MainActivity).supportFragmentManager.apply {
                beginTransaction().remove(this@FarmModifyFragment).commit()
                popBackStack()
            }
        }

        binding.farmModifyCompleteButton.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                farmModifyViewModel.patchFarmEditinfo(
                    farmId = binding.farmDetail!!.FarmID,
                    farmName = binding.farmModifyFarmTitleEdit.text.toString(),
                    farmInfo = binding.farmModifyFarmIntroductionEdit.text.toString(),
                    locationBig = binding.cityTextItem.text.toString(),
                    locationMid = binding.townTextItem.text.toString(),
                    size = binding.rentalConversionFirstText.text.toString(),
                    price = binding.farmModifyRentalPrice.text.toString(),
                    file = imageAdapter.getFiles()
                )
            }
        }

        farmModifyViewModel.editinfoResponse.observe(viewLifecycleOwner) {
            (activity as MainActivity).supportFragmentManager.apply {
                beginTransaction().remove(this@FarmModifyFragment).commit()
                popBackStack()
            }
        }

        farmModifyViewModel.editinfoErrorResponse.observe(viewLifecycleOwner) {
            if (it != null) {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        }

        // image recyclerView 초기세팅
        farmModifyViewModel.farmDetail.observe(viewLifecycleOwner) {
            binding.farmDetail = it

            if (it.PictureObject != null) {
                convertToUris(it.PictureObject).forEach{
                    imageUris.add(it)
                }
            }

            imageAdapter.notifyDataSetChanged()
            farmModifyViewModel.notifyRecyclerviewChange(imageAdapter.getUriCount())
        }

        // location 초기세팅
        farmModifyViewModel.farmDetail.observe(viewLifecycleOwner) {
            //지역 설정 시/도
            val cityAutoCompleteTextView = binding.cityTextItem

            cityAutoCompleteTextView.setText(it.LocationBig)

            val cityItems = arrayListOf<String>("전체", "서울특별시", "경기도","강원도","충청북도","충청남도","전라북도","전라남도","경상북도","경상남도","제주특별자치도","부산광역시","대구광역시","인천광역시", "광주광역시","대전광역시","울산광역시","세종특별자치시")
            val cityItemAdapter = ArrayAdapter(requireContext(),R.layout.loc_dropdown_item_list,cityItems)
            cityAutoCompleteTextView.setAdapter(cityItemAdapter)

            //지역 설정 시/군/구
            val townAutoCompleteTextView = binding.townTextItem

            townAutoCompleteTextView.setText(it.LocationMid)

            val townItems = arrayListOf<String>()
            setDropdown(cityItems, townItems, cityAutoCompleteTextView, townAutoCompleteTextView)

            val countryItemAdapter = ArrayAdapter(requireContext(), R.layout.loc_dropdown_item_list, townItems)
            townAutoCompleteTextView.setAdapter(countryItemAdapter)
        }

        return binding.root
    }

    private fun checkNext() {
        binding.farmModifyFarmTitleEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                __checkNext()
            }
        })

        binding.farmModifyFarmIntroductionEdit.addTextChangedListener(object :
            TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val introductionEdit = binding.farmModifyFarmIntroductionEdit
                val introductionLength = introductionEdit.text.toString().length
                binding.introductionEditCount.text = introductionLength.toString()
                if (introductionLength > 150) {
                    introductionEdit.setText(introductionEdit.toString().substring(0, 150))
                    introductionEdit.setSelection(introductionLength)
                }
            }
            override fun afterTextChanged(p0: Editable?) {
                __checkNext()
            }
        })

        farmModifyViewModel.urisCount.observe(viewLifecycleOwner) {
            __checkNext()
        }

        binding.totalConversionFirstText.addTextChangedListener (object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (isTotalConversionFirstText) {
                    isTotalConversionSecondText = false
                    binding.totalConversionSecondText.setText(
                        convertPyeongToSquareMeter(binding.totalConversionFirstText.text.toString())
                    )
                }
            }
            override fun afterTextChanged(p0: Editable?) {
                __checkNext()

                isTotalConversionFirstText = true
            }
        })

        binding.totalConversionSecondText.addTextChangedListener (object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (isTotalConversionSecondText) {
                    isTotalConversionFirstText = false
                    binding.totalConversionFirstText.setText(
                        convertSquareMeterToPyeong(binding.totalConversionSecondText.text.toString())
                    )
                }
            }
            override fun afterTextChanged(p0: Editable?) {
                isTotalConversionSecondText = true
            }
        })

        binding.rentalConversionFirstText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (isRentalConversionFirstText) {
                    isRentalConversionSecondText = false
                    binding.rentalConversionSecondText.setText(
                        convertPyeongToSquareMeter(binding.rentalConversionFirstText.text.toString())
                    )
                }
            }
            override fun afterTextChanged(p0: Editable?) {
                __checkNext()

                isRentalConversionFirstText = true
            }
        })

        binding.rentalConversionSecondText.addTextChangedListener (object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (isRentalConversionSecondText) {
                    isRentalConversionFirstText = false
                    binding.rentalConversionFirstText.setText(
                        convertSquareMeterToPyeong(binding.rentalConversionSecondText.text.toString())
                    )
                }
            }
            override fun afterTextChanged(p0: Editable?) {
                isRentalConversionSecondText = true
            }
        })

        binding.farmModifyRentalPrice.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.farmModifyRentalPrice.text.toString().let {
                    if (it.isNullOrEmpty()) {
                        binding.rentalPriceError.visibility = View.VISIBLE
                    } else {
                        if (it.toInt() >= 1000) {
                            binding.rentalPriceError.visibility = View.INVISIBLE
                        } else {
                            binding.rentalPriceError.visibility = View.VISIBLE
                        }
                    }
                }
            }
            override fun afterTextChanged(p0: Editable?) {
                __checkNext()
            }
        })

    }

    private fun __checkNext(){
        val titleEdit = binding.farmModifyFarmTitleEdit.text.toString()
        val introductionEdit = binding.farmModifyFarmIntroductionEdit.text.toString()
        val pictureItemsCount = imageAdapter.getUriCount()
        val totalConversion = binding.totalConversionFirstText.text.toString()
        val rentalConversion = binding.rentalConversionFirstText.text.toString()
        val rentalPriceStr = binding.farmModifyRentalPrice.text.toString()
        val rentalPrice = if (rentalPriceStr.isNullOrEmpty()) 0
        else rentalPriceStr.toInt()
        binding.farmModifyCompleteButton.isEnabled =
            titleEdit.isNotEmpty() && introductionEdit.isNotEmpty() && pictureItemsCount > 0
                    && totalConversion.isNotEmpty() && rentalConversion.isNotEmpty() && rentalPrice >= 1000
    }

    private fun getFarmDetail() {
        farmModifyViewModel.getTempFileDetail()
    }

    private fun setupImageButton() {
        imageView = ImageView(context).apply {
            setImageResource(R.drawable.add_a_photo)
            setBackgroundResource(R.drawable.camera_green_background_vector_image)
            layoutParams = ViewGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            scaleType = ImageView.ScaleType.CENTER
            setOnClickListener {
                selectImages()
            }
        }
    }

    private fun setupRecyclerView() {
        imageAdapter = FarmModifyRVAdapter(imageUris, imageView, object : FarmModifyRVAdapter.ItemClickListener {
            override fun onItemDelete(urisCount: Int) {
                farmModifyViewModel.notifyRecyclerviewChange(urisCount)
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

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                Log.d(TAG,"data is null")
                return
            }

            val clipData = data.clipData
            if (clipData != null) {
                for (i in 0 until Math.min(clipData.itemCount, 5 - imageUris.size)) {
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
            farmModifyViewModel.notifyRecyclerviewChange(imageAdapter.getUriCount())
        }
    }

    private fun setDropdown(checkList: ArrayList<String>, itemList: ArrayList<String>, cityAutoCompleteTextView: AutoCompleteTextView, townCompleteTextView: AutoCompleteTextView) {
        cityAutoCompleteTextView.setOnItemClickListener{ adapterView, view, position, rowId ->
            itemList.clear()
            when(checkList[position]){
                "서울특별시" -> itemList.addAll(resources.getStringArray(R.array.seoul_list))
                "경기도" -> itemList.addAll(resources.getStringArray(R.array.gyeonggi_list))
                "강원도" -> itemList.addAll(resources.getStringArray(R.array.gangwon_list))
                "충청북도" -> itemList.addAll(resources.getStringArray(R.array.chungbuk_list))
                "충청남도" -> itemList.addAll(resources.getStringArray(R.array.chungnam_list))
                "전라북도" -> itemList.addAll(resources.getStringArray(R.array.jeonbuk_list))
                "전라남도" -> itemList.addAll(resources.getStringArray(R.array.jeonnam_list))
                "경상북도" -> itemList.addAll(resources.getStringArray(R.array.gyeongbuk_list))
                "경상남도" -> itemList.addAll(resources.getStringArray(R.array.gyeongnam_list))
                "제주특별자치도" -> itemList.addAll(resources.getStringArray(R.array.jeju_list))
                "부산광역시" -> itemList.addAll(resources.getStringArray(R.array.busan_list))
                "대구광역시" -> itemList.addAll(resources.getStringArray(R.array.daegu_list))
                "인천광역시" -> itemList.addAll(resources.getStringArray(R.array.incheon_list))
                "광주광역시" -> itemList.addAll(resources.getStringArray(R.array.gwangju_list))
                "대전광역시" -> itemList.addAll(resources.getStringArray(R.array.daejeon_list))
                "울산광역시" -> itemList.addAll(resources.getStringArray(R.array.ulsan_list))
                "세종특별자치시" -> itemList.addAll(resources.getStringArray(R.array.sejong_list))
                else -> itemList.add("전체")
            }
        }
    }

    private fun convertToUris(pictures: List<Pictures>): List<Uri> {
        return pictures.mapNotNull { picture ->
            picture.PictureUrl?.let { Uri.parse(it) }
        }
    }

    private fun convertSquareMeterToPyeong(squareMeter: String): String {
        if (squareMeter.isNullOrEmpty()) {
            return ""
        }

        return (squareMeter.toInt() / 3.3).roundToInt().toString()
    }

    private fun convertPyeongToSquareMeter(pyeong: String): String {
        if (pyeong.isNullOrEmpty()) {
            return ""
        }

        return (pyeong.toInt() * 3.3).roundToInt().toString()
    }

}