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
public class Video implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * BgmID
     */
    private String audioId;

    /**
     * 视频描述
     */
    private String videoDesc;

    /**
     * 视频时长
     */
    private Float videoSeconds;

    /**
     * 视频宽度
     */
    private Integer videoWidth;

    /**
     * 视频高度
     */
    private Integer videoHeight;

    /**
     * 视频路径
     */
    private String videoPath;

    /**
     * 封面路径
     */
    private String coverPath;

    /**
     * 被点赞的数量
     */
    private Long likeCounts;

    /**
     * 状态 “0”正常播放 “1”禁播
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
