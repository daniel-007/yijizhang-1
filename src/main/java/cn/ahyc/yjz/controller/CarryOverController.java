package cn.ahyc.yjz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ahyc.yjz.model.CompanyCommonValue;
import cn.ahyc.yjz.service.VoucherService;

/**
 * 结转损益. Created by john Hu on 15-9-22. CarryOverController
 */
@Controller
@RequestMapping("/account/carryOver")
public class CarryOverController  extends BaseController {
	
	@Autowired
	private VoucherService voucherService;
	// 结转损益页面
	@RequestMapping(value = ("/main"))
	public String main(Model model) {
		return view("carryOver/main");
	}
	/**
	 * 凭证字.
	 *
	 * @return
	 */
    @RequestMapping("/category/detail")
    @ResponseBody
    public List<CompanyCommonValue> getCategoryDetail() {
    	List<CompanyCommonValue> categoryDetails=voucherService.queryVoucherWordList();
        return categoryDetails;
    }

}
