package me.wemeet.kele.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author Quino Wu
 * @since 2022-01-05
 */
@Getter
@Setter
@TableName("playlist_extends")
public class PlaylistExtends implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long playlistId;

    private Integer favorites;

    private Integer views;


}