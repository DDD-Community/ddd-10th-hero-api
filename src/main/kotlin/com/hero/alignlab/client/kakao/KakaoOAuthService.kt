package com.hero.alignlab.client.kakao

import com.hero.alignlab.client.kakao.client.KaKaoOAuthClient
import com.hero.alignlab.client.kakao.config.KakaoOAuthClientConfig
import com.hero.alignlab.client.kakao.model.request.GenerateKakaoOAuthTokenRequest
import com.hero.alignlab.client.kakao.model.response.GenerateKakaoOAuthTokenResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service

@Service
class KakaoOAuthService(
    private val kaKaoOAuthClient: KaKaoOAuthClient,
    private val config: KakaoOAuthClientConfig.Config
) {
    suspend fun getOAuthAuthorizeCode(redirectUrl: String? = null) {
        withContext(Dispatchers.IO) {
            kaKaoOAuthClient.getOAuthAuthorizeCode()
        }
    }

    suspend fun generateOAuthToken(code: String, redirectUri: String? = null): GenerateKakaoOAuthTokenResponse {
        val request = GenerateKakaoOAuthTokenRequest(
            clientId = config.clientSecretCode,
            redirectUri = redirectUri ?: config.redirectUrl,
            code = code,
        )

        return generateOAuthToken(request)
    }

    suspend fun generateOAuthToken(request: GenerateKakaoOAuthTokenRequest): GenerateKakaoOAuthTokenResponse {
        return withContext(Dispatchers.IO) {
            kaKaoOAuthClient.generateOAuthToken(request)
        }
    }
}