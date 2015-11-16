package cn.ahyc.yjz.service.impl;

import cn.ahyc.yjz.dto.ReportRow;
import cn.ahyc.yjz.mapper.extend.CashFlowExtendMapper;
import cn.ahyc.yjz.mapper.extend.SubjectBalanceExtendMapper;
import cn.ahyc.yjz.model.Period;
import cn.ahyc.yjz.service.CashFlowService;
import cn.ahyc.yjz.util.MyAviator;
import com.googlecode.aviator.Expression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Joey Yan on 15-11-3.
 */
@Service
public class CashFlowServiceImpl implements CashFlowService {

    @Autowired
    private CashFlowExtendMapper cashFlowExtendMapper;
    @Autowired
    private SubjectBalanceExtendMapper subjectBalanceExtendMapper;

    @Override
    public List<Map> cashflows(Period period, Integer startPeriod, Integer endPeriod) {

        List<ReportRow> colList = cashFlowExtendMapper.selectCashFlowExpressionColumn();
        List<Expression> compileList = new ArrayList();
        Map<String, Object> envMap = new HashMap();
        // 编译表达式、设置变量
        for (ReportRow col : colList) {
            MyAviator.compile(compileList, MyAviator.getEnvAndExpression(envMap, col.getcB(), colList));
            MyAviator.compile(compileList, MyAviator.getEnvAndExpression(envMap, col.getcC(), colList));
        }
        // 获取变量值
        envMap = MyAviator.getEnvValueNew(envMap, period.getCurrentPeriod(), period.getBookId(),
                subjectBalanceExtendMapper);
        List<Map> list = new ArrayList<>();
        Map<String, Object> map;
        // 执行表达式
        int j = 0;
        for (ReportRow col : colList) {
            map = new HashMap();
            map.put("cA", col.getcA());
            map.put("cAVal", col.getcA());
            map.put("cB", col.getcB());
            map.put("cC", col.getcC());
            map.put("cBVal", compileList.get(j++).execute(envMap));
            map.put("cCVal", compileList.get(j++).execute(envMap));
            list.add(map);
        }
        return list;
    }
}
