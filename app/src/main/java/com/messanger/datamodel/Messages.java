package com.messanger.datamodel;

/**
 * Created by clicklabs on 11/24/17.
 * <p>
 * Message model for user.
 */

public class Messages {
    private String fromId;
    private String message = "";
    private String imageUrl = "";
    private long timeStamp;
    private String toId;

    /**
     * get data
     *
     * @return string.
     */
    public String getFromId() {
        return fromId;
    }

    /**
     * String
     *
     * @param fromId string
     */
    public void setFromId(final String fromId) {
        this.fromId = fromId;
    }

    /**
     * get data
     *
     * @return string.
     */
    public String getMessage() {
        return message;
    }

    /**
     * String
     *
     * @param message string
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * get data
     *
     * @return string.
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * String
     *
     * @param imageUrl string
     */
    public void setImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * get data
     *
     * @return string.
     */
    public long getTimeStamp() {
        return timeStamp;
    }

    /**
     * String
     *
     * @param timeStamp string
     */
    public void setTimeStamp(final long timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * get data
     *
     * @return string.
     */
    public String getToId() {
        return toId;
    }

    /**
     * String
     *
     * @param toId string
     */
    public void setToId(final String toId) {
        this.toId = toId;
    }
}
