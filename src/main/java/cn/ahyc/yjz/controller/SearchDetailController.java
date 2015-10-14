package cn.ahyc.yjz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 明细账控制器.
 * Created by John Hu on 15-10-14.
 */
@Controller
@RequestMapping("/search/detail")
public class SearchDetailController  extends BaseController{
	public SearchDetailController() {
		this.pathPrefix = this.pathPrefix + "search/detail/";
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
}
