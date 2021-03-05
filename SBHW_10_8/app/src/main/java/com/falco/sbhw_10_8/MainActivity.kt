package com.falco.sbhw_10_8

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.falco.sbhw_10_8.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val edList = listOf(binding.emailInput, binding.passwordInput)
        val textWatcher = CustomTextWatcher(binding.loginButton, binding.agreementsCheckBox, edList)

        for (editText in edList) editText.addTextChangedListener(textWatcher)

        binding.agreementsCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                for (editText in edList) {
                    if (editText.text.toString().isEmpty()) {
                        binding.loginButton.isEnabled = false
                        break
                    } else binding.loginButton.isEnabled = true
                }
            } else binding.loginButton.isEnabled = false
        }

        binding.loginButton.setOnClickListener {
            makeLoginOperation()
        }

        Glide
            .with(this)
            .load(R.drawable.logo)
            .into(binding.logoView)
    }

    private fun makeLoginOperation() {
        binding.emailInput.isEnabled = false
        binding.passwordInput.isEnabled = false
        binding.agreementsCheckBox.isEnabled = false
        binding.loginButton.isEnabled = false

        val progressBar =
            layoutInflater.inflate(R.layout.progress_bar_layout, binding.mainLayoutLogin, false)
        binding.loginLayout.addView(progressBar)

        Handler().postDelayed(
            {
                binding.loginLayout.removeView(progressBar)
                binding.emailInput.isEnabled = true
                binding.passwordInput.isEnabled = true
                binding.agreementsCheckBox.isEnabled = true
                binding.loginButton.isEnabled = true
                Toast.makeText(this, getString(R.string.successful_login), Toast.LENGTH_LONG).show()
            },
            2000
        )
    }
}
