package com.example.farmus_application.ui.farm

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.icu.text.CaseMap.Title
import android.os.Bundle
import android.provider.CalendarContract.Colors
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
import com.prolificinteractive.materialcalendarview.format.TitleFormatter

class CalendarBottomSheetDialog: BottomSheetDialogFragment() {

    private lateinit var bottomSheetDialogBinding: DialogBottomSheetCalendarBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bottomSheetDialogBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_bottom_sheet_calendar, container, false)
        val view = bottomSheetDialogBinding

        view.bottomSheetCalendar.addDecorator(TodayDecorator(requireContext()))
        view.bottomSheetCalendar.addDecorator(BeforeDayDecorator())
        view.bottomSheetCalendar.setTitleFormatter { day -> "${day.month}월${day.year}년" }
        view.bottomSheetCalendar.setLeftArrow(R.drawable.calendar_today_background)

        view.bottomSheetCalendar.setOnDateChangedListener { widget, date, _ ->
            Log.e("setOnDateChangedListener","DateChangedClick!")
            widget.removeDecorators()
            widget.addDecorator(FirstDayDecorator(date))
            widget.addDecorators(TodayDecorator(requireContext()), BeforeDayDecorator())
            // 다시 범위를 클릭할때 color를 다시 원상복구 시켜야함 (모든 deco 초기화 하고 today,befor Deco 다시 설정)
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
        }


        return view.root
    }
}

class TodayDecorator(private val context: Context) : DayViewDecorator {

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

class BeforeDayDecorator(): DayViewDecorator {

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
        view?.let {
            it.addSpan(ForegroundColorSpan(Color.parseColor("#ffffff")))
        }
    }
}
class RangeDayDecorator(
    private val days: List<CalendarDay>,
    private val context: Context
): DayViewDecorator {
    private var drawable: Drawable = ContextCompat.getDrawable(context, R.drawable.calendar_range_background)!!

    override fun shouldDecorate(day: CalendarDay?): Boolean {
        return days.contains(day)
    }

    override fun decorate(view: DayViewFacade?) {
        view?.let {
            it.setSelectionDrawable(drawable)
        }
    }
}
class LastDayDecorator(private val lastDay: CalendarDay): DayViewDecorator {

    override fun shouldDecorate(day: CalendarDay?): Boolean {
        return day == lastDay
    }

    override fun decorate(view: DayViewFacade?) {
        view?.let {
            it.addSpan(ForegroundColorSpan(Color.parseColor("#ffffff")))
        }
    }
}



