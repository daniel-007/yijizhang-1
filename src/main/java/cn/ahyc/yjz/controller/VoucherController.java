/**
 * Copyright (c) 2015, AnHui Xin Hua She Group. All rights reserved.
 */
package cn.ahyc.yjz.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    public String voucher(Model model, Long voucherId) {
        Voucher voucher;
        if (voucherId != null) {
            voucher = voucherService.queryVoucher(voucherId);
        } else {
            voucher = new Voucher();
        }
        model.addAttribute("voucher", voucher);
        // TODO "1" "1L"
        model.addAttribute("period", "1");
        model.addAttribute("voucherNo", voucherService.queryNextVoucherNo(1L));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        model.addAttribute("voucherTime", dateFormat.format(new Date()));
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

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(Model model, HttpServletRequest request, Voucher voucher) {

        String summarys = request.getParameter("summary");
        String subjectCodes = request.getParameter("subjectCode");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // TODO "1L"
            voucher.setPeriodId(1L);
            /** 组织凭证明细数据 **/
            List<VoucherDetail> details = new ArrayList<VoucherDetail>();
            if (StringUtils.isNoneBlank(subjectCodes)) {
                String[] summaryArr = StringUtils.split(summarys, ",");
                String[] subjectCodeArr = StringUtils.split(subjectCodes, ",");
                VoucherDetail voucherDetail;
                for (int i = 0; i < subjectCodeArr.length; i++) {
                    voucherDetail = new VoucherDetail();
                    voucherDetail.setSummary(summaryArr[i]);
                    voucherDetail.setSubjectCode(Integer.valueOf(subjectCodeArr[i]));
                    details.add(voucherDetail);
                }
            }
            /** 保存 **/
            voucherService.save(voucher, details);
            map.put("success", "success");
            map.put("message", "保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message", "系统异常！");
        }
        return map;
    }
}
