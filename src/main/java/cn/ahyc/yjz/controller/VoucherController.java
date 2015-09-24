/**
 * Copyright (c) 2015, AnHui Xin Hua She Group. All rights reserved.
 */
package cn.ahyc.yjz.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * VoucherController
 * @author sanlai_lee@qq.com
 *
 */
@Controller
@RequestMapping("/voucher")
public class VoucherController extends BaseController{

	public VoucherController() {
		this.pathPrefix="module/voucher/";
	}

	@RequestMapping("/main")
	public String voucher(Map<String, Object> model) {


		return view("voucher");
	}

    @RequestMapping("/voucher.json")
    @ResponseBody
    public Map<String, Object> voucherJson(Map<String, Object> model) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("productid", 11);
        m.put("listprice", 11);
        list.add(m);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", 1);
        map.put("rows", list);
        return map;
    }
}
