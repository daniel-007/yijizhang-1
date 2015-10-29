package cn.ahyc.yjz.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;

import cn.ahyc.yjz.dto.ExpressionColumn;
import cn.ahyc.yjz.mapper.extend.ProfitExtendMapper;
import cn.ahyc.yjz.mapper.extend.SubjectBalanceExtendMapper;
import cn.ahyc.yjz.service.ProfitService;
import cn.ahyc.yjz.thread.ExpressionThread;

/**
 * @ClassName: ProfitServiceImpl
 * @Description: TODO
 * @author chengjiarui 1256064203@qq.com
 * @date 2015年10月23日 下午5:06:31
 * 
 */
@Service
public class ProfitServiceImpl implements ProfitService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfitServiceImpl.class);

    @Autowired
    private ProfitExtendMapper profitExtendMapper;

    @Autowired
    private SubjectBalanceExtendMapper subjectBalanceExtendMapper;

    /**
     * 获取变量值
     * 
     * @param map
     * @param currentPeriod
     * @param bookId
     * @param subjectBalanceExtendMapper
     * @return
     */
    private static Map<String, Object> getEnvValue(Map<String, Object> map, Integer currentPeriod, Long bookId,
            SubjectBalanceExtendMapper subjectBalanceExtendMapper) {
        ExecutorService executor = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(map.size());
        Iterator<Entry<String, Object>> it = map.entrySet().iterator();
        Entry<String, Object> entry;
        while (it.hasNext()) {
            entry = it.next();
            if (StringUtils.startsWith(entry.getKey(), "a")) { // 账上取数
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
     * 编译表达式
     * 
     * @param list
     * @param expression
     * @return
     */
    private static List<Expression> compile(List<Expression> list, String expression) {
        if (StringUtils.isBlank(expression)) {
            expression = "''";
        }
        System.out.println(expression);
        Expression compiledExp = AviatorEvaluator.compile(expression, true);
        list.add(compiledExp);
        return list;
    }

    /**
     * 设置变量
     * 
     * @param map
     * @param expStr
     * @return
     */
    private static String getEnv(Map<String, Object> map, String expStr) {
        if(StringUtils.isBlank(expStr)){
            return null;
        }
        String result = expStr.replace("=", "");
        String str = result.replaceAll("\\+|\\-/D|\\*|\\/", "&");
        String[] exps = str.split("&");
        String param;
        Pattern pattern = Pattern.compile("([A-Z]+)([0-9]+)");
        Matcher matcher;
        for (String exp : exps) {
            param = "";
            matcher = pattern.matcher(exp);
            if (matcher.find()) { // 单元格取数 B2
                param = "map" + matcher.group(2) + ".c" + matcher.group(1) + "Val";
                result = result.replace(exp, param);
            } else if (exp.indexOf("<") >= 0) { // 账上取数
                param = "a" + map.size();
                map.put(param, exp);
                result = result.replace(exp, param);
            }
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.ahyc.yjz.service.ProfitService#getList(java.lang.Integer,
     * java.lang.Long)
     */
    @Override
    public List<Map<String, Object>> getList(Integer currentPeriod, Long bookId) {
        List<ExpressionColumn> colList = profitExtendMapper.selectProfitExpressionColumn();
        List<Expression> compileList = new ArrayList<Expression>();
        Map<String, Object> envMap = new HashMap<String, Object>();
        // 编译表达式、设置变量
        for (ExpressionColumn col : colList) {
            compile(compileList, getEnv(envMap, col.getcB()));
            compile(compileList, getEnv(envMap, col.getcC()));
        }
        // 获取变量值
        envMap = getEnvValue(envMap, currentPeriod, bookId, subjectBalanceExtendMapper);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;
        map = new HashMap<String, Object>();
        map.put("cA", "项目");
        map.put("cBVal", "本期金额");
        map.put("cCVal", "上期金额");
        list.add(map);
        // 执行表达式
        int j = 0;
        for (ExpressionColumn col : colList) {
            map = new HashMap<String, Object>();
            map.put("cA", col.getcA());
            map.put("cB", col.getcB());
            map.put("cC", col.getcC());
            map.put("cBVal", compileList.get(j++).execute(envMap));
            map.put("cCVal", compileList.get(j++).execute(envMap));
            list.add(map);
            envMap.put("map" + list.size(), map);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.ahyc.yjz.service.ProfitService#countExp(java.lang.Integer,
     * java.lang.Long, java.lang.String)
     */
    @Override
    public Object countExp(Integer currentPeriod, Long bookId, String expStr) {
        List<Expression> compileList = new ArrayList<Expression>();
        Map<String, Object> envMap = new HashMap<String, Object>();
        compile(compileList, getEnv(envMap, expStr));
        envMap = getEnvValue(envMap, currentPeriod, bookId, subjectBalanceExtendMapper);
        return compileList.get(0).execute(envMap);
    }
}
