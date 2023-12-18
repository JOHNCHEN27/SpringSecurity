package com.lncanswer.security.distributed.uaa.model;

import lombok.Data;

/**
 * @author LNC
 * @version 1.0
 * @description
 * @date 2023/12/16 10:59
 */
@Data
public class PermissionDto {
    private String id;
    private String code;
    private String description;
    private String url;
}
