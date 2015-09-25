/**
 * Copyright (c) 2015, AnHui Xin Hua She Group. All rights reserved.
 */
package cn.ahyc.yjz.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ahyc.yjz.model.CompanyCommonValue;
import cn.ahyc.yjz.model.Voucher;
import cn.ahyc.yjz.model.VoucherDetail;
import cn.ahyc.yjz.service.VoucherService;

/**
 * VoucherController
 *
 * @author sanlai_lee@qq.com
 */
@Controller
@RequestMapping("/voucher")
public class VoucherController extends BaseController{

    @Autowired
    private VoucherService voucherService;

	public VoucherController() {
		this.pathPrefix="module/voucher/";
	}

	@RequestMapping("/main")
    public String voucher(Model model) {
	    return view("voucher");
	}
	
    @RequestMapping("/voucherDetailList")
    @ResponseBody
    public Map<String, Object> voucherDetailList(Long voucherId) {
        List<VoucherDetail> list = new ArrayList<VoucherDetail>();
        if (voucherId != null) {
            list = voucherService.queryVoucherDetailList(voucherId);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", list != null && list.size() > 0 ? list.size() : 0);
        map.put("rows", list);
        return map;
    }

    @RequestMapping("/voucherWordList")
    @ResponseBody
    public List<CompanyCommonValue> voucherWordList() {
        List<CompanyCommonValue> list = voucherService.queryVoucherWordList();
        return list;
    }

    @RequestMapping("/save")
    @ResponseBody
    public Map<String, Object> save(Model model, HttpServletRequest request, Voucher voucher) {

        String summarys = request.getParameter("summary");
        String subjectCodes = request.getParameter("subjectCode");
        String[] summaryArr = StringUtils.split(summarys, ",");
        String[] subjectCodeArr = StringUtils.split(subjectCodes, ",");
        List<VoucherDetail> details = new ArrayList<VoucherDetail>();
        VoucherDetail voucherDetail;
        for (int i = 0; i < subjectCodeArr.length; i++) {
            voucherDetail = new VoucherDetail();
            voucherDetail.setSummary(summaryArr[i]);
            voucherDetail.setSubjectCode(Integer.valueOf(subjectCodeArr[i]));
            details.add(voucherDetail);
        }
        voucherService.save(voucher, details);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", 1);
        map.put("message", 1);
        return map;
    }
}
