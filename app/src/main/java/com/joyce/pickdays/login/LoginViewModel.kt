package com.joyce.pickdays.login

import android.content.Intent
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.joyce.pickdays.R
import com.joyce.pickdays.util.GlobalUtil.getContext
import com.joyce.pickdays.util.mLog

class LoginViewModel: ViewModel() {

    private lateinit var googleSignInClient: GoogleSignInClient

    init {
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getContext().getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(getContext(), googleSignInOptions)
    }

    fun googleSignInIntent(): Intent {
        return googleSignInClient.signInIntent
    }

    fun handleLoginResult(data: Intent?) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        try {
            val account = task.getResult(ApiException::class.java)
            account?.idToken?.let { idToken ->
                mLog.i("idToken = $idToken")
            }
        } catch (e: Exception){
            mLog.i(e.toString())
        }
    }

}