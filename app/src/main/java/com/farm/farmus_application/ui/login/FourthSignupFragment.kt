package com.farm.farmus_application.ui.login

import android.R
import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.farm.farmus_application.utilities.ValidationCheckUtil
import com.farm.farmus_application.databinding.FragmentSignupFourthBinding
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*


class SignupFourthFragment: Fragment(){

    private lateinit var viewBinding : FragmentSignupFourthBinding

    var signupActivity: SignupActivity? = null
    private var checkName = false
    private var checkNickName = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        signupActivity = context as SignupActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentSignupFourthBinding.inflate(inflater, container, false)

        viewBinding.signupInfoToolbar.toolbarWithoutTitleBackButton.setOnClickListener{
            signupActivity!!.supportFragmentManager.beginTransaction().remove(this).commit()
            signupActivity!!.supportFragmentManager.popBackStack()
        }

        val editTextId = arguments?.getString("idText").toString()
        val editTextPw = arguments?.getString("pwText").toString()
        val editTextPhone = arguments?.getString("phoneText").toString()

        // 입력칸 관련 value 설정
        val editTextName = viewBinding.nameTextField
        val editTextNick = viewBinding.nickTextField
        editTextName.tag = "Name"
        editTextNick.tag = "NickName"

        checkEditText(editTextName)
        checkEditText(editTextNick)
        val datePickerDialog = datePickerDialog()

        viewBinding.selectBrithDropdown.setOnClickListener{
            datePickerDialog.show()
        }

        // TODO:  모든 데이터가 입력되었을 경우 다음 버튼 활성화 조건 추가 필요
//        viewBinding.toFifthSignupButton.isEnabled = true

        // 클릭 시 프래그먼트를 5번으로 스왑
        viewBinding.toFifthSignupButton.setOnClickListener {
            val bundle = Bundle().apply {
                putString("idText",editTextId)
                putString("pwText",editTextPw)
                putString("phoneText",editTextPhone)
                putString("nameText", editTextName.text.toString())
                putString("nickNameText", editTextNick.text.toString())
            }
            signupActivity!!.replaceFragment(5, bundle)
        }

        return viewBinding.root
    }

    private fun datePickerDialog(): DatePickerDialog {
        val onlyDate: LocalDate = LocalDate.now()
        val currentDate = onlyDate.toString().split("-")
        viewBinding.selectBrithDropdown.text =
            currentDate[0] + " | " + currentDate[1] + " | " + currentDate[2]

        // 날짜 선택 버튼 누를 경우 DatePickerDialog 생성
        val c = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            requireActivity(),
            R.style.Theme_Holo_Light_Dialog_MinWidth,
            { view, year, monthOfYear, dayOfMonth ->
                try {
                    val checkedDate = SimpleDateFormat(
                        "yyyy-MM-dd",
                        Locale.getDefault()
                    ).parse(year.toString() + "-" + (monthOfYear + 1) + "-" + dayOfMonth)
                    viewBinding.selectBrithDropdown.text =
                        year.toString() + " | " + (monthOfYear + 1) + " | " + dayOfMonth
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
        return datePickerDialog
    }

    private fun checkEditText(EditText: EditText) {
        EditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(e: Editable?) {
                if (EditText.tag == "Name") {
                    val name = e.toString()
                    checkName = name.isNotEmpty()
                    // TODO : 이름에 대한 확인도 필요할지도?
                    // 이름은 비어있지만 않으면 괜찮게 처리
                    viewBinding.toFifthSignupButton.isEnabled = checkNickName
                }
                if (EditText.tag == "NickName") {
                    val nickname = e.toString()
                    checkNickName = nickname.isNotEmpty()

                    if(checkNickName){
                        val nicknameStatus = ValidationCheckUtil.checkNickname(nickname)
                        val errorMessage = when (nicknameStatus) {
                            ValidationCheckUtil.NicknameStatus.SPECIAL_CHARACTERS -> "특수 문자를 포함할 수 없습니다."
                            ValidationCheckUtil.NicknameStatus.SPACE -> "띄어쓰기를 사용할 수 없습니다."
                            ValidationCheckUtil.NicknameStatus.BAD_WORDS -> "비속어를 포함할 수 없습니다."
                            else -> null
                        }

                        if(errorMessage != null){
                            viewBinding.nickNameWarningMessage.text = errorMessage
                            viewBinding.toFifthSignupButton.isEnabled = false
                        } else{
                            // TODO : 사용가능한 닉네임인경우 -> Button으로 닉네임 중복 체크 이벤트 발생시 UI 변경 로직
                            // 우선 임시 처리
                            viewBinding.nickNameWarningMessage.text = null
                            viewBinding.toFifthSignupButton.isEnabled = true
                        }
                    }
                }
            }
        })
    }

}

