package com.td.smartschool.admin.pojo;/*
 *Author:XX
 *Time:2021/11/4 16:57
 */

public class Img {

    int id;
    int subscript;
    String imagename;
    String title;
    byte[] img;
    String uuid;

    public Img() {

    }

    public Img(int subscript, String imagename, String title, byte[] img, String uuid) {
        this.subscript = subscript;
        this.imagename = imagename;
        this.title = title;
        this.img = img;
        this.uuid = uuid;
    }

    public Img(String imagename, String title, byte[] image, String uuid) {
        this.imagename = imagename;
        this.title = title;
        this.img = image;
        this.uuid = uuid;
    }

    public Img(int subscript, String imagename, String title, String uuid) {
        this.subscript = subscript;
        this.imagename = imagename;
        this.title = title;
        this.uuid = uuid;
    }

    public int getId() {
        return id;
    }

    public Img setId(int id) {
        this.id = id;
        return this;
    }

    public int getSubscript() {
        return subscript;
    }

    public Img setSubscript(int subscript) {
        this.subscript = subscript;
        return this;
    }

    public String getImagename() {
        return imagename;
    }

    public Img setImagename(String imagename) {
        this.imagename = imagename;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Img setTitle(String title) {
        this.title = title;
        return this;
    }

    public byte[] getImage() {
        return img;
    }

    public Img setImage(byte[] image) {
        this.img = image;
        return this;
    }

    public String getUuid() {
        return uuid;
    }

    public Img setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }
}
