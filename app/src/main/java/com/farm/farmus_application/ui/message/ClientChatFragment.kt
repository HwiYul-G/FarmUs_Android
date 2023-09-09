package com.farm.farmus_application.ui.message

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.PopupMenu
import androidx.recyclerview.widget.LinearLayoutManager
import com.farm.farmus_application.R
import com.farm.farmus_application.databinding.FragmentClientChatBinding
import com.farm.farmus_application.repository.UserPrefsStorage
import com.farm.farmus_application.ui.MainActivity
import com.farm.farmus_application.ui.message.adapter.ClientChatRVAdapter
import com.farm.farmus_application.utilities.JWTUtils


class ClientChatFragment : Fragment() {
    private val TAG = "ClientChatFragmentTAG"
    private var _binding: FragmentClientChatBinding? = null
    private val binding get() = _binding!!
    private val jwtToken = UserPrefsStorage.accessToken
    private val name = JWTUtils.decoded(jwtToken.toString())?.tokenBody?.name ?: ""

    // TODO : 임시 데이터. 사용 예시
    private val chatMessages = ArrayList<ChatMessage>().apply {
        // senderId 와 receivedId가 "0", "0"일 때는 날짜 업데이트를 하게 구현되어있음(rvAdapter와 함께 봐야함)
        // 기능 구현시 그 부분을 고려해서 수정필요.
//        add(ChatMessage("0", "0", "2023년 01월 08일", "00:00"))

        // 일반 대화 임시 데이터
        add(ChatMessage(name, "내가 보냄", "오후 2:16"))
        add(ChatMessage("kong", "상대방이 보냄", "오후 3:10"))
    }
    private lateinit var clientChatRVAdapter: ClientChatRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClientChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.hideBottomNavigation(true)
        init()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as? MainActivity)?.hideBottomNavigation(false)
        _binding = null
    }

    private fun init() {
        initRecyclerView()
        initBackButton()
        initMoreButton()
        initBottomSheet()
        // TODO: 추가할 초기화가 있다면 초기화
    }

    private fun initRecyclerView() {
        // TODO : sender(현 사용자)의 id를 잘 전달해야함
        clientChatRVAdapter = ClientChatRVAdapter(chatMessages)
        binding.commonChatLayout.rvChat.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = clientChatRVAdapter

            // editText 가 눌리고 keyboard가 보이고 나서 recyclerview의 부분을 클릭하면 hide keyboard하기
            setOnTouchListener { v, event ->
                if (event.action == MotionEvent.ACTION_UP) {
                    v.performClick()
                    hideKeyboard()
                }
                false
            }

        }
    }

    private fun initBackButton() {
        binding.toolbarLayout.toolbarWithTitleAndMoreBackButton.setOnClickListener {
            hideKeyboard()
            
            // keyboard를 숨긴 후에 back되도록 (keyboard hide가 안된 것이 이전 화면에 영향을 줌)
            it.postDelayed({
                activity?.supportFragmentManager?.apply {
                    beginTransaction().remove(this@ClientChatFragment).commit()
                    popBackStack()
                }  
            }, 100) // 100초 뒤에 처리됨
        }
    }

    private fun initMoreButton() {
        binding.toolbarLayout.toolbarWithTitleAndMoreMoreButton.setOnClickListener {
            val popup = PopupMenu(context, it)
            popup.menuInflater.inflate(R.menu.top_more_menu, popup.menu)
            // TODO: item click 리스너 구현 위치
            popup.show()
        }
    }

    private fun initBottomSheet() {
        binding.clientFarmInfo.clientChatHistory.setOnClickListener {
            val bottomSheetFragment = ChatBottomSheetFragment()
            bottomSheetFragment.show(childFragmentManager, bottomSheetFragment.tag)
        }
    }

    private fun hideKeyboard() {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(view?.windowToken, 0)
    }

}