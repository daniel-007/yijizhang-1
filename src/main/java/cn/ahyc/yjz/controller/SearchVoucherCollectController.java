package cn.ahyc.yjz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/search/vouchercollect")
public class SearchVoucherCollectController extends BaseController{

	public SearchVoucherCollectController() {
		this.pathPrefix = this.pathPrefix + "search/vouchercollect/";
	}
}
