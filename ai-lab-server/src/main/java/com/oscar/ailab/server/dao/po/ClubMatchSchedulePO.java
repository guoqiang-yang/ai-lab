package com.oscar.ailab.server.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("wm_club_match_schedule")
public class ClubMatchSchedulePO {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String clubId;
    private String matchName;
    private LocalDateTime matchTime;
    private Integer num;

    private Integer status;
    private LocalDateTime ctime;
    private LocalDateTime mtime;
}
