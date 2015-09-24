package cn.ahyc.yjz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 新建账套.
 * Created by john Hu on 15-9-22.
 * AccountBookController
 */
@Controller
@RequestMapping("/account/book")
public class AccountBookController extends BaseController {
	@RequestMapping(value = ("/main"))
	public String main(Model model) {
	     return view("accountBook/main");
	}
}

