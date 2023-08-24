package com.farmus.farmus_application.ui.farm

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.farmus.farmus_application.R
import com.farmus.farmus_application.databinding.FragmentFarmSecondRegistrationBinding
import com.farmus.farmus_application.repository.UserPrefsStorage
import com.farmus.farmus_application.ui.MainActivity
import com.farmus.farmus_application.utilities.JWTUtils
import com.farmus.farmus_application.viewmodel.farm.FarmRegistrationViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

class SecondFarmRegistrationFragment: Fragment() {

    private lateinit var binding: FragmentFarmSecondRegistrationBinding
    private val farmRegistrationViewModel : FarmRegistrationViewModel by activityViewModels()


    private var isTotalConversionFirstText = true
    private var isTotalConversionSecondText = true

    private var isRentalConversionFirstText = true
    private var isRentalConversionSecondText = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_farm_second_registration, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.farmSecondRegistrationToolbar.toolbarWithoutTitleBackButton.setOnClickListener {
            (activity as MainActivity).changeFragment(FirstFarmRegistrationFragment())
        }

        binding.farmSecondRegistrationToolbarCompleteButton.setOnClickListener {
            val jwtToken = UserPrefsStorage.accessToken

            val startDate = LocalDate.now()
            val endDate = startDate.plusMonths(1).atStartOfDay()
            val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

            farmRegistrationViewModel.postFarmPostings(
                name = farmRegistrationViewModel.getFarmTitle(),
                owner = JWTUtils.decoded(jwtToken.toString())?.tokenBody?.email!!,
                startDate = startDate.format(dateFormat),
                endDate = endDate.format(dateTimeFormat),
                price = binding.farmSecondRegistrationRentalPrice.text.toString(),
                squaredMeters = binding.rentalConversionSecondText.text.toString(),
                locationBig = binding.cityTextItem.text.toString(),
                locationMid = binding.townTextItem.text.toString(),
                description = farmRegistrationViewModel.getFarmIntroduction(),
                imageFiles = farmRegistrationViewModel.getFarmPictures()
            )
        }

        farmRegistrationViewModel.postingsResponse.observe(viewLifecycleOwner) {
            if (it != null) {
                farmRegistrationViewModel.clearAllData()
                (activity as MainActivity).changeFragment(FarmFragment())
            }
        }

        farmRegistrationViewModel.postingsErrorResponse.observe(viewLifecycleOwner) {
            if (it != null) {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        }

        checkNext()

        //지역 설정 시/도
        val cityAutoCompleteTextView = binding.cityTextItem

        val cityItems = arrayListOf<String>("전체", "서울특별시", "경기도","강원도","충청북도","충청남도","전라북도","전라남도","경상북도","경상남도","제주특별자치도","부산광역시","대구광역시","인천광역시", "광주광역시","대전광역시","울산광역시","세종특별자치시")
        val cityItemAdapter = ArrayAdapter(requireContext(),R.layout.loc_dropdown_item_list,cityItems)
        cityAutoCompleteTextView.setAdapter(cityItemAdapter)

        //지역 설정 시/군/구
        val townAutoCompleteTextView = binding.townTextItem

        val townItems = arrayListOf<String>()
        setDropdown(cityItems, townItems, cityAutoCompleteTextView, townAutoCompleteTextView)

        val countryItemAdapter = ArrayAdapter(requireContext(), R.layout.loc_dropdown_item_list, townItems)
        townAutoCompleteTextView.setAdapter(countryItemAdapter)
    }

    private fun checkNext() {
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
                val totalConversion = binding.totalConversionFirstText.text.toString()
                val rentalConversion = binding.rentalConversionFirstText.text.toString()
                val rentalPriceStr = binding.farmSecondRegistrationRentalPrice.text.toString()
                val rentalPrice = if(rentalPriceStr.isNullOrEmpty()) 0 else rentalPriceStr.toInt()

                binding.farmSecondRegistrationToolbarCompleteButton.isEnabled =
                    totalConversion.isNotEmpty() && rentalConversion.isNotEmpty() && rentalPrice >= 1000

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
                val totalConversion = binding.totalConversionFirstText.text.toString()
                val rentalConversion = binding.rentalConversionFirstText.text.toString()
                val rentalPriceStr = binding.farmSecondRegistrationRentalPrice.text.toString()
                val rentalPrice = if(rentalPriceStr.isNullOrEmpty()) 0 else rentalPriceStr.toInt()

                binding.farmSecondRegistrationToolbarCompleteButton.isEnabled =
                    totalConversion.isNotEmpty() && rentalConversion.isNotEmpty() && rentalPrice >= 1000

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

        binding.farmSecondRegistrationRentalPrice.addTextChangedListener (object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.farmSecondRegistrationRentalPrice.text.toString().let {
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
                val totalConversion = binding.totalConversionFirstText.text.toString()
                val rentalConversion = binding.rentalConversionFirstText.text.toString()
                val rentalPriceStr = binding.farmSecondRegistrationRentalPrice.text.toString()
                val rentalPrice = if(rentalPriceStr.isNullOrEmpty()) 0 else rentalPriceStr.toInt()
                binding.farmSecondRegistrationToolbarCompleteButton.isEnabled =
                    totalConversion.isNotEmpty() && rentalConversion.isNotEmpty() && rentalPrice >= 1000
            }
        })
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