/**
 * Copyright (c) 2015, AnHui Xin Hua She Group. All rights reserved.
 */
package cn.ahyc.yjz.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ahyc.yjz.model.Period;
import cn.ahyc.yjz.service.SubjectBalanceService;
import cn.ahyc.yjz.util.Constant;

/**
 * @ClassName: SubjectBalanceController
 * @Description: TODO
 * @author chengjiarui 1256064203@qq.com
 * @date 2015年10月18日 下午9:33:04
 * 
 */
@Controller
@RequestMapping("/search/subjectBalance")
public class SubjectBalanceController extends BaseController {

    @Autowired
    private SubjectBalanceService subjectBalanceService;

    public SubjectBalanceController() {
        this.pathPrefix = "module/search/subjectBalance/";
	}

    /**
     * 查看科目余额表数据.
     * 
     * @param subjectCode
     * @param periodId
     * @param session
     * @param periodFrom
     * @param periodTo
     * @param subjectCodeFrom
     * @param subjectCodeTo
     * @param level
     * @param valueNotNull
     * @return
     */
    @RequestMapping("/subjectBalanceList")
    @ResponseBody
    public Map<String, Object> subjectBalanceList(Long subjectCode, Long periodId, HttpSession session, Long periodFrom,
            Long periodTo, Long subjectCodeFrom, Long subjectCodeTo, Long level, Long valueNotNull) {
        Period period = (Period) session.getAttribute(Constant.CURRENT_PERIOD);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        if (subjectCode != null) {// TODO 记账中查看科目余额
            list = subjectBalanceService.querySubjectBalanceList(subjectCode, period.getId());
        } else {// 查询-科目余额表
            list = subjectBalanceService.querySubjectBalanceList(period.getBookId(), periodFrom, periodTo, level,
                    subjectCodeFrom, subjectCodeTo, valueNotNull);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", list != null && list.size() > 0 ? list.size() : 0);
        map.put("rows", list);
        return map;
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
        model.addAttribute("periodFrom", period.getCurrentPeriod());
        model.addAttribute("periodTo", period.getCurrentPeriod());
        model.addAttribute("level", 1);
        model.addAttribute("valueNotNull", "1");
        return view("main");
    }

    /**
     * 页面
     * 
     * @param model
     * @param session
     * @param periodFrom
     * @param periodTo
     * @param subjectCodeFrom
     * @param subjectCodeTo
     * @param level
     * @param valueNotNull
     * @return
     */
    @RequestMapping("/main2")
    public String main2(Model model, HttpSession session, Long periodFrom, Long periodTo, Long subjectCodeFrom,
            Long subjectCodeTo, Long level, Long valueNotNull) {
        if (periodFrom == null && periodTo == null) {
            Period period = (Period) session.getAttribute(Constant.CURRENT_PERIOD);
            model.addAttribute("periodFrom", period.getCurrentPeriod());
            model.addAttribute("periodTo", period.getCurrentPeriod());
        } else {
            model.addAttribute("periodFrom", periodFrom);
            model.addAttribute("periodTo", periodTo);
        }
        model.addAttribute("level", level != null && level > 0 ? level : 1);
        model.addAttribute("subjectCodeFrom", subjectCodeFrom);
        model.addAttribute("subjectCodeTo", subjectCodeTo);
        model.addAttribute("valueNotNull", valueNotNull);
        return view("main");
    }

    /**
     * 页面
     * 
     * @param model
     * @param periodFrom
     * @param periodTo
     * @param subjectCodeFrom
     * @param subjectCodeTo
     * @param level
     * @param valueNotNull
     * @return
     */
    @RequestMapping("/search")
    public String search(Model model, Long periodFrom, Long periodTo, Long subjectCodeFrom,
            Long subjectCodeTo, Long level, Long valueNotNull) {
        model.addAttribute("periodFrom", periodFrom);
        model.addAttribute("periodTo", periodTo);
        model.addAttribute("level", level != null && level > 0 ? level : 1);
        model.addAttribute("subjectCodeFrom", subjectCodeFrom);
        model.addAttribute("subjectCodeTo", subjectCodeTo);
        model.addAttribute("valueNotNull", valueNotNull);
        return view("search");
    }
}
