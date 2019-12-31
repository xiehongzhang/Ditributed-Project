package com.imooc.pojo;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author xhz
 * @since 2019-12-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserReport implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 被举报者ID
     */
    private String dealUserId;

    /**
     * 被举报视频ID
     */
    private String dealVideoId;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 举报者ID
     */
    private String userId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
