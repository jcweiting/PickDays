package com.joyce.pickdays.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.joyce.pickdays.R
import com.joyce.pickdays.databinding.ActivityLoginBinding
import com.joyce.pickdays.util.mLog

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    companion object {
        private const val RC_SIGN_IN = 100

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
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
    }

    override fun onActivityResult(requestCoded: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCoded, resultCode, data)
        if (requestCoded == RC_SIGN_IN){
            viewModel.handleLoginResult(data)
        }
    }
}