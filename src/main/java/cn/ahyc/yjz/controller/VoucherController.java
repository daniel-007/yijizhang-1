/**
 * Copyright (c) 2015, AnHui Xin Hua She Group. All rights reserved.
 */
package cn.ahyc.yjz.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ahyc.yjz.model.AccountBook;
import cn.ahyc.yjz.model.AccountSubject;
import cn.ahyc.yjz.model.CompanyCommonValue;
import cn.ahyc.yjz.model.Period;
import cn.ahyc.yjz.model.Voucher;
import cn.ahyc.yjz.model.VoucherDetail;
import cn.ahyc.yjz.service.VoucherService;
import cn.ahyc.yjz.util.Constant;

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
    public String voucher(Model model, Long voucherId, HttpSession session) {
        Voucher voucher;
        if (voucherId != null) {
            voucher = voucherService.queryVoucher(voucherId);
        } else {
            voucher = new Voucher();
        }
        model.addAttribute("voucher", voucher);
        model.addAttribute("period", ((Period) session.getAttribute(Constant.CURRENT_PERIOD)).getCurrentPeriod());
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
        // TODO
        List<Map<String, Object>> footerlist = new ArrayList<Map<String, Object>>();
        Map<String, Object> footermap = new HashMap<String, Object>();
        footermap.put("summary", "合计：");
        footerlist.add(footermap);
        map.put("footer", footerlist);
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
    public Map<String, Object> save(HttpSession session, Model model, HttpServletRequest request, Voucher voucher) {

        Map<String, Object> map = new HashMap<String, Object>();
        try {
            voucher.setPeriodId(((Period) session.getAttribute(Constant.CURRENT_PERIOD)).getId());
            /** 组织凭证明细数据 **/
            List<VoucherDetail> details = new ArrayList<VoucherDetail>();
            String[] subjectCodeArr = request.getParameterValues("subjectCode");
            if (subjectCodeArr != null) {
                if (subjectCodeArr.length <= 0) {
                    throw new RuntimeException("必须存在凭证分录");
                }
                String[] summaryArr = request.getParameterValues("summary");
                String[] debitArr = request.getParameterValues("debitArr");
                String[] crebitArr = request.getParameterValues("crebitArr");
                VoucherDetail voucherDetail;
                BigDecimal ebit;
                BigDecimal debitSum = new BigDecimal(0);
                BigDecimal crebitSum = new BigDecimal(0);
                for (int i = 0; i < subjectCodeArr.length; i++) {
                    if (StringUtils.isBlank(debitArr[i]) && StringUtils.isBlank(crebitArr[i])) {
                        throw new RuntimeException("同一凭证分录中借方金额、贷方金额必须存在一个");
                    }
                    if (StringUtils.isNotBlank(debitArr[i]) && StringUtils.isNotBlank(crebitArr[i])) {
                        throw new RuntimeException("同一凭证分录中借方金额、贷方金额只能存在一个");
                    }
                    voucherDetail = new VoucherDetail();
                    voucherDetail.setSummary(summaryArr[i]);
                    voucherDetail.setSubjectCode(Integer.valueOf(subjectCodeArr[i]));
                    if (StringUtils.isNotBlank(debitArr[i])) {
                        ebit = new BigDecimal(debitArr[i]);
                        debitSum = debitSum.add(ebit);
                        voucherDetail.setDebit(ebit);
                    }
                    if (StringUtils.isNotBlank(crebitArr[i])) {
                        ebit = new BigDecimal(crebitArr[i]);
                        crebitSum = crebitSum.add(ebit);
                        voucherDetail.setCredit(ebit);
                    }
                    details.add(voucherDetail);
                }
                if (debitSum.compareTo(crebitSum) != 0) {
                    throw new RuntimeException("记账凭证借贷不平衡");
                }
            }
            /** 保存 **/
            map.put("result", voucherService.save(voucher, details));
            map.put("message", "保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message", "系统异常！");
        }
        return map;
    }

    @RequestMapping("/accountSubjectList")
    @ResponseBody
    public List<AccountSubject> accountSubjectList(HttpSession session) {
        long bookId = ((AccountBook) session.getAttribute(Constant.CURRENT_ACCOUNT_BOOK)).getId();
        List<AccountSubject> list = voucherService.queryAccountSubjectList(bookId);
        return list;
    }
}
