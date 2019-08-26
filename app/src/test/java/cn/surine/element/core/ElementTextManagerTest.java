package cn.surine.element.core;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Intro：
 *
 * @author sunliwei
 * @date 2019-08-16 13:01
 */
public class ElementTextManagerTest {

    @Test
    public void parseString() {
        String str = ElementTextManager.abt.getInstance().parseString("我是{这是一段{Time.Hour}测试}时，{Time.minute}分");
        fail(str);
    }
}