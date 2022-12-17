package com.example.android.shoestore.feature.onboarding

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.aemerse.onboard.OnboardAdvanced
import com.aemerse.onboard.OnboardFragment
import com.example.android.shoestore.R
import com.example.android.shoestore.feature.MainActivity

private const val SHOE_APP_PREF = "Shoe App preference"
private const val FIRST_RUN = "first_run_ke"

class OnBoardingActivity : OnboardAdvanced() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences(SHOE_APP_PREF, MODE_PRIVATE)

        checkIfFirstOnBoardingLaunch()
        addSlide(
            OnboardFragment.newInstance(
                title = "Welcome...",
                description = "Browse and check all our new shoes.",
                resourceId = R.raw.onboarding1,
                isLottie = true,
                backgroundDrawable = R.drawable.onboarding_bg
            )
        )
        addSlide(
            OnboardFragment.newInstance(
                title = "...Let's get started!",
                description = "Pay with any digital payment method.",
                resourceId = R.raw.onboarding2,
                isLottie = true,
                backgroundDrawable = R.drawable.onboarding_bg
            )
        )

        addSlide(
            OnboardFragment.newInstance(
                title = "Finally",
                description = "Receive your product in less than 2 days",
                resourceId = R.raw.onboarding3,
                isLottie = true,
                backgroundDrawable = R.drawable.onboarding_bg
            )
        )
    }

    private fun checkIfFirstOnBoardingLaunch() {
        val isFirstOnBoardingLaunch = sharedPreferences.getBoolean(FIRST_RUN, true)
        if (isFirstOnBoardingLaunch) {
            sharedPreferences.edit().putBoolean(FIRST_RUN, false).apply()
        } else {
            navigateToShoeAppMainEntry()
        }
    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        navigateToShoeAppMainEntry()

    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        navigateToShoeAppMainEntry()
    }


    private fun navigateToShoeAppMainEntry() {
        val mainEntryIntent = Intent(this, MainActivity::class.java)
        startActivity(mainEntryIntent)
        finish()
    }
}