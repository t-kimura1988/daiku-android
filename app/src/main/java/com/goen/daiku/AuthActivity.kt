package com.goen.daiku

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.goen.auth.presentation.ui.PrivacyPolicyCompose
import com.goen.auth.presentation.ui.SignInCompose
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class AuthActivity(
) : ComponentActivity(), FirebaseAuth.AuthStateListener {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    private val googleSignInIdToken: String = BuildConfig.GOOGLE_SIGN_IN_ID_TOKEN

    val appBaseUrl: String = com.goen.auth.BuildConfig.APP_BASE_URL

    private val authResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            val account = task.getResult(ApiException::class.java)!!

            firebaseAuthWithGoogle(account.idToken!!)

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidThreeTen.init(this)
        FirebaseApp.initializeApp(this)
        mAuth = Firebase.auth

    }

    private fun firebaseAuthWithGoogle(idToken: String){
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Timber.d("signInWithCredential:success")
                }
            }
    }

    var signIn: () -> Unit = {
        // Configure Google Sign In
        Timber.i("Log IN :START")
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(googleSignInIdToken)
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
        val signInInten = googleSignInClient.signInIntent
        authResultLauncher.launch(signInInten)
    }

    override fun onStart() {
        super.onStart()
        FirebaseAuth.getInstance().addAuthStateListener(this)
    }

    override fun onStop() {
        super.onStop()
        FirebaseAuth.getInstance().removeAuthStateListener(this)
    }

    @OptIn(
        ExperimentalComposeUiApi::class,
        ExperimentalMaterialApi::class
    )
    override fun onAuthStateChanged(fAuth: FirebaseAuth) {
        if (fAuth.currentUser == null ) {
            setContent {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "auth"
                ) {
                    navigation(
                        startDestination = "signIn",
                        route = "auth"
                    ) {
                        composable(
                            "signIn"
                        ) {
                            SignInCompose(
                                onSignIn = {signIn()},
                                navController = navController
                            )
                        }

                        composable(
                            "privacyPolicy"
                        ) {
                            PrivacyPolicyCompose(
                                navController = navController,
                                viewUrl = "${appBaseUrl}/privacy-policy"
                            )
                        }

                        composable(
                            "termsOfUse"
                        ) {
                            PrivacyPolicyCompose(
                                navController = navController,
                                viewUrl = "${appBaseUrl}/terms-of-use"
                            )
                        }
                    }
                }
            }
            return
        }

        setContent {
            AccountExistCompose()
        }

    }
}