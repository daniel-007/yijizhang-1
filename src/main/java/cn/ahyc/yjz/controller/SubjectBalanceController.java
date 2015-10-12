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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ahyc.yjz.model.Period;
import cn.ahyc.yjz.service.SubjectBalanceService;
import cn.ahyc.yjz.util.Constant;

@Controller
@RequestMapping("/subjectBalance")
public class SubjectBalanceController extends BaseController {

    @Autowired
    private SubjectBalanceService subjectBalanceService;

    public SubjectBalanceController() {
        this.pathPrefix = "module/subjectBalance/";
	}

    @RequestMapping("/subjectBalanceList")
    @ResponseBody
    public Map<String, Object> subjectBalanceList(Long subjectCode, Long periodId, HttpSession session) {
        Period period = (Period) session.getAttribute(Constant.CURRENT_PERIOD);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        if (subjectCode != null) {
            list = subjectBalanceService.querySubjectBalanceList(subjectCode, period.getId());
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", list != null && list.size() > 0 ? list.size() : 0);
        map.put("rows", list);
        return map;
    }

}
