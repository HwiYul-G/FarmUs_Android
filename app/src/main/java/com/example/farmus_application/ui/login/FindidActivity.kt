package com.example.farmus_application.ui.login

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.farmus_application.databinding.ActivityFindIdBinding
import com.example.farmus_application.ui.StartActivity
import com.example.farmus_application.viewmodel.findAccount.FindAccountViewModel

class FindidActivity : AppCompatActivity() {

    private lateinit var findIdBinding: ActivityFindIdBinding
    private val findAccountViewModel : FindAccountViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findIdBinding = ActivityFindIdBinding.inflate(layoutInflater)
        setContentView(findIdBinding.root)

        val editTextName : EditText = findIdBinding.nameTextField
        val editTextNum : EditText = findIdBinding.phoneNumberField
        val editTextVerify : EditText = findIdBinding.verifyNumberField

        // 추후 이전 화면에 따른 메인툴바의 설명 표시 변화 필요
        findIdBinding.findidFirstToolbar.toolbarMainTitleText.text = "아이디 찾기"

        // 추후 이전 화면에 따른 뒤로가기 연결 페이지 변화 필요
        // 뒤로가기
        findIdBinding.findidFirstToolbar.toolbarWithTitleBackButton.setOnClickListener(){
            val back_intent = Intent(this,StartActivity::class.java)
            if(!isFinishing) finish()
        }

        // 전화번호 입력 여부에 따라 인증 버튼 활성화/비활성화
        editTextNum.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                if(s!=null && s.toString() != ""){
                    findIdBinding.sendVerifyButton.isEnabled = true
                } else {
                    findIdBinding.sendVerifyButton.isEnabled = false
                }
            }
        })

        // 타이머 설정
        val countDown = object : CountDownTimer(120000, 1000) {
            override fun onTick(p0: Long) {
                // countDownInterval 마다 호출 (여기선 1000ms)
                findIdBinding.timerMinute.text = (p0 / (1000*60)).toString()
                findIdBinding.timerSecond.text = ((p0 % (1000*60))/1000).toString()
            }
            override fun onFinish() {
                // 타이머가 종료되면 호출
            }
        }

        //인증 버튼 누를 경우 인증번호 입력 칸 및 버튼 표시
        findIdBinding.sendVerifyButton.setOnClickListener{
            findIdBinding.verifySentMessage.visibility = View.VISIBLE
            editTextVerify.visibility = View.VISIBLE
            findIdBinding.resendVerifyButton.visibility = View.VISIBLE
            findIdBinding.timerLayout.visibility = View.VISIBLE
            findIdBinding.resendVerifyButton.isEnabled = true
            countDown.start()
        }

        //재전송 버튼 클릭 시 제한시간 초기화 및 안내 문구
        findIdBinding.resendVerifyButton.setOnClickListener {
            Toast.makeText(this, "인증번호가 재전송되었습니다.", Toast.LENGTH_LONG).show()
            countDown.cancel()
            countDown.start()
        }

        // 이름과 인증번호 입력 완료시에만 아이디 찾기 버튼 활성화/비활성화
        editTextVerify.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                findIdBinding.findIdButton.isEnabled = false
                if((s!=null && s.toString() != "") && (editTextName.text.toString()!=null&&editTextName.text.toString()!="")){
                    findIdBinding.findIdButton.isEnabled = true
                }
            }
        })
        editTextName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                findIdBinding.findIdButton.isEnabled = false
                if((s!=null && s.toString() != "") && (editTextVerify.text.toString()!=null&&editTextVerify.text.toString()!="")){
                    findIdBinding.findIdButton.isEnabled = true
                }
            }
        })

        // 아이디 찾기 화면(프래그먼트) 추가
        findIdBinding.findIdButton.setOnClickListener{
            findIdBinding.findidMainLayout.visibility = View.INVISIBLE
            findIdBinding.findidMainLayout.isClickable = false
            findIdBinding.findidMainLayout.isFocusable = false

            // TODO : 아이디 찾기
            // VM으로 받은 결과에는 result(true/false 여부)와 id가 들어있어야함.
            // result가 true이면 id 값을 bundle로 넘기고
            // result가 false이먄 id를 찾을 수 없다고 bundle로 넘기는 로직 처리 필요
            findAccountViewModel.findAccount(editTextName.text.toString(), editTextNum.text.toString())


            val bundle = Bundle()
            bundle.putString("nameText", "${editTextName.text}")

            val findIdResultFragment = FindIdResultFragment()
            findIdResultFragment.arguments = bundle

            val transaction = supportFragmentManager.beginTransaction()
                .replace(findIdBinding.findidFrameLayout.id, findIdResultFragment)
            transaction.commit()
        }

    }
    // 로그인 액티비티로 회귀
    fun BacktoLoginActivity(){
        val login_intent = Intent(this, LoginActivity::class.java)
        if(!isFinishing) finish()
    }
}