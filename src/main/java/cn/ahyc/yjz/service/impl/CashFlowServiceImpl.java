package cn.ahyc.yjz.service.impl;

import cn.ahyc.yjz.mapper.extend.CashFlowExtendMapper;
import cn.ahyc.yjz.model.CashFlow;
import cn.ahyc.yjz.model.Period;
import cn.ahyc.yjz.service.CashFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public List<Map> cashflows(Period period, Integer startPeriod, Integer endPeriod) {

        Map map = new HashMap();
        map.put("bookId", period.getBookId());
        map.put("startPeriod", startPeriod);
        map.put("endPeriod", endPeriod);

        return cashFlowExtendMapper.cashFlowsByPeriod(map);
    }
}
