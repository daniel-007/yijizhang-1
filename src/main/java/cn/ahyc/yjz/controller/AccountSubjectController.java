package cn.ahyc.yjz.controller;

import cn.ahyc.yjz.model.AccountSubject;
import cn.ahyc.yjz.model.AccountSubjectVo;
import cn.ahyc.yjz.service.AccountSubjectService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 会计科目.
 * Created by Joey Yan on 15-9-22.
 */
@Controller
@RequestMapping("/account/subject")
public class AccountSubjectController extends BaseController {

    private final Integer category_subject_code = -1;
    private final Integer category_detail_subject_code = 0;

    @Resource
    private AccountSubjectService accountSubjectService;

    /**
     * 会计科目管理页面入口.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = ("/main"))
    public String main(Model model) {

        List<AccountSubject> templates = accountSubjectService.getCategoriesByCode(category_subject_code);

        model.addAttribute("categories", templates);

        return view("accountSubject/main");
    }


    /**
     * 新增 修改页面入口.
     *
     * @param categoryId
     * @param subjectId
     * @param model
     * @return
     */
    @RequestMapping("/edit/category/{categoryId}")
    public String editPage(
            @PathVariable("categoryId") Integer categoryId
            , String subjectId
            , Model model) {

        model.addAttribute("categoryId", categoryId);
        model.addAttribute("subjectId", subjectId);

        return view("accountSubject/edit");
    }


    /**
     * 会计小分类.
     *
     * @return
     */
    @RequestMapping("/category/detail")
    @ResponseBody
    public List<AccountSubject> getCategoryDetail() {
        List<AccountSubject> categoryDetails = accountSubjectService.getCategoriesByCode(0);
        return categoryDetails;
    }


    /**
     * 会计分类列表.
     *
     * @param categoryId
     * @return
     */
    @RequestMapping("/category/{id}/subjects")
    @ResponseBody
    public List<Map<String,Object>> getSubject(@PathVariable("id") Integer categoryId) {

        Long bookId = 1l;

        return accountSubjectService.getSubjectsByCategoryId(categoryId, bookId);
    }


}
