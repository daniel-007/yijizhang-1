package cn.ahyc.yjz.service.impl;

import cn.ahyc.yjz.mapper.base.BalanceSheetMapper;
import cn.ahyc.yjz.mapper.base.SubjectBalanceMapper;
import cn.ahyc.yjz.mapper.extend.SubjectBalanceExtendMapper;
import cn.ahyc.yjz.model.*;
import cn.ahyc.yjz.service.BalanceSheetService;
import com.googlecode.aviator.AviatorEvaluator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Joey Yan on 15-10-15.
 */
@Service
public class BalanceSheetServiceImpl implements BalanceSheetService {

    private Logger logger = LoggerFactory.getLogger(BalanceSheetServiceImpl.class);

    @Autowired
    private BalanceSheetMapper balanceSheetMapper;

    @Autowired
    private SubjectBalanceExtendMapper subjectBalanceExtendMapper;

    @Override
    public List<Map> balanceSheets(Period period, Long code) {

        List list = new ArrayList();
        Map map;

        BalanceSheetExtExample balanceSheetExample = new BalanceSheetExtExample();
        balanceSheetExample.andCodeFLike(code.toString().concat("%"));
        balanceSheetExample.setOrderByClause("CAST(CODE AS CHAR)");

        List<BalanceSheet> balanceSheets = balanceSheetMapper.selectByExample(balanceSheetExample);
        Map subjectBalanceMap = this.subjectBalances(period);

        for (BalanceSheet balanceSheet : balanceSheets) {

            String periodEndExp = balanceSheet.getPeriodEndExp();
            String yearBeginExp = balanceSheet.getYearBeginExp();

            map = new HashMap();
            map.put("id", balanceSheet.getId());
            map.put("name", balanceSheet.getName());
            map.put("level", balanceSheet.getLevel());
            map.put("periodEndExp", periodEndExp);
            map.put("yearBeginExp", yearBeginExp);
            map.put("periodEnd", parseAndCalcu(subjectBalanceMap, periodEndExp));
            map.put("yearBegin", parseAndCalcu(subjectBalanceMap, yearBeginExp));
            list.add(map);
        }
        return list;
    }

    @Override
    public List<BalanceSheet> balanceSheetsByParentCode(Long parentCode) {
        BalanceSheetExample balanceSheetExample = new BalanceSheetExample();
        BalanceSheetExample.Criteria criteria = balanceSheetExample.createCriteria();
        criteria.andParentCodeEqualTo(parentCode);
        return balanceSheetMapper.selectByExample(balanceSheetExample);
    }

    /**
     * =<1101>.JC
     * =<1101:1211>.JC
     * =<1101>.JC@1
     * =<1101>.JC@(2001,0)
     * =<1101>.JC + <1101>.JC@(2001,0) * (<1101>.JC+()<1101>.JC)
     *
     * @param map
     * @param express
     */
    private Object parseAndCalcu(Map map, String express) {

        Map storeMap = new HashMap();
        String replaceStr = "x"; //

        String expStr = express.replaceAll("[\\=\" \"]", "");
        expStr = expStr.replaceAll("[\\\" \\\"=\\\\+\\-/*/]", "&");
        String[] exps = expStr.split("&");

        for (String exp : exps) {

            replaceStr += replaceStr;
            express.replace(exp, replaceStr);

            if (exp.contains("@(")) { //计算其他年数据

            } else if (exp.contains("@")) { //计算本年其他期数据.


            } else {
                if (exp.contains(":")) {

                } else {
                    String code = exp.replace("<", "").replace(">", "");
                    storeMap.put(replaceStr, map.get(code));
                }
            }

        }

        return AviatorEvaluator.execute(express, storeMap);

    }

    /**
     * 获取科目余额表信息并转换成map.
     *
     * @param period
     * @return
     */
    private Map subjectBalances(Period period) {

        List<Map> balanceAndDirects = subjectBalanceExtendMapper.subjectBalanceAndDirection(period.getId());
        Map map = new HashMap();

        for (Map balanceAndDirect : balanceAndDirects) {

            String subjectCodeStr = balanceAndDirect.get("subject_code").toString();
            Integer direction = (Integer) balanceAndDirect.getOrDefault("direction", 1);
            String formatPre = "<".concat(subjectCodeStr).concat(">");

            if (direction == 1) {
                map.put(formatPre.concat(".C"), balanceAndDirect.get("initial_debit_balance"));
                map.put(formatPre.concat(""), balanceAndDirect.get("terminal_debit_balance"));
            } else {
                map.put(formatPre.concat(".C"), balanceAndDirect.get("initial_credit_balance"));
                map.put(formatPre.concat(""), balanceAndDirect.get("terminal_credit_balance"));
            }

            map.put(formatPre.concat(".DC"), balanceAndDirect.get("initial_credit_balance"));
            map.put(formatPre.concat(".JC"), balanceAndDirect.get("initial_debit_balance"));
            map.put(formatPre.concat(".JF"), balanceAndDirect.get("period_debit_occur"));
            map.put(formatPre.concat(".DF"), balanceAndDirect.get("period_credit_occur"));
            map.put(formatPre.concat(".JL"), balanceAndDirect.get("year_debit_occur"));
            map.put(formatPre.concat(".DL"), balanceAndDirect.get("year_credit_occur"));
            map.put(formatPre.concat(".JY"), balanceAndDirect.get("terminal_debit_balance"));
            map.put(formatPre.concat(".DY"), balanceAndDirect.get("terminal_credit_balance"));
            map.put(formatPre.concat(".SY"), balanceAndDirect.get("profit_loss_occur_amount"));
            map.put(formatPre.concat(".LY"), balanceAndDirect.get("profit_loss_total_occur_amount"));
        }

        return map;

    }

}
