/**
 * Copyright (c) 2015, AnHui Xin Hua She Group. All rights reserved.
 */
package cn.ahyc.yjz.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.ahyc.yjz.service.ProfitService;

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
    public String voucher(Model model, HttpSession session) {
        return view("main");
    }
    
}
