/**
 * Copyright (c) 2015, AnHui Xin Hua She Group. All rights reserved.
 */
package cn.ahyc.yjz.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ahyc.yjz.dto.ExpressionColumn;
import cn.ahyc.yjz.model.Period;
import cn.ahyc.yjz.service.ProfitService;
import cn.ahyc.yjz.util.Constant;

/**
 * @ClassName: ProfitController
 * @Description: 利润表
 * @author chengjiarui 1256064203@qq.com
 * @date 2015年10月23日 下午3:39:25
 * 
 */
@Controller
@RequestMapping("/profit")
public class ProfitController extends BaseController {

    @Autowired
    private ProfitService profitService;

    public ProfitController() {
        this.pathPrefix = "module/profit/";
	}

    /**
     * 页面
     * 
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/main")
    public String main(Model model, HttpSession session) {
        Period period = (Period) session.getAttribute(Constant.CURRENT_PERIOD);
        model.addAttribute("searchtPeriod", period.getCurrentPeriod());
        return view("main");
    }
    
    /**
     * 数据列表
     * 
     * @param model
     * @param session
     * @param searchtPeriod
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> list(Model model, HttpSession session, Integer searchtPeriod,
            HttpServletRequest request) {
        
        Period period = (Period) session.getAttribute(Constant.CURRENT_PERIOD);
        Long bookId = period.getBookId();
        Integer currentPeriod;
        if (searchtPeriod != null) {
            currentPeriod= searchtPeriod;
        } else {
            currentPeriod = period.getCurrentPeriod();
        }
        List<Map<String, Object>> list;
        List<ExpressionColumn> expList = new ArrayList<ExpressionColumn>();
        String[] cAs = getValues("cA", request);
        String[] cBs = getValues("cB", request);
        String[] cCs = getValues("cC", request);
        String[] cAVals = getValues("cAVal", request);
        String[] cBVals = getValues("cBVal", request);
        String[] cCVals = getValues("cCVal", request);
        if (cAs != null) {
            ExpressionColumn entry;
            int i = 0;
            for (String cA : cAs) {
                entry = new ExpressionColumn();
                entry.setcA(cA);
                entry.setcAVal(cAVals[i]);
                entry.setcB(cBs[i]);
                entry.setcBVal(cBVals[i]);
                entry.setcC(cCs[i]);
                entry.setcCVal(cCVals[i]);
                i++;
            }
        }
        if (expList != null && expList.size() > 0) {
            list = profitService.getListWithExpList(expList, currentPeriod, bookId);
        } else {
            list = profitService.getList(currentPeriod, bookId);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", list != null && list.size() > 0 ? list.size() : 0);
        map.put("rows", list);
        return map;
    }

    private String[] getValues(String name, HttpServletRequest request) {
        String str = request.getParameter(name);
        if (StringUtils.isBlank(str)) {
            return null;
        }
        String[] values = str.split(",", -1);
        return values;
    }

    /**
     * 数据列表
     * 
     * @param model
     * @param session
     * @param searchtPeriod
     * @return
     */
    @RequestMapping(value = "/count", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> count(Model model, HttpSession session, Integer searchtPeriod,
            @RequestBody(required = false) List<ExpressionColumn> expList) {

        Period period = (Period) session.getAttribute(Constant.CURRENT_PERIOD);
        Long bookId = period.getBookId();
        Integer currentPeriod;
        if (searchtPeriod != null) {
            currentPeriod = searchtPeriod;
        } else {
            currentPeriod = period.getCurrentPeriod();
        }
        List<Map<String, Object>> list;
        if (expList != null) {
            list = profitService.getListWithExpList(expList, currentPeriod, bookId);
        } else {
            list = profitService.getList(currentPeriod, bookId);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", list != null && list.size() > 0 ? list.size() : 0);
        map.put("rows", list);
        return map;
    }
}
