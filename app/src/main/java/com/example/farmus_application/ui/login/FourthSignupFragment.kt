package com.example.farmus_application.ui.login

import android.R
import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.farmus_application.databinding.FragmentSignupFourthBinding
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*


class SignupFourthFragment: Fragment(){

    private lateinit var viewBinding : FragmentSignupFourthBinding

    var SignupActivity: SignupActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        SignupActivity = context as SignupActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentSignupFourthBinding.inflate(inflater, container, false)

        // 입력칸 관련 value 설정
        val editTextName : EditText = viewBinding.nameTextField
        val editTextNick : EditText = viewBinding.nickTextField

        val onlyDate: LocalDate = LocalDate.now()
        val currentDate = onlyDate.toString().split("-")
        viewBinding.selectBrithDropdown.text = currentDate[0] + " | " + currentDate[1] + " | " + currentDate[2]

        // 날짜 선택 버튼 누를 경우 DatePickerDialog 생성
        val c = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            requireActivity(),
            R.style.Theme_Holo_Light_Dialog_MinWidth,
            {view, year, monthOfYear, dayOfMonth ->
                try {
                    val checkedDate = SimpleDateFormat(
                        "yyyy-MM-dd",
                        Locale.getDefault()
                    ).parse(year.toString() + "-" + (monthOfYear + 1) + "-" + dayOfMonth)
                    viewBinding.selectBrithDropdown.text = year.toString() + " | " + (monthOfYear + 1) + " | " + dayOfMonth
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            },
            c[Calendar.YEAR],
            c[Calendar.MONTH],
            c[Calendar.DAY_OF_MONTH]
        )
        datePickerDialog.datePicker.calendarViewShown = false
        datePickerDialog.window!!.setBackgroundDrawableResource(R.color.transparent)

        viewBinding.selectBrithDropdown.setOnClickListener{
            datePickerDialog.show()
        }

        // TODO:  모든 데이터가 입력되었을 경우 다음 버튼 활성화 조건 추가 필요
        viewBinding.toFifthSignupButton.isEnabled = true

        // 클릭 시 프래그먼트를 5번으로 스왑
        viewBinding.toFifthSignupButton.setOnClickListener {
            SignupActivity!!.replaceFragment(5)
        }

        return viewBinding.root
    }
}