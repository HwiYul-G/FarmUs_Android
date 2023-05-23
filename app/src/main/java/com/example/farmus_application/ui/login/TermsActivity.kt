package com.example.farmus_application.ui.login

import android.app.PendingIntent.getActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.farmus_application.databinding.ActivityTermsMainBinding

class TermsActivity : AppCompatActivity() {

    private lateinit var termsBinding: ActivityTermsMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        termsBinding = ActivityTermsMainBinding.inflate(layoutInflater)
        setContentView(termsBinding.root)

        // 로그인 화면으로 다시 이동
        termsBinding.termsMainToolbar.toolbarWithTitleBackButton.setOnClickListener(){
            BacktoLoginActivity()
        }

        //회원가입 두번째 화면(프래그먼트) 추가
        termsBinding.checkboxNextFirst.setOnClickListener{
            replaceFragment(1)
        }
        termsBinding.checkboxNextSecond.setOnClickListener{
            replaceFragment(2)
        }
        termsBinding.checkboxNextThird.setOnClickListener{
            replaceFragment(3)
        }
        termsBinding.checkboxNextFourth.setOnClickListener{
            replaceFragment(4)
        }

        // 동의 후 진행 버튼 클릭 시 회원정보 입력 및 가입 액티비티로 이동
        val Singup_intent = Intent(this, SignupActivity::class.java)
        termsBinding.toSingupActivityButton.setOnClickListener{
            startActivity(Singup_intent)
        }

        // 입력칸 관련 value 설정
        val checkAll : CheckBox = termsBinding.checkboxAll
        val checkOne : CheckBox = termsBinding.checkboxFirst
        val checkTwo : CheckBox = termsBinding.checkboxSecond
        val checkThree : CheckBox = termsBinding.checkboxThird
        val checkFour : CheckBox = termsBinding.checkboxFourth

        // 모두 동의시에만 다음으로 이어지는 버튼 클릭 가능하도록
//        termsBinding.toSingupActivityButton.isEnabled = true

        // 모두 동의시 전체 선택
        checkAll.setOnClickListener{
            checkOne.isChecked = false
            checkTwo.isChecked = false
            checkThree.isChecked = false
            checkFour.isChecked = false
            termsBinding.toSingupActivityButton.isEnabled = false
            if(checkAll.isChecked){
                checkOne.isChecked = true
                checkTwo.isChecked = true
                checkThree.isChecked = true
                checkFour.isChecked = true
                termsBinding.toSingupActivityButton.isEnabled = true
            }
        }

    }

    // 프래그먼트 변화 클래스
    fun replaceFragment(int: Int){
        termsBinding.termsMainLayout.visibility = View.INVISIBLE
        termsBinding.termsMainLayout.isClickable = false
        termsBinding.termsMainLayout.isFocusable = false
        val transaction = supportFragmentManager.beginTransaction()
        when(int){
            1 -> transaction.replace(termsBinding.termsFrameLayout.id, TermsOneFragment())
            2 -> transaction.replace(termsBinding.termsFrameLayout.id, TermsTwoFragment())
            3 -> transaction.replace(termsBinding.termsFrameLayout.id, TermsThreeFragment())
            4 -> transaction.replace(termsBinding.termsFrameLayout.id, TermsFourFragment())
        }
        transaction.commit()
    }

    fun activateMainLayout(){
        termsBinding.termsMainLayout.visibility = View.VISIBLE
        termsBinding.termsMainLayout.isClickable = true
        termsBinding.termsMainLayout.isFocusable = true
    }

    // 로그인 액티비티로 전환
    fun BacktoLoginActivity(){
        val login_intent = Intent(this, LoginActivity::class.java)
        if(!isFinishing) finish()
    }
}