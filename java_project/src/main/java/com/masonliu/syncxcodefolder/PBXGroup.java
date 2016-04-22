package com.masonliu.syncxcodefolder;

import com.sap.prd.mobile.ios.mios.xcodeprojreader.Array;

/**
 * Created by liumeng on 16/4/17.
 */
public class PBXGroup {
    private String key;
    private String isa;
    private String name;
    private String path;
    private String sourceTree;
    private Array children;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getIsa() {
        return isa;
    }

    public void setIsa(String isa) {
        this.isa = isa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSourceTree() {
        return sourceTree;
    }

    public void setSourceTree(String sourceTree) {
        this.sourceTree = sourceTree;
    }

    public Array getChildren() {
        return children;
    }

    public void setChildren(Array children) {
        this.children = children;
    }
}
