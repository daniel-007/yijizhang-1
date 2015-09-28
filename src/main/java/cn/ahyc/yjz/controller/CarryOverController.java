package cn.ahyc.yjz.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public CarryOverController() {
		this.pathPrefix = this.pathPrefix + "carryOver/";
	}

	@Autowired
	private VoucherService voucherService;
	/**
	 * 初始化页面.
	 *
	 * @return
	 */
	@RequestMapping(value = ("/main"))
	public String main(Model model) {
		return view("carryOver/main");
	}
	/**
	 * 凭证字.
	 *
	 * @return
	 */
    @RequestMapping(value = ("/category/detail"))
    @ResponseBody
    public List<CompanyCommonValue> getCategoryDetail() {
    	List<CompanyCommonValue> categoryDetails=voucherService.queryVoucherWordList();
        return categoryDetails;
    }
    /**
	 * 结转损益.
	 *
	 * @return
	 */
    @RequestMapping(value = ("/complete"))
	@ResponseBody
	public Map<String, Boolean> complete( String summary, String voucherWord) {
			Map<String, Boolean> map = new HashMap<String, Boolean>();
			//保存业务数据
			
			int result = 1;
			map.put("result", result > 0 ? true : false);
			return map;
	}
}
