package com.okason.prontodiary.model;

/**
 * Created by Valentine on 9/7/2015.
 */
public class Tag {
    private String tagId;
    private String tagName;
    private int journalCount;

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public int getJournalCount() {
        return journalCount;
    }

    public void setJournalCount(int journalCount) {
        this.journalCount = journalCount;
    }
}
