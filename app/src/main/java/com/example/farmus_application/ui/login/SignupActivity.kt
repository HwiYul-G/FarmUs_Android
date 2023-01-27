package com.example.farmus_application.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.farmus_application.databinding.ActivityMainBinding
import com.example.farmus_application.databinding.ActivitySignupFirstBinding
import com.example.farmus_application.ui.StartActivity
import java.util.regex.Pattern

class SignupActivity : AppCompatActivity() {

    private lateinit var signupBinding: ActivitySignupFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signupBinding = ActivitySignupFirstBinding.inflate(layoutInflater)
        setContentView(signupBinding.root)

        signupBinding.signupIdToolbar.toolbarMainTitleText.text = "시작화면"

        // 시작화면으로 다시 이동
        signupBinding.signupIdToolbar.toolbarWithTitleBackButton.setOnClickListener(){
            BacktoStartActivity()
        }

        // 입력칸 관련 value 설정
        val editTextID : EditText = signupBinding.idTextField

        // 아이디 이메일 형식 정규식 확인 후 주의 메세지 여부 및 다음 버튼 활성화 여부
        editTextID.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                if(s!=null && s.toString() != ""){
                    val pattern: Pattern = Patterns.EMAIL_ADDRESS
                    if (!pattern.matcher(s).matches()) {
                        signupBinding.idWarningMessage.visibility = View.VISIBLE
                        signupBinding.toSecondSignupButton.isEnabled = false
                    } else {
                        signupBinding.idWarningMessage.visibility = View.INVISIBLE
                        signupBinding.toSecondSignupButton.isEnabled = true
                    }
                } else {
                    signupBinding.idWarningMessage.visibility = View.INVISIBLE
                    signupBinding.toSecondSignupButton.isEnabled = false
                }
            }
        })

        //회원가입 두번째 화면(프래그먼트) 추가
        signupBinding.toSecondSignupButton.setOnClickListener{
            signupBinding.signupMainLayout.visibility = View.INVISIBLE
            signupBinding.signupMainLayout.isClickable = false
            signupBinding.signupMainLayout.isFocusable = false

            val bundle = Bundle()
            bundle.putString("idText", "${editTextID.text}")

            val S2Fragment = SignupSecondFragment()
            S2Fragment.arguments = bundle

            val transaction = supportFragmentManager.beginTransaction()
                .replace(signupBinding.signupFrameLayout.id, S2Fragment)
            transaction.commit()

//            supportFragmentManager
//                .beginTransaction()
//                .replace(signupBinding.signupFrameLayout.id, S2Fragment)
//                .commitAllowingStateLoss()
        }

//        changeFrame(signupBinding, HomeFragment.newInstance("","")) //Initialize Frame
//        signupBinding.selectFragmentBottomNavi.run {
//            setOnItemSelectedListener {
//                val switchFragment = when(it.itemId){
//                    R.id.menu_favorites -> FavoriteFragment.newInstance("", "")
//                    R.id.menu_farm -> FarmFragment.newInstance("", "")
//                    R.id.menu_chat -> ChatFragment.newInstance("", "")
//                    R.id.menu_my_page -> MyPageFragment.newInstance("", "")
//                    else -> HomeFragment.newInstance("","")
//                }
//                changeFrame(mainBinding, switchFragment)
//                true
//            }
//        }

    }
    // 프래그먼트 변화 클래스
    fun replaceFragment(int: Int){
        val transaction = supportFragmentManager.beginTransaction()
        when(int){
            2 -> transaction.replace(signupBinding.signupFrameLayout.id, SignupSecondFragment())
            3 -> transaction.replace(signupBinding.signupFrameLayout.id, SignupThirdFragment())
            4 -> transaction.replace(signupBinding.signupFrameLayout.id, SignupFourthFragment())
            5 -> transaction.replace(signupBinding.signupFrameLayout.id, SignupFifthFragment())
        }
        transaction.commit()
    }

    fun BacktoStartActivity(){
        val login_intent = Intent(this, LoginActivity::class.java)
        if(!isFinishing) finish()
    }
}