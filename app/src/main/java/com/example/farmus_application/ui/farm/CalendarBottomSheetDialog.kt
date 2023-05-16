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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        bottomSheetDialogBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_bottom_sheet_calendar, container, false)
        val view = bottomSheetDialogBinding

        view.bottomSheetCalendar.addDecorator(TodayDecorator(requireContext()))
        view.bottomSheetCalendar.addDecorator(BeforeDayDecorator())
        view.bottomSheetCalendar.setTitleFormatter { day -> "${day.month}월${day.year}년" }
        view.bottomSheetCalendar.setLeftArrow(R.drawable.calendar_back_button_vector)
        view.bottomSheetCalendar.setRightArrow(R.drawable.calendar_forward_button_vector)

        settingDateTextView()


        view.bottomSheetCalendar.setOnDateChangedListener { widget, date, _ ->
            Log.e("setOnDateChangedListener","DateChangedClick!")
            widget.removeDecorators()
            widget.addDecorator(FirstDayDecorator(date))
            widget.addDecorators(TodayDecorator(requireContext()), BeforeDayDecorator())
            view.calendarStartDayYear.text = date.year.toString()
            view.calendarStartDayMonth.text = String.format("%02d",date.month)
            view.calendarStartDayDate.text = date.day.toString()
            // month가 5월이면 05가 아닌 5로 나옴 -> 0을 붙혓 나오게 하던지 아니면 date변수의 string을 잘라서 사용
            view.startDayConstraint.isSelected = true
            view.lastDayConstraint.isSelected = false
            view.applicationButton.isEnabled = false
        }

        view.bottomSheetCalendar.setOnRangeSelectedListener { widget, dates ->
            val dayList = mutableListOf<CalendarDay>().apply {
                addAll(dates)
                removeAt(0)
                removeAt(this.size -1)
            }
            widget.addDecorator(LastDayDecorator(dates[dates.size -1]))
            if (dayList.size != 0) {
                widget.addDecorator(RangeDayDecorator(dayList, requireContext()))
            }
            view.calendarLastDayYear.text = dates[dates.size-1].year.toString()
            view.calendarLastDayMonth.text = String.format("%02d",dates[dates.size-1].month)
            view.calendarLastDayDate.text = dates[dates.size-1].day.toString()
            Log.e("StartDay:","${dates[dates.size-1].year},${dates[dates.size-1].month},${dates[dates.size-1].day}")
            view.startDayConstraint.isSelected = false
            view.lastDayConstraint.isSelected = true
            view.applicationButton.isEnabled = true
        }


        return view.root
    }

    private fun settingDateTextView() {
        val today = CalendarDay.today()
        bottomSheetDialogBinding.calendarStartDayYear.text = today.year.toString()
        bottomSheetDialogBinding.calendarStartDayMonth.text = String.format("%02d",today.month)
        bottomSheetDialogBinding.calendarStartDayDate.text = today.day.toString()
        bottomSheetDialogBinding.calendarLastDayYear.text = today.year.toString()
        bottomSheetDialogBinding.calendarLastDayMonth.text = String.format("%02d",today.month)
        bottomSheetDialogBinding.calendarLastDayDate.text = today.day.toString()
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

class FirstDayDecorator(private val firstDay: CalendarDay): DayViewDecorator {

    override fun shouldDecorate(day: CalendarDay?): Boolean {
        return day == firstDay
    }

    override fun decorate(view: DayViewFacade?) {
        view?.addSpan(ForegroundColorSpan(Color.parseColor("#ffffff")))
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
    }
}
class LastDayDecorator(private val lastDay: CalendarDay): DayViewDecorator {

    override fun shouldDecorate(day: CalendarDay?): Boolean {
        return day == lastDay
    }

    override fun decorate(view: DayViewFacade?) {
        view?.addSpan(ForegroundColorSpan(Color.parseColor("#ffffff")))
    }
}



