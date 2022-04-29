package com.xlh.util;

import com.xlh.expection.BusinessException;
import jodd.util.StringUtil;
import org.nfunk.jep.JEP;
import org.nfunk.jep.Node;
import org.nfunk.jep.ParseException;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: xielinhao
 * @title: ExpressionUtil
 * @projectName: holeturn
 * @description: 数学公式解析计算
 * @date: 11:09 2022/4/25
 */
public class ExpressionUtil {

    /**
     * 动态配置公式方式
     *
     * @param mapList
     * @param conditionExpr
     * @param formulaExpr
     * @param evaluate
     */
    public static void workOutListMap(List<Map> mapList, String conditionExpr, String formulaExpr, String evaluate) {
        for (Map map : mapList) {
            JEP jep = getJEP(map);
            workOutKey(jep, map, conditionExpr, formulaExpr, evaluate);
        }
    }

    /**
     * 动态配置公式方式
     *
     * @param map
     * @param conditionExpr
     * @param formulaExpr
     * @param evaluate
     */
    public static void workOutMap(Map map, String conditionExpr, String formulaExpr, String evaluate) {
        JEP jep = getJEP(map);
        workOutKey(jep, map, conditionExpr, formulaExpr, evaluate);
    }


    /**
     * 计算出表达式并填充
     *
     * @param jep
     * @param map
     * @param conditionExpr
     * @param formulaExpr
     * @param evaluate
     */
    private static void workOutKey(JEP jep, Map map, String conditionExpr, String formulaExpr, String evaluate) {
        //如果没有条件
        if (StringUtil.isEmpty(conditionExpr)) {
            map.put(evaluate, workOutSingle(jep, formulaExpr));
            //如果有条件 且条件为true
        } else if (workOutBool(jep, conditionExpr)) {
            map.put(evaluate, workOutSingle(jep, formulaExpr));
        }
    }

    /**
     * 判断条件表达式
     *
     * @param jep
     * @param expression
     * @return
     */
    private static boolean workOutBool(JEP jep, String expression) {
        return (Double) workOutSingle(jep, expression) > 0;
    }

    /**
     * 计算表达式的值
     *
     * @param jep
     * @param expression
     * @return
     */
    private static Object workOutSingle(JEP jep, String expression) {
        Object result = null;
        try { //执行
            Node parse = jep.parse(expression);
            result = jep.evaluate(parse);
        } catch (ParseException e) {
            throw new BusinessException(101, "公式表达式解析失败");
        }
        if (result == null) {
            throw new BusinessException(101, "公式表达式解析失败");
        }
        return result;
    }

    /**
     * 获取填充好变量的JEP对象
     *
     * @param param
     * @return
     */
    private static JEP getJEP(Map param) {
        JEP jep = new JEP();
        Set<Map.Entry> set = param.entrySet();
        for (Map.Entry entry : set) {
            Object entryValue = entry.getValue();
            String entryKey = (String) entry.getKey();
            jep.addVariable(entryKey, entryValue);
        }
        return jep;
    }
}
