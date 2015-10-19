package cn.ahyc.yjz.service.impl;

import cn.ahyc.yjz.mapper.base.BalanceSheetMapper;
import cn.ahyc.yjz.mapper.base.SubjectBalanceMapper;
import cn.ahyc.yjz.model.*;
import cn.ahyc.yjz.service.BalanceSheetService;
import org.apache.commons.lang3.StringUtils;
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


    @Autowired
    private BalanceSheetMapper balanceSheetMapper;

    @Autowired
    private SubjectBalanceMapper subjectBalanceMapper;

    @Override
    public List<Map> balanceSheets(Period period, Long code) {

        List list = new ArrayList();
        Map map;

        BalanceSheetExtExample balanceSheetExample = new BalanceSheetExtExample();
        balanceSheetExample.andCodeFLike(code.toString().concat("%"));
        balanceSheetExample.setOrderByClause("CAST(CODE AS CHAR)");

        List<BalanceSheet> balanceSheets = balanceSheetMapper.selectByExample(balanceSheetExample);

        for (BalanceSheet balanceSheet : balanceSheets) {
            map = new HashMap();
            map.put("id", balanceSheet.getId());
            map.put("name", balanceSheet.getName());
            map.put("level", balanceSheet.getLevel());
            map.put("periodEndExp", balanceSheet.getPeriodEndExp());
            map.put("yearBeginExp", balanceSheet.getYearBeginExp());
            map.put("periodEnd", 0);
            map.put("yearBegin", 0);
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


    /**y
     * =<1101>.JC
     * =<1101:1211>.JC
     * =<1101>.JC@1
     * =<1101>.JC@(2001,0)
     * =<1101>.JC + <1101>.JC@(2001,0) + (<1101>.JC+<1101>.JC)
     *
     * @param map
     * @param express
     */
    private static List<String> parseAndCalcu(Map map, String express) {

        List<String> list = new ArrayList<String>();


        if (!StringUtils.isEmpty(express)) {

            if (express.startsWith("=")) {
                express = express.substring(1, express.length());
            }

            int fromindex = 0;
            while (true) {
                int idx = express.indexOf("<", fromindex);
                if (idx > -1) {
                    fromindex = express.indexOf("<", idx) + 1;
                    if (fromindex > -1) {
                        list.add(express.substring(idx, fromindex));
                    } else {
                        list.add(express.substring(idx, express.length()));
                        break;
                    }
                } else {
                    break;
                }

            }
        }

        return list;

    }


    private int getIdxByChar(String charStr, String originStr) {

        return originStr.indexOf(charStr);

    }

    /**
     * 获取科目余额表信息并转换成map.
     *
     * @param period
     * @return
     */
    private Map subjectBalances(Period period) {
        SubjectBalanceExample subjectBalanceExample = new SubjectBalanceExample();
        SubjectBalanceExample.Criteria criteria = subjectBalanceExample.createCriteria();
        criteria.andPeriodIdEqualTo(period.getId());

        List<SubjectBalance> subjectBalances = subjectBalanceMapper.selectByExample(subjectBalanceExample);

        Map map = new HashMap();

        for (SubjectBalance subjectBalance : subjectBalances) {

            Long subjectCode = subjectBalance.getSubjectCode();
            String formatPre = "<".concat(subjectCode.toString()).concat(">");

            map.put(formatPre.concat(".DC"), subjectBalance.getInitialCreditBalance());
            map.put(formatPre.concat(".JC"), subjectBalance.getInitialDebitBalance());
            map.put(formatPre.concat(".JF"), subjectBalance.getPeriodDebitOccur());
            map.put(formatPre.concat(".DF"), subjectBalance.getPeriodCreditOccur());
            map.put(formatPre.concat(".JL"), subjectBalance.getYearDebitOccur());
            map.put(formatPre.concat(".DL"), subjectBalance.getYearCreditOccur());
            map.put(formatPre.concat(".JY"), subjectBalance.getTerminalDebitBalance());
            map.put(formatPre.concat(".DY"), subjectBalance.getTerminalCreditBalance());

        }


        return map;

    }

}
