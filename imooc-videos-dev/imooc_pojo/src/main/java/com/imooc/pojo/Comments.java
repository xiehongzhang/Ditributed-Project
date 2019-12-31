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
public class Comments implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 视频ID
     */
    private String videoId;

    /**
     * 评论者ID
     */
    private String fromUserId;

    /**
     * 父评论ID
     */
    private String parentCommentId;

    /**
     * 被评论者ID
     */
    private String toUserId;

    /**
     * 内容
     */
    private String comment;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
