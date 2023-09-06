package com.farm.farmus_application.ui.farm

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.farm.farmus_application.R
import com.farm.farmus_application.databinding.FragmentManagementCalendarBinding
import com.farm.farmus_application.model.reserve.unbookable.UnBookableResult
import com.farm.farmus_application.viewmodel.calendar.CalendarViewModel
import com.prolificinteractive.materialcalendarview.CalendarDay
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ManagementCalendarFragment: Fragment() {

    private lateinit var binding: FragmentManagementCalendarBinding
    private lateinit var firstSelectedDay: CalendarDay
    private lateinit var lastSelectedDay: CalendarDay
    private var farmId = 0
    private var unBookDayList: List<UnBookableResult> = listOf()
    private val calendarViewModel: CalendarViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_management_calendar, container, false)
        farmId = arguments?.getInt("farmId") ?: 0
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        binding.managementCalendarBack.setOnClickListener {
            activity?.supportFragmentManager?.apply {
                beginTransaction().remove(this@ManagementCalendarFragment).commit()
                popBackStack()
            }
        }

        calendarViewModel.unBookable.observe(viewLifecycleOwner) { result ->
            if (result.isSuccess) {
                result.result?.let {
                    binding.managementCalendar.addDecorator(UnBookableDayDecorator(it))
                    unBookDayList = it
                    Log.e("unBookDayList","$unBookDayList")
                }
            } else {
                Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
            }
        }

        binding.managementCalendar.setOnDateChangedListener { widget, date, selected ->
            widget.apply {
                removeDecorators()
                addDecorator(SelectedDecorator(date))
                addDecorators(TodayDecorator(requireContext()), BeforeDayDecorator(),SelectedDecorator(date))
            }
            settingCalendarStartText(date)
            switchDateFocus(true)
        }

        binding.managementCalendar.setOnRangeSelectedListener { widget, dates ->
            val dayList = mutableListOf<CalendarDay>().apply {
                addAll(dates)
                removeAt(0)
                removeAt(this.size -1)
            }
            if (firstSelectedDay != dates[0]) {
                widget.addDecorator(SelectedDecorator(dates[0]))
                settingCalendarStartText(dates[0])
            } else {
                widget.addDecorator(SelectedDecorator(dates[dates.size -1]))
            }
            if (dayList.size != 0) {
                widget.addDecorator(RangeDayDecorator(dayList, requireContext()))
            }
            settingCalendarLastText(dates[dates.size-1])
            switchDateFocus(false)
        }
    }

    private fun initView() {
        val date = CalendarDay.today()
        settingCalendarStartText(date)
        settingCalendarLastText(date)
        calendarViewModel.getReserveUnBookable(farmId.toString())
        binding.managementCalendar.apply {
            addDecorator(TodayDecorator(requireContext()))
            addDecorator(BeforeDayDecorator())
            setTitleFormatter { day -> "${day.month}월${day.year}년" }
            setLeftArrow(R.drawable.calendar_back_button_vector)
            setRightArrow(R.drawable.calendar_forward_button_vector)
        }
    }

    private fun settingCalendarStartText(date: CalendarDay) {
        binding.apply {
            calendarStartDayYear.text = date.year.toString()
            calendarStartDayMonth.text = String.format("%02d",date.month)
            calendarStartDayDate.text = date.day.toString()
        }
        firstSelectedDay = date
    }
    private fun settingCalendarLastText(date: CalendarDay) {
        binding.apply {
            calendarLastDayYear.text = date.year.toString()
            calendarLastDayMonth.text = String.format("%02d",date.month)
            calendarLastDayDate.text = date.day.toString()
        }
        lastSelectedDay = date
    }
    private fun switchDateFocus(switch: Boolean) {
        if (switch) {
            binding.apply {
                startDayConstraint.isSelected = true
                lastDayConstraint.isSelected = false
            }
        } else {
            binding.apply {
                startDayConstraint.isSelected = false
                lastDayConstraint.isSelected = true
            }
        }
    }
}
