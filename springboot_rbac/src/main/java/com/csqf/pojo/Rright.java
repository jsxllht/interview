package com.csqf.pojo;

public class Rright {
    private Integer rightid;

    private String righttext;

    private Integer righttype;

    private String righturl;

    private Integer parentid;

    private Boolean isMenu;

    public Integer getRightid() {
        return rightid;
    }

    public void setRightid(Integer rightid) {
        this.rightid = rightid;
    }

    public String getRighttext() {
        return righttext;
    }

    public void setRighttext(String righttext) {
        this.righttext = righttext == null ? null : righttext.trim();
    }

    public Integer getRighttype() {
        return righttype;
    }

    public void setRighttype(Integer righttype) {
        this.righttype = righttype;
    }

    public String getRighturl() {
        return righturl;
    }

    public void setRighturl(String righturl) {
        this.righturl = righturl == null ? null : righturl.trim();
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public Boolean getIsMenu() {
        return isMenu;
    }

    public void setIsMenu(Boolean isMenu) {
        this.isMenu = isMenu;
    }
}