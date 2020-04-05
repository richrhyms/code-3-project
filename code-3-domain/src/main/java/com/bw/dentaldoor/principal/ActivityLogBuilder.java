package com.bw.dentaldoor.principal;

import com.bw.entity.ActivityLog;

public abstract class ActivityLogBuilder {

    private String action;
    private String description;
    private Long entityId;
    private String entityName;

    public String getAction() {
        return action;
    }

    public ActivityLogBuilder setAction(String action) {
        this.action = action;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ActivityLogBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public abstract ActivityLogBuilder setEntity(Object entity);

    public Long getEntityId() {
        return entityId;
    }

    public ActivityLogBuilder setEntityId(Long entityId) {
        this.entityId = entityId;
        return this;
    }

    public String getEntityName() {
        return entityName;
    }

    public ActivityLogBuilder setEntityName(String entityName) {
        this.entityName = entityName;
        return this;
    }

    public abstract ActivityLog log();
}
