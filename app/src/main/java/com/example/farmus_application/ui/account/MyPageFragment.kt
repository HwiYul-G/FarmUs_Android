package com.example.farmus_application.ui.account
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.farmus_application.R
import com.example.farmus_application.databinding.FragmentMyPageBinding
import com.example.farmus_application.repository.UserPrefsStorage
import com.example.farmus_application.ui.MainActivity
import com.example.farmus_application.utilities.JWTUtils


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MyPageFragment : Fragment() {

    private lateinit var myPageBinding: FragmentMyPageBinding
    private val tokenBody = JWTUtils.decoded(UserPrefsStorage.accessToken.toString())!!.tokenBody

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
    ): View? {
        // Inflate the layout for this fragment
        myPageBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_page, container, false)
        val view = myPageBinding

        //bottomNavigationView 보이게
        (activity as MainActivity).hideBottomNavigation(false)

        //프로필 수정 버튼 누르면 ProfileSettingFragment로 이동
        view.btnProfileSetting.setOnClickListener{
            (activity as MainActivity).changeFragment(ProfileSettingFragment.newInstance("",""))
        }
        //설정 버튼 누르면 MyPageSettingFragment로 이동
        view.btnSetting.setOnClickListener {
            (activity as MainActivity).changeFragment(AccountSettingFragment.newInstance("",""))
        }

        view.listEnrollFarmer.setOnClickListener {
            (activity as MainActivity).changeFragmentAddToBackStack(EnrollFarmerFragment.newInstance("",""))
        }

        view.profileName.text = tokenBody.name
        view.profileMail.text = tokenBody.email
        if(tokenBody.profile != null)
            Glide.with(this).load(tokenBody.profile).into(view.myPageProfileImage)


        return view.root
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MyPageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}