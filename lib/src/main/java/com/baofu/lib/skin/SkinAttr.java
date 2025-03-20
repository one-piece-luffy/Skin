package com.baofu.lib.skin;

public class SkinAttr {
    //属性名（例如：background、textColor）
    public String attrName;

    //属性类型（例如：drawable、color）
    public String attrType;

    //资源id（例如：123）
    public int resId;

    //资源名称（例如：ic_bg）
    public String resName;

    public SkinAttr(String attrName, String attrType, String resName,int resId) {
        this.attrName = attrName;
        this.attrType = attrType;
        this.resId = resId;
        this.resName = resName;
    }

}
