package com.hero.alignlab.domain.user.domain

import com.hero.alignlab.domain.common.domain.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "user_info")
class UserInfo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(name = "nickname")
    var nickname: String,

    @Column(name = "level")
    var level: Int = 1,
) : BaseEntity() {
    val maxLevel: Boolean
        get() = level >= 5
}
