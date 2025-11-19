

CREATE TABLE `wm_account` (
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `name` varchar(16) NOT NULL DEFAULT '' COMMENT '姓名',
    `nickname` varchar(64) NOT NULL DEFAULT '' COMMENT '昵称',
    `gender` tinyint(1) NOT NULL DEFAULT 0 COMMENT '性别：0-男，1-女',
    `wechat` varchar(32) NOT NULL DEFAULT '' COMMENT '微信账号',

    `level` tinyint(1) NOT NULL DEFAULT 0 COMMENT '级别',
    `total_score` int(10) NOT NULL DEFAULT 0 COMMENT '总积分',

    `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',

    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='账号' AUTO_INCREMENT=10000;

CREATE TABLE `wm_club` (
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `club_id` varchar(16) NOT NULL DEFAULT '' COMMENT '俱乐部ID',
    `name` varchar(32) NOT NULL DEFAULT '' COMMENT '名称',
    `logo` varchar(256) NOT NULL DEFAULT '' COMMENT 'Logo',
    `intro` varchar(256) NOT NULL DEFAULT '' COMMENT '介绍',
    `member_num` int(10) NOT NULL DEFAULT 0 COMMENT '会员数量',

    `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',

    PRIMARY KEY (`id`),
    UNIQUE KEY ix_club_id (`club_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='俱乐部' AUTO_INCREMENT=1000;

CREATE TABLE `wm_club_member` (
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `club_id` varchar(16) NOT NULL DEFAULT '' COMMENT '俱乐部ID',
    `uid` int(10)  NOT NULL DEFAULT 0 COMMENT '会员ID',
    `nickname` varchar(64) NOT NULL DEFAULT '' COMMENT '俱乐部昵称',
    `level` tinyint(1) NOT NULL DEFAULT 0 COMMENT '级别',
    `records` varchar(256) NOT NULL DEFAULT '' COMMENT '战绩，Json， {t-total,w-win,l-lose}',

    `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态：0-正常，1-离开',
    `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',

    PRIMARY KEY (`id`),
    KEY ix_club_id (`club_id`),
    KEY ix_uid (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='俱乐部会员';

CREATE TABLE `wm_club_match_schedule` (
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `club_id` varchar(16) NOT NULL DEFAULT '' COMMENT '俱乐部ID',
    `match_name` varchar(64) NOT NULL DEFAULT '' COMMENT '比赛名称',
    `match_time` datetime NOT NULL DEFAULT '1970-01-01' COMMENT '比赛时间',
    `num` int(10)  NOT NULL DEFAULT 0 COMMENT '报名人数',
    `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态：1:未开放，2:报名中，3:已分组/比赛中，4:结束，-1:取消',

    `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',

    PRIMARY KEY (`id`),
    KEY ix_clubid_time (`club_id`, `match_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='俱乐部比赛赛程';

CREATE TABLE `wm_club_match_signup` (
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `club_id` varchar(16) NOT NULL DEFAULT '' COMMENT '俱乐部ID',
    `match_id` int(10) NOT NULL DEFAULT 0 COMMENT '比赛id',
    `uid` int(10) NOT NULL DEFAULT 0 COMMENT '会员id',
    `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态：0-空闲，1-比赛中',

    `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',

    PRIMARY KEY (`id`),
    KEY ix_clubid_mid (`club_id`, `match_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='俱乐部比赛报名表';

CREATE TABLE `wm_club_match_battle` (
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `club_id` varchar(16) NOT NULL DEFAULT '' COMMENT '俱乐部ID',
    `match_id` int(10) NOT NULL DEFAULT 0 COMMENT '比赛id',
    `type` tinyint(1) NOT NULL DEFAULT 0 COMMENT '比赛类型：1-男单，2-女单，3-男双，4-女双，5-混双, 6-自定义',
    `team_a` varchar(128) NOT NULL DEFAULT '' COMMENT 'A队：json, List(uid)',
    `team_b` varchar(128) NOT NULL DEFAULT '' COMMENT 'B队：json, List(uid)',
    `score` varchar(32) NOT NULL DEFAULT '' COMMENT '比分: 21:19',

    `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',

    PRIMARY KEY (`id`),
    KEY ix_clubid_mid (`club_id`, `match_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='俱乐部比赛对战表';




-- ================================================================================
-- ================================================================================
-- ================================================================================
CREATE TABLE `wm_xxxxxxxxx` (
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',

    `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',

    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='xxxxxxxxxx';

CREATE TABLE `wm_tester` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(64) DEFAULT '' COMMENT '名称',

  `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',

  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='test table';