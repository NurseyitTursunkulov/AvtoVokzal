package com.example.avtovokzal.findAdvert

import android.view.Gravity
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.DrawerMatchers
import androidx.test.espresso.contrib.NavigationViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.MediumTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.agoda.kakao.screen.Screen
import com.example.avtovokzal.MainActivity
import com.example.avtovokzal.R
import com.example.avtovokzal.util.DataBindingIdlingResource
import com.example.avtovokzal.util.EspressoIdlingResource
import com.example.avtovokzal.util.monitorActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito


@MediumTest
@RunWith(AndroidJUnit4ClassRunner::class)
@ExperimentalCoroutinesApi
class FindAdvertsFragmentTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)
    private val dataBindingIdlingResource = DataBindingIdlingResource()

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
            fromTV.replaceText("Кочкор")
            toTV.replaceText("Балыкчы")
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

            publicateButton {
                click()
            }
            Mockito.verify(navController).navigate(
                FindAdvertsFragmentDirections.actionNavFindadvertToAdvertsFragment2(
                )
            )
        }
    }

    @Test
    fun validTask_isSave() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        dataBindingIdlingResource.monitorActivity(activityScenario)

        onView(ViewMatchers.withId(R.id.drawer_layout))
            .check(ViewAssertions.matches(DrawerMatchers.isClosed(Gravity.START))) // Left Drawer should be closed.
            .perform(DrawerActions.open()) // Open Drawer

        onView(ViewMatchers.withId(R.id.nav_view))
            .perform(NavigationViewActions.navigateTo(R.id.nav_find_adverts))

        Screen.onScreen<FindAdvertsScreen> {
            fromTV.replaceText("Кочкор")
            toTV.replaceText("Балыкчы")
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

            publicateButton {
                click()
            }
            Screen.onScreen<AdvertsScreen> {
                recycler{
                    isVisible()

                    lastChild<AdvertsScreen.MainItem> {
                        isVisible()
                        date {
                            isDisplayed()
                            hasText("2012/1/2")
                        }

                    }

                }
            }
        }
    }
}
