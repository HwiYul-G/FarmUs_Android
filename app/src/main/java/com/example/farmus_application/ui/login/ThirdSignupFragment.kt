package com.example.farmus_application.ui.login

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.farmus_application.databinding.FragmentSignupThirdBinding

class SignupThirdFragment: Fragment(){
    private lateinit var viewBinding : FragmentSignupThirdBinding

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
        viewBinding = FragmentSignupThirdBinding.inflate(layoutInflater)

        viewBinding.signupPhoneToolbar.toolbarWithTitleBackButton.setOnClickListener{
            SignupActivity!!.supportFragmentManager.beginTransaction().remove(this).commit();
            SignupActivity!!.supportFragmentManager.popBackStack();
        }

        // 입력칸 관련 value 설정
        val editTextNum : EditText = viewBinding.phoneNumberField
        val editTextVerify : EditText = viewBinding.verifyNumberField

        // 전화번호 입력 여부에 따라 인증 버튼 활성화/비활성화
        editTextNum.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                viewBinding.sendVerifyButton.isEnabled = false
                if(s!=null && s.toString() != ""){
                    viewBinding.sendVerifyButton.isEnabled = true
                }
            }
        })

        // 타이머 설정
        val countDown = object : CountDownTimer(120000, 1000) {
            override fun onTick(p0: Long) {
                // countDownInterval 마다 호출 (여기선 1000ms)
                viewBinding.timerMinute.text = (p0 / (1000*60)).toString()
                viewBinding.timerSecond.text = ((p0 % (1000*60))/1000).toString()
            }
            override fun onFinish() {
                // 타이머가 종료되면 호출
            }
        }

        //인증 버튼 누를 경우 인증번호 입력 칸 및 버튼 표시
        viewBinding.sendVerifyButton.setOnClickListener{
            viewBinding.verifySentMessage.visibility = View.VISIBLE
            editTextVerify.visibility = View.VISIBLE
            viewBinding.resendVerifyButton.visibility = View.VISIBLE
            viewBinding.timerLayout.visibility = View.VISIBLE
            viewBinding.resendVerifyButton.isEnabled = true
            countDown.start()
        }

        // 인증번호 입력 여부에 따라 다음 버튼 활성화/비활성화
        editTextVerify.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                viewBinding.toFourthButton.isEnabled = false
                if(s!=null && s.toString() != ""){
                    viewBinding.toFourthButton.isEnabled = true
                }
            }
        })

        //재전송 버튼 클릭 시 제한시간 초기화 및 안내 문구
        viewBinding.resendVerifyButton.setOnClickListener {
            Toast.makeText(requireActivity(), "인증번호가 재전송되었습니다.", Toast.LENGTH_LONG).show()
            countDown.cancel()
            countDown.start()
        }

        //클릭 시 프래그먼트를 4번으로 스왑 (인증번호 일치 여부 확인 필요)
        viewBinding.toFourthButton.setOnClickListener {
//            if ( != editTextVerify.text.toString()) {
//
//            } else {
//                SignupActivity!!.replaceFragment(4)
//            }
            SignupActivity!!.replaceFragment(4)
        }

        return viewBinding.root
    }
}