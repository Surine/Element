package cn.surine.element.bean.product;

import java.io.Serializable;
import java.util.List;

/**
 * Intro：
 *
 * @author sunliwei
 * @date 2019-08-14 16:27
 */
public class ProductInfo implements Serializable {

    /**版本号*/
    private int versionCode;
    private String versionName;

    /**名称*/
    private String name;

    /**描述*/
    private String description;

    /**作者*/
    private String author;

    /**联系方式*/
    private String contact;

    /**分类标签*/
    private List<String> tags;

    /**组件大图*/
    private int src;


    public int getVersionCode() {
        return versionCode;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public String getContact() {
        return contact;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getVersionName() {
        return versionName;
    }

    public int getSrc() {
        return src;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void setSrc(int src) {
        this.src = src;
    }
}
