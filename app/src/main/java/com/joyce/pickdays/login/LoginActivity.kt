package com.joyce.pickdays.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.joyce.pickdays.databinding.ActivityLoginBinding
import com.joyce.pickdays.util.mLog

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var loginResultLauncher: ActivityResultLauncher<Intent>

    companion object {
        fun startActivity(context: Context){
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.googleLogin.setOnClickListener {
            mLog.i("click google login")
            val signInIntent = viewModel.googleSignInIntent()
            loginResultLauncher.launch(signInIntent)
        }

        loginResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            if (result.resultCode == Activity.RESULT_OK){
                viewModel.handleLoginResult(result.data)
            } else {
                mLog.e("Google sign-in failed, resultCode: ${result.resultCode}")
            }
        }
    }
}