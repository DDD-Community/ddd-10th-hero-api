package com.hero.alignlab.domain.group.model.response

import com.hero.alignlab.domain.group.domain.Group
import com.hero.alignlab.domain.group.domain.GroupTag

data class GetGroupResponse(
    val id: Long,
    val name: String,
    val description: String?,
    val ownerUid: Long,
    /** 그룹장명 */
    val ownerName: String,
    val isHidden: Boolean,
    /** 그룹장만 조회 가능 */
    val joinCode: String?,
    /** 그룹원 수 */
    val userCount: Int,
    /** 그룹 정원 */
    val userCapacity: Int,
    val ranks: List<GetGroupRankResponse>? = null,
    /** 그룹 태그 리스트 */
    val tagNames: List<String>?,
    val countCheeredUp: Long? = null,
) {
    companion object {
        fun of(
            group: Group,
            tags: List<GroupTag>?,
            ownerName: String,
            countCheeredUp: Long?,
        ): GetGroupResponse {
            return GetGroupResponse(
                id = group.id,
                name = group.name,
                description = group.description,
                ownerUid = group.ownerUid,
                ownerName = ownerName,
                isHidden = group.isHidden,
                joinCode = group.joinCode,
                userCount = group.userCount,
                userCapacity = group.userCapacity,
                tagNames = tags?.map { it.name },
                countCheeredUp = countCheeredUp,
            )
        }
    }
}
