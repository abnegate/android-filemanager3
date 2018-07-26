package com.jakebarnby.filemanager3.sources.models;

/**
 * Created by Jake on 7/31/2017.
 */

public class StorageStats {
    private long mTotalSpace;
    private long mUsedSpace;
    private long mFreeSpace;

    public long getTotalSpace() {
        return mTotalSpace;
    }

    public void setTotalSpace(long mTotalSpace) {
        this.mTotalSpace = mTotalSpace;
    }

    public long getUsedSpace() {
        return mUsedSpace;
    }

    public void setUsedSpace(long mUsedSpace) {
        this.mUsedSpace = mUsedSpace;
    }

    public long getFreeSpace() {
        return mFreeSpace;
    }

    public void setFreeSpace(long mFreeSpace) {
        this.mFreeSpace = mFreeSpace;
    }
}
