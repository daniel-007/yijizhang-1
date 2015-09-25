package cn.ahyc.yjz.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.ahyc.yjz.model.AccountBook;
import cn.ahyc.yjz.model.DictValue;
import cn.ahyc.yjz.service.AccountBookService;

/**
 * 新建账套. Created by john Hu on 15-9-22. AccountBookController
 */
@Controller
@RequestMapping("/account/book")
public class AccountBookController extends BaseController {

	@Resource
	private AccountBookService accountBookService;

	// 新建账套首页
	@RequestMapping(value = ("/main"))
	public String main(Model model) {
		List<DictValue> subjectSystem = accountBookService.selectSubjectSystem();
		model.addAttribute("categories", subjectSystem);
		return view("accountBook/main");
	}

	// 制度说明页面
	@RequestMapping(value = ("/info"))
	public String info(Model model) {
		return view("accountBook/info");
	}

	/**
	 * 完成新建账套配置
	 * 
	 * @param accountBook
	 * @param level2
	 *            二级会计科目 level3 三级会计科目
	 * @param level4
	 *            四级会计科目 level5 五级会计科目 均指长度设置
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = ("/complete"))
	public ResponseEntity<Map<String, Object>> complete(AccountBook accountBook, int level2, int level3, int level4,
			int level5,HttpServletResponse response) throws IOException {
		Map<String, Object> result = new HashMap<String, Object>();
		String flag="0";
		int level1=4;
		//保存业务数据
		try {
			flag=accountBookService.createAccountBook(accountBook, level1,level2, level3, level4, level5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		result.put("flag",flag);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        return new ResponseEntity<Map<String, Object>>(result, headers, HttpStatus.OK);
	}
}
