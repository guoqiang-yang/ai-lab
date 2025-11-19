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
@TableName("wm_club_match_battle")
public class ClubMatchBattlePO {

    @TableId(type = IdType.AUTO)
    private Integer id;



    private String clubId;
    private Integer matchId;
    private Integer type;
    private String teamA;
    private String teamB;
    private String score;

    private LocalDateTime ctime;
    private LocalDateTime mtime;
}
