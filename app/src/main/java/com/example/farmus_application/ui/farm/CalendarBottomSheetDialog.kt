package com.example.farmus_application.ui.farm

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.farmus_application.R
import com.example.farmus_application.databinding.DialogBottomSheetCalendarBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade

class CalendarBottomSheetDialog: BottomSheetDialogFragment() {

    private lateinit var bottomSheetDialogBinding: DialogBottomSheetCalendarBinding
    private lateinit var firstSelectedDay: CalendarDay

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        bottomSheetDialogBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_bottom_sheet_calendar, container, false)
        val view = bottomSheetDialogBinding

        initCalendarDialog()

        view.bottomSheetCalendar.setOnDateChangedListener { widget, date, _ ->
            Log.e("setOnDateChangedListener","$date!")
            firstSelectedDay = date
            widget.removeDecorators()
            widget.addDecorator(SelectedDecorator(date))
            widget.addDecorators(TodayDecorator(requireContext()), BeforeDayDecorator())
            settingCalendarStartText(date)
            settingButtonSelect(false)
        }

        view.bottomSheetCalendar.setOnRangeSelectedListener { widget, dates ->
            Log.e("ErrorRange","${dates}")
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
            settingButtonSelect(true)
        }

        return view.root
    }

    private fun initCalendarDialog() {
        val today = CalendarDay.today()
        settingCalendarStartText(today)
        settingCalendarLastText(today)
        bottomSheetDialogBinding.bottomSheetCalendar.apply {
            addDecorator(TodayDecorator(requireContext()))
            addDecorator(BeforeDayDecorator())
            setTitleFormatter { day -> "${day.month}월${day.year}년" }
            setLeftArrow(R.drawable.calendar_back_button_vector)
            setRightArrow(R.drawable.calendar_forward_button_vector)
        }
    }

    private fun settingCalendarStartText(date: CalendarDay) {
        bottomSheetDialogBinding.apply {
            calendarStartDayYear.text = date.year.toString()
            calendarStartDayMonth.text = String.format("%02d",date.month)
            calendarStartDayDate.text = date.day.toString()
        }
    }

    private fun settingCalendarLastText(date: CalendarDay) {
        bottomSheetDialogBinding.apply {
            calendarLastDayYear.text = date.year.toString()
            calendarLastDayMonth.text = String.format("%02d",date.month)
            calendarLastDayDate.text = date.day.toString()
        }
    }

    private fun settingButtonSelect(selected: Boolean) {
        if (selected) {
            bottomSheetDialogBinding.apply {
                startDayConstraint.isSelected = false
                lastDayConstraint.isSelected = true
                applicationButton.isEnabled = true
            }
        } else {
            bottomSheetDialogBinding.apply {
                startDayConstraint.isSelected = true
                lastDayConstraint.isSelected = false
                applicationButton.isEnabled = false
            }
        }
    }

}

class TodayDecorator(context: Context) : DayViewDecorator {

    private var drawable: Drawable = ContextCompat.getDrawable(context, R.drawable.calendar_today_background)!!

    override fun shouldDecorate(day: CalendarDay): Boolean {
        return day == CalendarDay.today()
    }

    override fun decorate(view: DayViewFacade?) {
        view?.setSelectionDrawable(drawable)
        view?.setDaysDisabled(true)
        view?.let {
            it.setSelectionDrawable(drawable)
            it.setDaysDisabled(true)
        }
    }
}

class BeforeDayDecorator: DayViewDecorator {

    override fun shouldDecorate(day: CalendarDay?): Boolean {
        return day?.isBefore(CalendarDay.today()) ?: false
    }

    override fun decorate(view: DayViewFacade?) {
        view?.let {
            it.addSpan(ForegroundColorSpan(Color.parseColor("#cccccc")))
            it.setDaysDisabled(true)
        }
    }
}

class RangeDayDecorator(
    private val days: List<CalendarDay>,
    context: Context
): DayViewDecorator {
    private var drawable: Drawable = ContextCompat.getDrawable(context, R.drawable.calendar_range_background)!!

    override fun shouldDecorate(day: CalendarDay?): Boolean {
        return days.contains(day)
    }

    override fun decorate(view: DayViewFacade?) {
        view?.setSelectionDrawable(drawable)
        view?.setDaysDisabled(true)
    }
}

class SelectedDecorator(private val selectedDay: CalendarDay): DayViewDecorator {
    override fun shouldDecorate(day: CalendarDay?): Boolean {
        return day == selectedDay
    }

    override fun decorate(view: DayViewFacade?) {
        view?.addSpan(ForegroundColorSpan(Color.parseColor("#ffffff")))
    }
}



