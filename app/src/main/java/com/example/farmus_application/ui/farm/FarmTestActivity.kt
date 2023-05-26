package com.example.farmus_application.ui.farm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.farmus_application.R
import com.example.farmus_application.databinding.ActivityFarmTestBinding

class FarmTestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFarmTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_farm_test)

        binding.TestButton.setOnClickListener {
            // 여기서 등록 프래그먼트로 이동해서 layout확인
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.frameView, SecondFarmRegistrationFragment())
//                .commit()
            val bottomsheet = RequestBottomSheetDialog()
            bottomsheet.show(supportFragmentManager, bottomsheet.tag)

        }
        // 캘린더의 화살표 위치를 날짜 양옆으로 붙이기 -> 가능한가? -> 일단 setLeftArrow를 통해 drawable 설정
        // 클릭시 나타나는 drawable의 크기 조절 -> 가능한가? -> verctor이미지 자체에 여백이 있는 이미지가 필요할듯
    }
}