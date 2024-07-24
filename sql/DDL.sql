-- scheme
CREATE
DATABASE hero CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

-- 유저 정보
CREATE TABLE `user_info`
(
    `id`          bigint      NOT NULL AUTO_INCREMENT COMMENT 'user id',
    `nickname`    varchar(64) NOT NULL COMMENT '닉네임',
    `created_at`  datetime DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    `modified_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=200000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='유저 정보';

CREATE UNIQUE INDEX uidx__nickname ON user_info (nickname);

-- 일반 회원가입 유저 정보
CREATE TABLE `credential_user_info`
(
    `id`          bigint       NOT NULL AUTO_INCREMENT COMMENT 'credential_user_info id',
    `uid`         bigint       NOT NULL COMMENT 'user id',
    `username`    varchar(256) NOT NULL COMMENT '로그인 id',
    `password`    varchar(512) NOT NULL COMMENT '로그인 pw',
    `created_at`  datetime DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    `modified_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=200000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='유저 정보';

CREATE UNIQUE INDEX uidx__uid ON credential_user_info (uid);
CREATE UNIQUE INDEX uidx__username ON credential_user_info (username);

-- 그룹
CREATE TABLE `group`
(
    `id`          bigint      NOT NULL AUTO_INCREMENT COMMENT 'group id',
    `name`        varchar(32) NOT NULL COMMENT '그룹명',
    `description` varchar(512) DEFAULT NULL COMMENT '그룹 설명',
    `owner_uid`   bigint      NOT NULL COMMENT 'owner uid',
    `created_at`  datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    `modified_at` datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=200000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='그룹';

CREATE UNIQUE INDEX uidx__name ON `group` (name);
CREATE INDEX idx__owner_uid ON `group` (owner_uid);

-- 그룹 유저
CREATE TABLE `group_user`
(
    `id`          bigint NOT NULL AUTO_INCREMENT COMMENT 'group user id',
    `group_id`    bigint NOT NULL COMMENT 'group id',
    `uid`         bigint NOT NULL COMMENT 'uid',
    `created_at`  datetime DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    `modified_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=200000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='그룹 유저';

CREATE UNIQUE INDEX uidx__group_id__uid ON group_user (group_id, uid);
CREATE INDEX uidx__uid ON group_user (uid);
