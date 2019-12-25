package com.github.satoshun.precomputedtextcompat

import androidx.benchmark.junit4.BenchmarkRule
import androidx.benchmark.junit4.measureRepeated
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MyBenchmarkTest {
  @get:Rule val benchmarkRule = BenchmarkRule()
  @get:Rule val scenarioRule = ActivityScenarioRule(MainActivity::class.java)

  @Test
  fun test1() {
    val scenario = scenarioRule.scenario

    benchmarkRule.measureRepeated  {
    }
  }
}
