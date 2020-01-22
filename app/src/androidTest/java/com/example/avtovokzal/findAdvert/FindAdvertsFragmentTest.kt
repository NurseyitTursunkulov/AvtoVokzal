package com.example.avtovokzal.findAdvert

import android.widget.DatePicker
import android.widget.TimePicker
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.PickerActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withClassName
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.agoda.kakao.screen.Screen
import com.example.avtovokzal.*
import com.example.avtovokzal.util.EspressoIdlingResource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.annotation.LooperMode
import org.robolectric.annotation.TextLayoutMode


@MediumTest
@RunWith(AndroidJUnit4ClassRunner::class)
@ExperimentalCoroutinesApi
class FindAdvertsFragmentTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun initRepository() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun validTask_isSaved() {
        val navController = Mockito.mock(NavController::class.java)
        val scenario = launchFragmentInContainer<FindAdvertsFragment>(null, R.style.AppTheme)

        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }
        Screen.onScreen<FindAdvertsScreen> {
            fromTV.replaceText("кочкор")
            toTV.replaceText("балыкчы")
            calendarButton.click()

            datePickerDialog {
                datePicker {
                    setDate(2012, 12, 12)
                    hasDate(2012, 12, 12)
                }
                okButton {
                    click()
                }
            }

            timePickerDialog {
                timePicker {
                    setTime(12, 12)
                    hasTime(12, 12)
                }
                okButton {
                    click()
                }
            }
            dateTV {
                hasText("12 дек. 12 12:12 ")
            }
        }
    }
}
