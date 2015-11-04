/**
 * @Title: MyAviator.java 
 * @Package cn.ahyc.yjz.util 
 * @Description: TODO
 * @author chengjiarui 1256064203@qq.com
 * @date 2015年11月2日 上午9:51:57 
 * @version V1.0   
 */
package cn.ahyc.yjz.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;

import cn.ahyc.yjz.mapper.extend.SubjectBalanceExtendMapper;
import cn.ahyc.yjz.thread.ExpressionThread;

/**
 * @ClassName: MyAviator
 * @Description:
 * @author chengjiarui 1256064203@qq.com
 * @date 2015年11月2日 上午9:51:57
 * 
 */
public class MyAviator {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MyAviator.class);
    /**
     * 账上取数
     */
    private static final String BOOK_DATA_FLAG = "a";

    /**
     * 编译表达式
     * 
     * @param list
     * @param expression
     * @return
     */
    public static List<Expression> compile(List<Expression> list, String expression) {
        if (StringUtils.isBlank(expression)) {
            expression = "0";
        }
        LOGGER.info("expression compile：{}", expression);
        Expression compiledExp = AviatorEvaluator.compile(expression, true);// 编译表达式缓存
        list.add(compiledExp);
        return list;
    }

    /**
     * 设置变量
     * 
     * @param envMap
     * @param expStr
     * @return
     */
    public static String getEnvAndExpression(Map<String, Object> envMap, String expStr) {
        if (StringUtils.isBlank(expStr)) {
            return null;
        }
        String result = expStr.replace("=", "");
        String str = result.replaceAll("\\+|\\*|\\/", "&");// 替换加号、乘号、除号
        str = str.replaceAll("\\-([A-Z]+)", "&$1");// 替换减号，排除@-1
        String[] exps = str.split("&");
        String param;
        Pattern pattern = Pattern.compile("([A-Z]+)([0-9]+)");
        Matcher matcher;
        for (String exp : exps) {
            param = "";
            matcher = pattern.matcher(exp);
            if (matcher.find()) { // 单元格取数 B2
                param = "cell(list," + (Integer.valueOf(matcher.group(2)) - 1) + ",c" + matcher.group(1) + "Val)";
                result = result.replace(exp, param);
            } else if (exp.indexOf("<") >= 0) { // 账上取数
                param = BOOK_DATA_FLAG + envMap.size();
                envMap.put(param, exp);
                result = result.replace(exp, param);
            }
        }
        return result;
    }

    /**
     * 获取变量值<br/>
     * 账上取数调用多线程<br/>
     * 单元格合计调用自定义函数cell，单元格计算只支持从上到下计算
     * 
     * @param map
     * @param currentPeriod
     * @param bookId
     * @param subjectBalanceExtendMapper
     * @return
     */
    public static Map<String, Object> getEnvValue(Map<String, Object> map, Integer currentPeriod, Long bookId,
            SubjectBalanceExtendMapper subjectBalanceExtendMapper) {
        ExecutorService executor = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(map.size());// 计数
        Iterator<Entry<String, Object>> it = map.entrySet().iterator();
        Entry<String, Object> entry;
        while (it.hasNext()) {
            entry = it.next();
            if (StringUtils.startsWith(entry.getKey(), BOOK_DATA_FLAG)) { // 账上取数
                executor.execute(new ExpressionThread(map, entry.getKey(), String.valueOf(entry.getValue()), latch,
                        currentPeriod, bookId, subjectBalanceExtendMapper));
            } else {
                latch.countDown();
            }
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
        return map;
    }

    /**
     * 执行表达式
     * 
     * @param expression
     * @param envMap
     * @return
     */
    public static Object execute(Expression expression, Map<String, Object> envMap) {
        return expression.execute(envMap);
    }
}