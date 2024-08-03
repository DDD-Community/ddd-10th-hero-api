package com.hero.alignlab.domain.discussion.domain

import com.hero.alignlab.domain.common.domain.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "discussion")
class Discussion(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1L,

    @Column(name = "uid")
    val uid: Long,

    @Column(name = "type")
    val type: DiscussionType,

    @Column(name = "title")
    val title: String,

    @Column(name = "content")
    val content: String,
) : BaseEntity()