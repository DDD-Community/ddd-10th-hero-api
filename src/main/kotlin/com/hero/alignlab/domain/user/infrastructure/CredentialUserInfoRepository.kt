package com.hero.alignlab.domain.user.infrastructure

import com.hero.alignlab.common.encrypt.EncryptData
import com.hero.alignlab.domain.user.domain.CredentialUserInfo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Repository
interface CredentialUserInfoRepository : JpaRepository<CredentialUserInfo, Long> {
    fun existsByUsername(username: String): Boolean

    fun findByUsernameAndPassword(username: String, password: EncryptData): CredentialUserInfo?
}