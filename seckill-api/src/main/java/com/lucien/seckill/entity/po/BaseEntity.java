package com.lucien.seckill.entity.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author Lucien
 * @version 1.0
 * @date 2020/7/21 11:43
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {
    /**
     * Create time.
     */
    @TableField(fill = FieldFill.INSERT)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * Update time.
     */
    @TableField(fill = FieldFill.UPDATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    /**
     * Delete flag.
     */
    @TableLogic
    private int deleted = 0;
}
