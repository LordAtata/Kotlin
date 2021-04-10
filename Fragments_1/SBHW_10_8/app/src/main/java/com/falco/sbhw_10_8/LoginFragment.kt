package com.falco.sbhw_10_8

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.transition.TransitionManager
import com.bumptech.glide.Glide
import com.falco.sbhw_10_8.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private var formState: FormState? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (savedInstanceState != null) {
            formState = savedInstanceState.getParcelable(KEY_VALIDATION)
                ?: error("Нежданчик")
            if (formState?.valid == false) stringBuilder(formState?.message ?: "???")
        }

        binding.loginButton.setOnClickListener {
            validation()
        }

        binding.buttonANR.setOnClickListener {
            Thread.sleep(10000)
        }

        Glide
            .with(this)
            .load(R.drawable.logo)
            .into(binding.logoView)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_VALIDATION, formState)
    }

    private fun validation() {
        if (!binding.emailInput.isEmailValid()) {
            formState = FormState(false, "Неверный адрес электронной почты")
            stringBuilder(formState?.message ?: "???")
            binding.emailInput.requestFocus()
        } else if (!isPasswordValid()) {
            stringBuilder(formState?.message ?: "???")
            binding.passwordInput.requestFocus()
        } else if (!binding.agreementsCheckBox.isChecked) {
            formState = FormState(false, "Подтвердите принятие соглашений")
            stringBuilder(formState?.message ?: "???")
            binding.agreementsCheckBox.requestFocus()
        } else {
            formState = FormState(true, "valid")
            binding.inputText.setText(R.string.input)
            makeLoginOperation()
        }
    }

    private fun stringBuilder(msg: String) {
        val ssb = SpannableStringBuilder()
        ssb.append(getString(R.string.input))
        ssb.append("\n")
        val initialPart = ssb.length
        ssb.append(getString(R.string.invalid_form))
        ssb.append("\n")
        ssb.append(msg)
        ssb.setSpan(
            ForegroundColorSpan(Color.rgb(154, 29, 29)),
            initialPart,
            ssb.length,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
        ssb.setSpan(
            RelativeSizeSpan(0.7f),
            initialPart,
            ssb.length,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
        binding.inputText.text = ssb
    }

    private fun EditText.isEmailValid(): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(this.text.toString()).matches()
    }

    private fun isPasswordValid(): Boolean {
        if (binding.passwordInput.text.toString().length < 6) {
            formState = FormState(false, "Длина пароля не должна быть меньше 6")
            return false
        }
        return true
    }

    private fun makeLoginOperation() {
        changeEnable(false)
        val progressBar = ProgressBar(requireContext())
        progressBar.id = View.generateViewId()
        binding.mainLayoutLogin.addView(progressBar)
        val set = ConstraintSet()
        set.clone(binding.mainLayoutLogin)
        set.constrainCircle(progressBar.id, R.id.loginButton, 200, 240F)
        TransitionManager.beginDelayedTransition(binding.mainLayoutLogin)
        set.applyTo(binding.mainLayoutLogin)
        Handler().postDelayed(
            {
                binding.mainLayoutLogin.removeView(progressBar)
                changeEnable(true)
                Toast.makeText(requireContext(), getString(R.string.successful_login), Toast.LENGTH_SHORT).show()
                (requireActivity() as FragmentChangeListener).replaceFragmentAfterSuccessfulLogin()
            },
            2000
        )
    }

    private fun changeEnable(statement: Boolean) {
        binding.emailInput.isEnabled = statement
        binding.passwordInput.isEnabled = statement
        binding.agreementsCheckBox.isEnabled = statement
        binding.loginButton.isEnabled = statement
    }

    object DebugLogger {
        fun d(tag: String, msg: String) {
            if (BuildConfig.DEBUG) {
                Log.d(tag, msg)
            }
        }
    }

    companion object {
        private const val KEY_VALIDATION = "validation"
    }
}