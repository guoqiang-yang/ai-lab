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
@TableName("wm_tester")
public class TesterPO {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private LocalDateTime ctime;
    private LocalDateTime mtime;
}
