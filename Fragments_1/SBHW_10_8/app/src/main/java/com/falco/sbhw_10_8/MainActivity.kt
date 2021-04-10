package com.falco.sbhw_10_8

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.falco.sbhw_10_8.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), FragmentChangeListener {
    private lateinit var binding: ActivityMainBinding
    private val tag = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportFragmentManager.beginTransaction()
            .add(R.id.mainFragmentContainer, LoginFragment())
            .commit()
    }

    override fun onStart() {
        super.onStart()
        DebugLogger.d(tag, "onStart was called ${hashCode()}")
    }

    override fun onResume() {
        super.onResume()
        DebugLogger.d(tag, "onResume was called ${hashCode()}")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onTopResumedActivityChanged(isTopResumedActivity: Boolean) {
        super.onTopResumedActivityChanged(isTopResumedActivity)
        DebugLogger.d(
            tag,
            "onTopResumedActivityChanged was called ${hashCode()} $isTopResumedActivity"
        )
    }

    override fun onPause() {
        super.onPause()
        DebugLogger.d(tag, "onPause was called ${hashCode()}")
    }

    override fun onStop() {
        super.onStop()
        DebugLogger.d(tag, "onStop was called ${hashCode()}")
    }

    override fun onDestroy() {
        super.onDestroy()
        DebugLogger.d(tag, "onDestroy was called ${hashCode()}")
    }

    object DebugLogger {
        fun d(tag: String, msg: String) {
            if (BuildConfig.DEBUG) {
                Log.d(tag, msg)
            }
        }
    }

    override fun replaceFragmentAfterSuccessfulLogin() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainFragmentContainer, MainFragment())
            .commit()
    }
}
