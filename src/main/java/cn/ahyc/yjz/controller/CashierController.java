package cn.ahyc.yjz.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 结账. Created by john Hu on 15-10-08. CashierController
 */
@Controller
@RequestMapping("/account/cashier")
public class CashierController  extends BaseController{
	
	public CashierController() {
		this.pathPrefix = this.pathPrefix + "cashier/";
	}
	/**
	 * 初始化页面.
	 *
	 * @return
	 */
	@RequestMapping(value = ("/main"))
	public String main(Model model) {
		return view("main");
	}

	/**
	 * 检查账套名称是否已经存在.
	 *
	 * @param name
	 * @return
	 */
	@RequestMapping("/is/balance")
	@ResponseBody
	public int isBalance(String name) {
		int flag=1;
		return flag;
	}
}
