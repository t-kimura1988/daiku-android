package com.goen.domain.interceptor

import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GetTokenResult
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor() : Interceptor {
    companion object {
        const val headerName = "Authorization"
        const val headerValue = "DUMMY"
        const val placeholder = "$headerName: $headerValue"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val user: FirebaseUser? = FirebaseAuth.getInstance().currentUser
        if(user == null) {
            throw Exception("current user not logged in")
        }
        val task: Task<GetTokenResult> = user.getIdToken(true)
        val tokenResult: GetTokenResult? = Tasks.await(task)
        val token: String? = tokenResult?.token
        val request = chain.request()
        var newRequest =
            if (request.header(headerName) == headerValue)
                request.newBuilder()
                    .header("Authorization", "Bearer $token")
                    .header("Cache-Control", "no-cache").build()

            else
                request
        return chain.proceed(newRequest)
    }
}