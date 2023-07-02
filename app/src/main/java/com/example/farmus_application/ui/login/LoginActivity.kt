package com.example.farmus_application.ui.login

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.farmus_application.databinding.ActivityLoginMainBinding
import com.example.farmus_application.model.user.login.LoginReq
import com.example.farmus_application.ui.MainActivity
import com.example.farmus_application.ui.StartActivity
import com.example.farmus_application.viewmodel.login.LoginViewModel
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.common.model.KakaoSdkError
import com.kakao.sdk.user.UserApiClient
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {

//    private lateinit var getPwMessage: ActivityResultLauncher<Intent>

//    val getPwMessage = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
//        result : ActivityResult ->
//        if (result.resultCode == Activity.RESULT_OK) {
//            val str = result.data?.getStringExtra("PW_Resent")
//            Toast.makeText(this.applicationContext,"$str", Toast.LENGTH_SHORT).show()
//        }
//        Log.i("resentMessage","----------------------")
//    }

    private val callback : (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null){
            Log.e("Login", "카카오계정으로 로그인 실패", error)
            // 카카오톡 설치가 되어있지만, 로그인이 안 되어 있는 경우 예외처리.
            if (error.toString().contains("statusCode=302")){
                loginWithAccount()
            }
        }
        else if (token != null){
            Log.i("Login", "카카오계정으로 로그인 성공 ${token.accessToken}")
            updateStatus()
            toMainActivity()
        }
    }

    private lateinit var loginBinding: ActivityLoginMainBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        setContentView(loginBinding.root)

        loginBinding.loginStartToolbar.toolbarMainTitleText.text = "시작화면"

        // 뒤로가기
        loginBinding.loginStartToolbar.toolbarWithTitleBackButton.setOnClickListener{
            val backIntent = Intent(this,StartActivity::class.java)
//            startActivity(backIntent)
            if(!isFinishing) finish()
        }

        // 입력칸 관련 value 설정
        val editTextID : EditText = loginBinding.idTextField
        val editTextPW : EditText = loginBinding.pwTextField

        // 아이디 이메일 형식 정규식 확인 후 주의 메세지 여부
        editTextID.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                if(s!=null && s.toString() != ""){
                    val pattern: Pattern = Patterns.EMAIL_ADDRESS
                    if (!pattern.matcher(s).matches()) {
                        loginBinding.idWarningMessage.visibility = View.VISIBLE
                        loginBinding.loginButton.isEnabled = false
                    } else if (pattern.matcher(s).matches()) {
                        loginBinding.idWarningMessage.visibility = View.INVISIBLE
                        loginBinding.loginButton.isEnabled = true
                    } else if (editTextPW.text!=null && editTextPW.text.toString() != ""){
                        loginBinding.idWarningMessage.visibility = View.INVISIBLE
                        loginBinding.loginButton.isEnabled = true
                    }
                } else {
                    loginBinding.idWarningMessage.visibility = View.INVISIBLE
                }
            }
        })

        // 두 입력칸에 내용이 있을 경우에만 로그인 버튼 활성화
        editTextPW.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                if(s!=null && s.toString() != ""){
                    if(loginBinding.idWarningMessage.visibility == View.INVISIBLE){
                        loginBinding.loginButton.isEnabled = true
                    }
                } else {
                    loginBinding.loginButton.isEnabled = false
                }
            }
        })

        loginBinding.loginButton.setOnClickListener{
            val params = LoginReq(
                email = editTextID.text.toString(),
                password = editTextPW.text.toString()
            )

            viewModel.userLogin(params)
        }

        viewModel.loginResponse.observe(this, Observer {
            //todo : LoginResult의 토큰 저장 및 앱 초기실행시 토큰 검사를 통해 자동로그인 로직 작성

            val startActivity = StartActivity.getInstance()
            startActivity?.let { it.finish() }
            finish()
            toMainActivity()
        })

        viewModel.errorResponse.observe(this, Observer {
            Toast.makeText(this, it,Toast.LENGTH_LONG).show()
        })

        // 아이디 찾기 이동
        val findID_intent = Intent(this, FindidActivity::class.java)
        val findPW_intent = Intent(this, FindpwActivity::class.java)
        val signup_intent = Intent(this, TermsActivity::class.java)

        loginBinding.findIdButton.setOnClickListener{
            startActivity(findID_intent)
        }

        // 비밀번호 찾기 이동
        loginBinding.findPwButton.setOnClickListener{
            startActivity(findPW_intent)
        }

        // 회원가입 이동 (이전 액티비티에 따라서 툴바의 내용 다르게 변화)
        loginBinding.signupButton.setOnClickListener{
            startActivity(signup_intent)
        }

        // 비밀번호 찾기로 액티비티가 종료되고 로그인 액티비티로 전환되었을 때 토스트 안내
        val getPwMessage = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val resentMessage = result.data?.getStringExtra("PW_Resent")
                Log.v("resentMessage","$resentMessage")
                Toast.makeText(this, "$resentMessage", Toast.LENGTH_LONG).show()
            }
        }

        // 카카오로 로그인 + 토큰 존재 여부 확인해서 자동로그인 및 정보 갱신
        loginBinding.kakaoLoginButton.setOnClickListener {
            if (AuthApiClient.instance.hasToken()) {
                UserApiClient.instance.accessTokenInfo { _, error ->
                    if (error != null) {
                        if (error is KakaoSdkError && error.isInvalidTokenError()) {
                            //로그인 필요
                            kakaoLoginAccess()
                        }
                        else {
                            //기타 에러
                        }
                    }
                    else {
                        //토큰 유효성 체크 성공(필요 시 토큰 갱신됨) + 로그인 후 메인 액티비티로 이동동
                        Log.i("AutoLogin", "::::::::::: 기존 토큰 정보로 자동로그인")
                        updateStatus()
                        toMainActivity()
                    }
                }
            }
            else {
                //로그인 필요
                kakaoLoginAccess()
            }
        }

        // 카카오계정 로그아웃
//        loginBinding.KakaoLogout.setOnClickListener {
//            UserApiClient.instance.unlink { error ->
//                if (error != null) {
//                    Log.e("Logout", "연결 끊기 실패", error)
//                }
//                else {
//                    Log.i("Logout", "연결 끊기 성공. SDK에서 토큰 삭제 됨")
//                    updateStatus()
//                    Toast.makeText(this, "로그아웃 되었습니다", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
    }

    private fun kakaoLoginAccess(){
        // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)){
            UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                if (error != null){
                    Log.e("LoginWithKakaoTalk", "카카오톡으로 로그인 실패", error)
                    // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                    // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }
                    // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                    loginWithAccount()
                }
                else if (token != null){
                    Log.i("Login", "카카오톡으로 로그인 성공 ${token.accessToken}")
                    toMainActivity()
                }
            }
        }
        else {
            loginWithAccount()
        }
    }

    private fun loginWithAccount(){
        UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
    }

    private fun updateStatus() {
        var currentStatus = ""
        UserApiClient.instance.me { user, error1 ->
            if (error1 != null) {
                Log.e("AddressInfo", "사용자 정보 요청 실패", error1)
                currentStatus = "로그인 필요"
//                loginBinding.KakaoUserProfileImage.setImageResource(R.drawable.invisible_person2)
            } else if (user != null) {
                Log.i("AddressInfo", "사용자 정보 요청 성공")
                currentStatus = "회원번호 : ${user.id} \n이메일 : ${user.kakaoAccount?.email} \n닉네임 : ${user.kakaoAccount?.profile?.nickname}"
//                Glide.with(this).load(user.kakaoAccount?.profile?.thumbnailImageUrl).into(viewBinding.KakaoUserProfileImage)
            }
//            loginBinding.KakaoUserInfoText.text = currentStatus
//            Toast.makeText(this, "$currentStatus", Toast.LENGTH_LONG).show()
        }
    }

    private fun toMainActivity() {
        val main_intent = Intent(this, MainActivity::class.java)
        startActivity(main_intent)
    }
}
