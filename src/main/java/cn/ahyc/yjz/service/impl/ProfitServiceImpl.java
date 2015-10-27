package cn.ahyc.yjz.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;

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

    @Autowired
    private SubjectBalanceExtendMapper subjectBalanceExtendMapper;

    private static Map<String, Object> getEnv(String expStr) {
        Map<String, Object> map = new HashMap<String, Object>();
        expStr = expStr.replaceAll("[\\\" \\\"=\\\\+\\-/*/]", "&");
        String[] exps = expStr.split("&");
        for (String exp : exps) {
            if (!map.containsKey(exp)) {
                map.put(exp, null);
            }
        }
        return map;
    }

    private static Map<String, Object> getValue(Map<String, Object> map, Integer currentPeriod, Long bookId,
            SubjectBalanceExtendMapper subjectBalanceExtendMapper) {
        ExecutorService executor = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(map.size());
        Iterator<Entry<String, Object>> it = map.entrySet().iterator();
        Entry<String, Object> entry;
        String key;
        while (it.hasNext()) {
            entry = it.next();
            System.out.println("while start " + entry.getKey());
            key = String.valueOf(entry.getKey());
            map.put(key, executor
                    .submit(new ExpressionThread(key, latch, currentPeriod, bookId, subjectBalanceExtendMapper)));
            System.out.println("while end " + entry.getKey());
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
        return map;
    }

    private static List<Expression> compile(List<Expression> list, String expression) {
        // 编译表达式
        Expression compiledExp = AviatorEvaluator.compile(expression);
        list.add(compiledExp);
        return list;
    }
}
