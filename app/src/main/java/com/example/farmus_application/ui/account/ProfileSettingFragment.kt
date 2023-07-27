package com.example.farmus_application.ui.account

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.farmus_application.R
import com.example.farmus_application.utilities.ValidationCheckUtil
import com.example.farmus_application.databinding.FragmentProfileSettingBinding
import com.example.farmus_application.model.mypage.EditInfoNameReq
import com.example.farmus_application.model.mypage.EditInfoNicknameReq
import com.example.farmus_application.model.mypage.EditInfoPasswordReq
import com.example.farmus_application.model.mypage.EditInfoPhoneNumberReq
import com.example.farmus_application.repository.UserPrefsStorage
import com.example.farmus_application.ui.MainActivity
import com.example.farmus_application.utilities.JWTUtils
import com.example.farmus_application.viewmodel.account.ProfileSettingViewModel


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ProfileSettingFragment : Fragment() {

    private lateinit var binding: FragmentProfileSettingBinding
    private val tokenBody = JWTUtils.decoded(UserPrefsStorage.accessToken.toString())!!.tokenBody
    private val profileSettingViewModel: ProfileSettingViewModel by viewModels()

    //뒤로가기 기능 구현
    private lateinit var callback: OnBackPressedCallback

    private var param1: String? = null
    private var param2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProfileSettingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //BottomNavigationView 숨기기
        (activity as MainActivity).hideBottomNavigation(true)

        binding.btnProfileImageSetting.bringToFront()

        //toopbar 텍스트 설정
        binding.toolBar.toolbarMainTitleText.text = "프로필 수정"
        //툴바 백터튼 누르면 MyPageFragment로 이동
        binding.toolBar.toolbarWithTitleBackButton.setOnClickListener {
            (activity as MainActivity).changeFragment(MyPageFragment.newInstance("", ""))
        }

        val userEmail = tokenBody.email
        val userNickname = tokenBody.nickname
        val userName = tokenBody.name
        val userPhoneNumber = tokenBody.phoneNumber

        val fragment = ProfileSettingValidationDialogFragment()
        val bundle = Bundle()

        // 1) 닉네임 + 편집 불가
        binding.edittextNickname.isEnabled = false
        binding.edittextNickname.setText(userNickname)
        // nickname 변경 or 완료 버튼 클릭 이벤트
        binding.btnChangeNickname.setOnClickListener {
            // 변경 버튼 클릭시 편집 가능 + 버튼 text '완료'로 변경 + focus
            if (binding.btnChangeNickname.text == "변경") {
                binding.edittextNickname.isEnabled = true
                binding.btnChangeNickname.text = "완료"
                // edittextNickname에 foucs + 색상 변경
                binding.edittextNickname.requestFocus()
                if (userNickname != "") {
                    binding.edittextNickname.setSelection(binding.edittextNickname.text!!.length) // 커서 위치를 마지막 글자 뒤로 이동
                }
                binding.edittextNickname.background = ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.longer_text_field__focus_vector_image,
                    null
                )
            } else {
                // 완료 버튼 클릭시
                // 기존의 닉네임과 동일한 경우
                if (binding.edittextNickname.text.toString() == userNickname) {
                    binding.edittextNickname.isEnabled = false
                    binding.btnChangeNickname.text = "변경"
                    binding.edittextNickname.background = ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.longer_text_field__disabled_vector_image,
                        null
                    )
                } else {
                    // 변경된 닉네임 유효성 검사
                    when (ValidationCheckUtil.checkNickname(binding.edittextNickname.text.toString())) {
                        ValidationCheckUtil.NicknameStatus.SPACE -> {
                            bundle.putString("errorMessage", "공백은 포함할 수 없습니다.")
                            fragment.arguments = bundle
                            fragment.show(
                                parentFragmentManager,
                                "profile_setting_validation_dialog"
                            )
                            // 기존의 닉네임이 편집가능 상태임
                        }
                        ValidationCheckUtil.NicknameStatus.SPECIAL_CHARACTERS -> {
                            // dialog로 안내
                            bundle.putString("errorMessage", "특수문자 및 공백은 포함할 수 없습니다.")
                            fragment.arguments = bundle
                            fragment.show(
                                parentFragmentManager,
                                "profile_setting_validation_dialog"
                            )
                        }
                        ValidationCheckUtil.NicknameStatus.BAD_WORDS -> {
                            bundle.putString("errorMessage", "비속어는 포함할 수 없습니다.")
                            fragment.arguments = bundle
                            fragment.show(
                                parentFragmentManager,
                                "profile_setting_validation_dialog"
                            )
                        }
                        ValidationCheckUtil.NicknameStatus.VALID -> {
                            // 유효성 검사 통과
                            // UserPrefsStorage의 nickname에 바뀐 것 저장 + VM의 API 처리v
                            // ViewModel을 통해 API 처리 -> observe data에서 다음 로직 진행
                            val newNickname = binding.edittextNickname.text.toString()
                            profileSettingViewModel.patchEditInfoNickname(
                                userEmail,
                                EditInfoNicknameReq(newNickname)
                            )
                        }
                    }
                }
            }
        }
        // nickname 변경 observe
        profileSettingViewModel.editInfoNicknameResponse.observe(viewLifecycleOwner) { response ->
            if (response) {
                // 편집 불가 + 버튼 text '변경'으로 변경 + 색상 변경
                binding.edittextNickname.isEnabled = false
                binding.btnChangeNickname.text = "변경"
                binding.edittextNickname.background = ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.longer_text_field__disabled_vector_image,
                    null
                )
                // 성공 안내 toast msg
                val toast = Toast.makeText(context, "닉네임이 변경되었습니다.", Toast.LENGTH_SHORT)
                toast.show()
            } else {
                // 실패
                // 토스트 메시지 띄우기
                val toast = Toast.makeText(context, "닉네임 변경에 실패했습니다.", Toast.LENGTH_SHORT)
                toast.show()
            }
        }

        // 2) id(email)은 원래 변경 불가 상태 + 사용자 이메일을 가져다 넣음
        binding.edittextId.setText(userEmail)

        // 3) 이름
        binding.edittextName.isEnabled = false
        //binding.edittextName.setText(UserPrefsStorage.name)
        binding.btnChangeName.setOnClickListener {
            if (binding.btnChangeName.text == "변경") {
                binding.edittextName.isEnabled = true
                binding.btnChangeName.text = "완료"
                binding.edittextName.requestFocus()
                binding.edittextName.setSelection(binding.edittextName.text!!.length)
                binding.edittextName.background = ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.longer_text_field__focus_vector_image,
                    null
                )
            } else {
                if (binding.edittextName.text.toString() == userName) {
                    binding.edittextName.isEnabled = false
                    binding.btnChangeName.text = "변경"
                    binding.edittextName.background = ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.longer_text_field__disabled_vector_image,
                        null
                    )
                } else {
                    // 이름 유효성 검사
                    when (ValidationCheckUtil.checkName(binding.edittextName.text.toString())) {
                        ValidationCheckUtil.NameStatus.SPECIAL_CHAR -> {
                            bundle.putString("errorMessage", "특수문자 및 공백은 포함할 수 없습니다.")
                            fragment.arguments = bundle
                            fragment.show(
                                parentFragmentManager,
                                "profile_setting_validation_dialog"
                            )
                        }
                        ValidationCheckUtil.NameStatus.BAD_WORDS -> {
                            bundle.putString("errorMessage", "비속어는 포함할 수 없습니다.")
                            fragment.arguments = bundle
                            fragment.show(
                                parentFragmentManager,
                                "profile_setting_validation_dialog"
                            )
                        }
                        ValidationCheckUtil.NameStatus.LIMITED_LENGTH -> {
                            bundle.putString("errorMessage", "이름은 3자 이상 20자이하 여야 합니다.")
                            fragment.arguments = bundle
                            fragment.show(
                                parentFragmentManager,
                                "profile_setting_validation_dialog"
                            )
                        }
                        ValidationCheckUtil.NameStatus.VALID -> {
                            val newName = binding.edittextName.text.toString()
                            // viewModel을 통해 API 처리 -> observe data에서 다음 로직 진행
                            profileSettingViewModel.patchEditInfoName(
                                userEmail,
                                EditInfoNameReq(newName)
                            )
                        }
                    }
                }
            }
        }
        // 이름 변경 observe
        profileSettingViewModel.editInfoNameResponse.observe(viewLifecycleOwner) { response ->
            if (response) {
                //UserPrefsStorage.name = binding.edittextName.text.toString()
                binding.edittextName.isEnabled = false
                binding.btnChangeName.text = "변경"
                binding.edittextName.background = ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.longer_text_field__disabled_vector_image,
                    null
                )
                // 성공 안내 toast
                val toast = Toast.makeText(context, "이름이 변경되었습니다.", Toast.LENGTH_SHORT)
                toast.show()
            } else {
                // 실패
                // 토스트 메시지 띄우기
                val toast = Toast.makeText(context, "이름 변경에 실패했습니다.", Toast.LENGTH_SHORT)
                toast.show()
            }
        }

        // 4) 비밀번호
        // TODO : login시 비밀번호 정보를 따로 담아두는 곳이 없음. 임시로 * 6개 표시
        binding.edittextPassword.setText("******")
        binding.edittextPassword.isEnabled = false
        binding.btnChangePassword.setOnClickListener {
            if (binding.btnChangePassword.text == "변경") {
                binding.edittextPassword.isEnabled = true
                binding.btnChangePassword.text = "완료"
                binding.edittextPassword.requestFocus()
                // TODO : 커서 위치 처리
                val passwordText = binding.edittextPassword.text.toString()
                val cursorPosition = if (passwordText.isEmpty()) 6 else passwordText.length
                binding.edittextPassword.setSelection(cursorPosition)
                binding.edittextPassword.background = ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.longer_text_field__focus_vector_image,
                    null
                )
            } else {
                // 변경 후 완료 버튼 클릭시
                // TODO : 비밀번호에 대한 정보가 없어서 기존 것과 동일한지 확인 불가능
                if (ValidationCheckUtil.checkPassword(binding.edittextPassword.text.toString())) {
                    // viewModel을 통해 API 처리 -> observe data에서 다음 로직 진행
                    profileSettingViewModel.patchEditInfoPassword(
                        userEmail,
                        EditInfoPasswordReq(binding.edittextPassword.text.toString())
                    )
                } else {
                    bundle.putString("errorMessage", "비밀번호는 8자 이상 20자 이하의 영문, 숫자, 특수문자 조합이어야 합니다.")
                    fragment.arguments = bundle
                    fragment.show(parentFragmentManager, "profile_setting_validation_dialog")
                }
            }
        }
        // 비밀번호 변경 observe
        profileSettingViewModel.editInfoPasswordResponse.observe(viewLifecycleOwner) { response ->
            if (response) {
                // TODO : UserPrefStorage에서 password 저장
                binding.edittextPassword.isEnabled = false
                binding.btnChangePassword.text = "변경"
                binding.edittextPassword.background = ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.longer_text_field__disabled_vector_image,
                    null
                )
                val toast = Toast.makeText(context, "비밀번호가 변경되었습니다.", Toast.LENGTH_SHORT)
                toast.show()
            } else {
                // 실패
                val toast = Toast.makeText(context, "비밀번호 변경에 실패했습니다.", Toast.LENGTH_SHORT)
                toast.show()
            }
        }

        // 5) 전화번호
        binding.edittextPhoneNumber.setText(userPhoneNumber)
        binding.edittextPhoneNumber.isEnabled = false
        binding.btnChangePhoneNumber.setOnClickListener {
            if (binding.btnChangePhoneNumber.text == "변경") {
                binding.edittextPhoneNumber.isEnabled = true
                binding.btnChangePhoneNumber.text = "완료"
                binding.edittextPhoneNumber.requestFocus()
                binding.edittextPhoneNumber.background = ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.longer_text_field__focus_vector_image,
                    null
                )
            } else {
                // 완료 버튼 클릭
                if (ValidationCheckUtil.isPhoneNumberValid(binding.edittextPhoneNumber.text.toString())) {
                    val newPhoneNumber: String = binding.edittextPhoneNumber.text.toString()
                    // viewModel을 통해 API 처리 -> observe data에서 다음 로직 진행
                    profileSettingViewModel.patchEditInfoPhoneNumber(
                        userEmail,
                        EditInfoPhoneNumberReq(newPhoneNumber)
                    )
                } else {
                    bundle.putString("errorMessage", "전화번호 형식이 올바르지 않습니다.")
                    fragment.arguments = bundle
                    fragment.show(parentFragmentManager, "profile_setting_validation_dialog")
                }
            }
        }
        profileSettingViewModel.editInfoPhoneNumberResponse.observe(viewLifecycleOwner) { response ->
            if (response) {
                // TODO : UserPrefStorage에서 phoneNumber 저장
                binding.edittextPhoneNumber.isEnabled = false
                binding.btnChangePhoneNumber.text = "변경"
                binding.edittextPhoneNumber.background = ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.longer_text_field__disabled_vector_image,
                    null
                )
                val toast = Toast.makeText(context, "전화번호가 변경되었습니다.", Toast.LENGTH_SHORT)
                toast.show()
            } else {
                // 실패
                val toast = Toast.makeText(context, "전화번호 변경에 실패했습니다.", Toast.LENGTH_SHORT)
                toast.show()
            }
        }

        // 6) 프로필 이미지
        binding.btnProfileImageSetting.setOnClickListener {
            val galleryIntent =
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            changeProfileImage.launch(galleryIntent)
        }

        profileSettingViewModel.editInfoProfileImageResponse.observe(viewLifecycleOwner) { response ->
            if (response.result) {
                // Glide로 img update
                Glide.with(this).load(response.photoUrl).into(binding.profileImage)
                val toast = Toast.makeText(context, "프로필 이미지가 변경되었습니다.", Toast.LENGTH_SHORT)
                toast.show()
            } else {
                // 실패
                val toast = Toast.makeText(context, "프로필 이미지 변경에 실패했습니다.", Toast.LENGTH_SHORT)
                toast.show()
            }
        }


    }

    private val changeProfileImage = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            val data = it.data
            val selectedImageUri = data?.data
            // 임시적으로 이미지가 변경된 척하는 코드
            binding.profileImage.setImageURI(selectedImageUri)

            val bitmap = getBitmapFromUri(selectedImageUri!!)
            profileSettingViewModel.patchEditInfoProfileImg(
                tokenBody.email,
                bitmap!!
            )
        }
    }

    private fun getBitmapFromUri(uri: Uri): Bitmap? {
        val context = context ?: return null
        if (Build.VERSION.SDK_INT >= 28) {
            val src = ImageDecoder.createSource(context.contentResolver, uri)
            return ImageDecoder.decodeBitmap(src)
        } else {
            return MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
        }
    }


    //뒤로가기 누르면 MyPageFragment로 이동
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                (activity as MainActivity).changeFragment(MyPageFragment.newInstance("", ""))
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    //뒤로가기 누르면 MyPageFragment로 이동
    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileSettingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}