package com.example.farmus_application.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import com.example.farmus_application.R
import com.example.farmus_application.databinding.ActivityTermsMainBinding

class TermsActivity : AppCompatActivity() {

    private lateinit var termsBinding: ActivityTermsMainBinding

    lateinit var checkAll : CheckBox
    lateinit var checkOne : CheckBox
    lateinit var checkTwo : CheckBox
    lateinit var checkThree : CheckBox
    lateinit var checkFour : CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        termsBinding = ActivityTermsMainBinding.inflate(layoutInflater)
        setContentView(termsBinding.root)

        // 로그인 화면으로 다시 이동
        termsBinding.termsMainToolbar.toolbarWithoutTitleBackButton.setOnClickListener(){
            backToLoginActivity()
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
            finish()
        }

        // 이용약관 클릭 및 데이터 묶음 처리
        checkAll = termsBinding.checkboxAll
        checkOne = termsBinding.checkboxFirst
        checkTwo = termsBinding.checkboxSecond
        checkThree = termsBinding.checkboxThird
        checkFour = termsBinding.checkboxFourth

//        checkAll = ArrayList<TermsData>

        checkAll.setOnClickListener { onCheckChanged(checkAll) }
        checkOne.setOnClickListener { onCheckChanged(checkOne) }
        checkTwo.setOnClickListener { onCheckChanged(checkTwo) }
        checkThree.setOnClickListener { onCheckChanged(checkThree) }
        checkFour.setOnClickListener { onCheckChanged(checkFour) }
    }

    // 프래그먼트 변화 클래스
    fun replaceFragment(int: Int){
        termsBinding.termsMainLayout.visibility = View.INVISIBLE
        termsBinding.termsMainLayout.isClickable = false
        termsBinding.termsMainLayout.isFocusable = false
        val transaction = supportFragmentManager.beginTransaction()
        when(int){
            1 -> transaction.replace(termsBinding.termsFrameLayout.id, TermsOneFragment(),)
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
    fun backToLoginActivity(){
//        val login_intent = Intent(this, LoginActivity::class.java)
        if(!isFinishing) finish()
    }

    fun rememberChecked(check: Boolean, int: Int){
        if(check){
            when(int){
                1 -> checkOne.isChecked = true
                2 -> checkTwo.isChecked = true
                3 -> checkThree.isChecked = true
                4 -> checkFour.isChecked = true
            }
        } else {
            when(int){
                1 -> checkOne.isChecked = false
                2 -> checkTwo.isChecked = false
                3 -> checkThree.isChecked = false
                4 -> checkFour.isChecked = false
            }
        }
//        onCheckChanged(checkAll)
    }

    // 이용약관 동의 여부에 따른 다음 전환 버튼 활성화 여부
    private fun onCheckChanged(CheckBox: CheckBox) {
        when(CheckBox.id) {
            R.id.checkbox_all -> {
                if (checkAll.isChecked) {
                    checkOne.isChecked = true
                    checkTwo.isChecked = true
                    checkThree.isChecked = true
                    checkFour.isChecked = true
                    termsBinding.toSingupActivityButton.isEnabled = true
                }else {
                    checkOne.isChecked = false
                    checkTwo.isChecked = false
                    checkThree.isChecked = false
                    checkFour.isChecked = false
                    termsBinding.toSingupActivityButton.isEnabled = false
                }
            }
            else -> {
                checkAll.isChecked = (checkOne.isChecked && checkTwo.isChecked && checkThree.isChecked && checkFour.isChecked)
                termsBinding.toSingupActivityButton.isEnabled = false
                if (checkAll.isChecked){
                    termsBinding.toSingupActivityButton.isEnabled = true
                }
            }
        }
    }
}