package com.example.searchimage.retrofit

import com.example.searchimage.R
import com.example.searchimage.SearchApp
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor: Interceptor {
    //네트워크 통신 할 때마다 캐치해서 특정 행위를 하고 싶을 때 사용
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .addHeader(
                "Authorization",
                "KakaoAK %s".format(
                    SearchApp.getApp().getString(R.string.kakao_rest_api_key)
                )
            ).build()
        return chain.proceed(newRequest)
    }
}