package cn.surine.element.bean.product;

import java.io.Serializable;

/**
 * Intro：
 *
 * @author sunliwei
 * @date 2019-08-14 16:27
 */
public class ProductProperties implements Serializable {
    /**属性名*/
    private String propertyName;
    /**属性类型*/
    private String propertyType;
    /**属性值*/
    private String propertyValue;

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public String getPropertyValue() {
        return propertyValue;
    }
}
