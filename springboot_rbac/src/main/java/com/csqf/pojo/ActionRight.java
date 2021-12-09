package com.csqf.pojo;

public class ActionRight {
    private Integer rightid;

    private String rightCode;

    private String righttext;

    private Integer righttype;

    private String righturl;

    private Integer parentid;

    public Integer getRightid() {
        return rightid;
    }

    public void setRightid(Integer rightid) {
        this.rightid = rightid;
    }

    public String getRightCode() {
        return rightCode;
    }

    public void setRightCode(String rightCode) {
        this.rightCode = rightCode == null ? null : rightCode.trim();
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
}