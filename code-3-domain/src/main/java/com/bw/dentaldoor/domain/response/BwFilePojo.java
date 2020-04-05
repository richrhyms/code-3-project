package com.bw.dentaldoor.domain.response;

public class BwFilePojo {
    private long id;
    private String contentType;
    private String description;
    private String externalReferencePath;
    private String dateCreated;

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExternalReferencePath() {
        return externalReferencePath;
    }

    public void setExternalReferencePath(String externalReferencePath) {
        this.externalReferencePath = externalReferencePath;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
}
