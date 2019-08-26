package cn.surine.element.bean.product;

import java.io.Serializable;

/**
 * Intro：
 *
 * @author sunliwei
 * @date 2019-08-17 19:17
 */
public class Product implements Serializable {
    /**信息*/
    private ProductInfo productInfo;
    /**布局*/
    private ProductView rootView;

    public ProductView getRootView() {
        return rootView;
    }

    public ProductInfo getProductInfo() {
        return productInfo;
    }
}
