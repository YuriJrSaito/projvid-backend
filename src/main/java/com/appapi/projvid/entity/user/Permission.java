package com.appapi.projvid.entity.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("ADMIN_READ"),
    ADMIN_UPDATE("ADMIN_UPDATE"),
    ADMIN_CREATE("ADMIN_CREATE"),
    ADMIN_DELETE("ADMIN_DELETE"),
    MANAGER_READ("MANAGEMENT_READ"),
    MANAGER_UPDATE("MANAGEMENT_UPDATE"),
    MANAGER_CREATE("MANAGEMENT_CREATE"),
    MANAGER_DELETE("MANAGEMENT_DELETE");

    @Getter
    private final String permission;
}