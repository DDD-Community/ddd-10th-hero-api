package com.hero.alignlab.domain.auth.model

import com.hero.alignlab.exception.ErrorCode
import com.hero.alignlab.exception.NoAuthorityException

/** 최상위 인증 및 인가 인터페이스 */
interface AuthUser {
    /** user id */
    val uid: Long

    /** user context */
    val context: AuthContext

    fun isAuthor(uid: Long): Boolean

    fun isAuthorThrow(uid: Long)

    fun isNotAuthorThrow(uid: Long)
}

data class AuthUserImpl(
    override val uid: Long,
    override val context: AuthContext,
) : AuthUser {
    override fun isAuthor(uid: Long): Boolean {
        return this.uid == uid
    }

    override fun isAuthorThrow(uid: Long) {
        if (isAuthor(uid)) {
            throw NoAuthorityException(ErrorCode.NO_AUTHORITY_ERROR)
        }
    }

    override fun isNotAuthorThrow(uid: Long) {
        if (!isAuthor(uid)) {
            throw NoAuthorityException(ErrorCode.NO_AUTHORITY_ERROR)
        }
    }
}

interface AuthContext {
    /** 이름 */
    val name: String
}

data class AuthContextImpl(
    override val name: String,
) : AuthContext

const val AUTH_TOKEN_KEY = "X-HERO-AUTH-TOKEN"

data class AuthUserToken(
    val key: String,
    val value: String,
) {
    fun isInvalid() = key.isBlank() || value.isBlank()

    companion object {
        fun from(value: String): AuthUserToken {
            return AuthUserToken(
                key = AUTH_TOKEN_KEY,
                value = value
            )
        }
    }
}

data class AuthUserTokenPayload(
    val id: Long,
    val aud: String,
    val iss: String,
    val exp: Long,
    val type: String,
)
