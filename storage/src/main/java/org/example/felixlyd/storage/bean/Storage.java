package org.example.felixlyd.storage.bean;

import lombok.Data;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/10/25
 */
@Data
public class Storage {
    private Long id;
    private Long productId;
    private Integer total;
    private Integer used;
    private Integer residue;
    private Integer frozen;
}
