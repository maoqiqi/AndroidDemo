package com.software.march.bean;

import java.io.Serializable;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description 一个媒体资源文件实体类
 * @date 2016/11/22
 */
public class MediaItemBean implements Serializable {

    // MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
    // MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

    /**
     * 编号
     */
    private long _id;

    /**
     * 标题
     */
    private String title;

    /**
     * 艺术家
     */
    private String artist;

    /**
     * 完整名字 (artist + "-" + title)
     */
    private String displayName;

    /**
     * 路径
     */
    private String data;

    /**
     * 时长
     */
    private long duration;

    /**
     * 大小
     */
    private long size;

    /**
     * 专辑id
     */
    private long albumId;

    /**
     * 艺术家id
     */
    private long artistId;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public long getArtistId() {
        return artistId;
    }

    public void setArtistId(long artistId) {
        this.artistId = artistId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "MediaItemBean{" +
                "_id=" + _id +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", displayName='" + displayName + '\'' +
                ", data='" + data + '\'' +
                ", duration=" + duration +
                ", size=" + size +
                ", albumId=" + albumId +
                ", artistId=" + artistId +
                '}';
    }
}