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
@TableName("wm_account")
public class AccountPO {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;
    private String nickname;
    private Integer gender;
    private String wechat;
    private Integer level;
    private Integer totalScore;

    private LocalDateTime ctime;
    private LocalDateTime mtime;

}
