package com.xlh.test;

import com.xlh.util.ExpressionUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: xielinhao
 * @title: JepTest
 * @projectName: holeturn
 * @description:
 * @date: 11:10 2022/4/25
 */
public class JepTest {
    public static void main(String[] args) {

        List<Map> mapList = new ArrayList<>();
        Map map = new HashMap() {{
            put("a", 4);
            put("b", 0);
            put("c", 3);
        }};
        Map map2 = new HashMap() {{
            put("a", 4);
            put("b", 0);
            put("c", 3);
        }};
        Map map3 = new HashMap() {{
            put("ecpm", 400);
            put("n", 1);
            put("c", 3);
        }};
        mapList.add(map);
        mapList.add(map2);

        ExpressionUtil.workOutListMap(mapList, "a > 3 && b > 1", "a+b+c * 2", "res");
        ExpressionUtil.workOutListMap(mapList, "a > 3 && b < 1", "a+b+c * 3", "res");
        ExpressionUtil.workOutListMap(mapList, "a < 0", "a+b+c", "res");
        ExpressionUtil.workOutMap(map3, "n <= 3", "ecpm * (4.5 - 0.5 * n)", "res");
        System.out.println(mapList);
        System.out.println(map3.get("res"));
        for (Map m : mapList) {
            System.out.println(m.get("res"));
        }


    }
}
