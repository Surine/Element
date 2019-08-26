package cn.surine.element.bean.product;

/**
 * Intro：属性表
 * @author sunliwei
 * @date 2019-08-14 20:06
 */
public class ProductAttrs  {

    /**公共属性*/

    /**在stack中的位置*/
    public static final String LAYOUTGRAVITY = "layoutGravity";
    /**padding*/
    public static final String PADDINGLEFT = "paddingLeft";
    public static final String PADDINGRIGHT = "paddingRight";
    public static final String PADDINGTOP = "paddingTop";
    public static final String PADDINGBOTTOM = "paddingBottom";

    /**显示与隐藏*/
    public static final String VISIBILITY = "visibility";

    public static class Gravity{
        //9个方位，左上，中上……中下，右下
        public static final String LT = "LT";
        public static final String CT = "CT";
        public static final String RT = "RT";
        public static final String LC = "LC";
        public static final String C = "C";
        public static final String RC = "RC";
        public static final String LB = "LB";
        public static final String CB = "CB";
        public static final String RB = "RB";
    }



    /**Image所具有的属性列表*/
    public static class Image{
        /**图资源*/
        public static final String SRC = "src";
        /**绘制宽度*/
        public static final String WIDTH = "width";
        /**绘制高度*/
        public static final String HEIGHT = "height";
        /**绘制圆角*/
        public static final String CORNERS = "corners";
    }

    /**Text所具有的属性列表*/
    public static class Text{

        /**文本内容*/
        public static final String TEXT = "text";
        /**文本大小*/
        public static final String TEXT_SIZE = "textSize";
        /**文本颜色*/
        public static final String TEXT_COLOR = "textColor";

        /**绘制宽度*/
        @Deprecated
        public static final String WIDTH = "width";

        /**绘制高度*/
        @Deprecated
        public static final String HEIGHT = "height";

        /**字体*/
        public static final String FONT = "font";
        /**字体粗细*/
        public static final String FONT_WEIGHT = "fontWeight";
        /**绘制风格*/
        public static final String PAINT_STYLE = "paintStyle";
        /**绘制路径粗细*/
        public static final String PAINT_THICK = "paintThick";
        /**对齐方式*/
        public static final String ALIGN = "align";

    }

    /**Shape所具有的属性列表*/
    public static class Shape{
        /**Shape种类*/
        public static final String SHAPE_TYPE = "shapeType";
        /**绘制背景色*/
        public static final String BACKGROUND_COLOR = "backgroundColor";
        /**绘制宽度*/
        public static final String WIDTH = "width";
        /**绘制高度*/
        public static final String HEIGHT = "height";
        /**绘制圆角*/
        public static final String CORNERS = "corners";
    }
}
