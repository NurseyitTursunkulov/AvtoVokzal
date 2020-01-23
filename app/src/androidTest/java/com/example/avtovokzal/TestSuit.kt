package com.example.avtovokzal

import com.example.avtovokzal.findAdvert.FindAdvertsFragmentTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    AppNavigationTest::class,
    FindAdvertsFragmentTest::class
)
class TestSuit