package cn.ahyc.yjz.controller;

import cn.ahyc.yjz.model.AccountSubject;
import cn.ahyc.yjz.service.AccountSubjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
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
    Long bookId = 1l;


    @Resource
    private AccountSubjectService accountSubjectService;

    /**
     * 跳转到科目说明页面.
     *
     * @return
     */
    @RequestMapping("/tip")
    public String tip() {
        return view("accountSubject/tip");
    }


    /**
     *
     * @param subjectId
     * @return
     */
    @RequestMapping("/delete/{subjectId}")
    @ResponseBody
    public Map<String, Object> delete(@PathVariable("subjectId") Long subjectId) {

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", true);

        try {
            accountSubjectService.delete(subjectId);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("success", false);
            result.put("msg", e.getMessage());
        }
        return result;
    }


    /**
     * 会计科目新增、修改统一入口.
     *
     * @param accountSubject
     * @param parentSubjectIdBack 添加首级科目需要.
     * @return
     */
    @RequestMapping(value = ("/edit"))
    @ResponseBody
    public Map<String, Object> edit(AccountSubject accountSubject, @RequestParam("parent_subject_id_back") Integer parentSubjectIdBack) {

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", true);

        try {
            accountSubjectService.editAccountSubject(accountSubject, parentSubjectIdBack);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("success", false);
            result.put("msg", e.getMessage());
        }
        return result;
    }


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
    @RequestMapping("/edit/{opt}/category/{categoryId}")
    public String editPage(
            @PathVariable("opt") String opt
            , @PathVariable("categoryId") Integer categoryId
            , Integer subjectId
            , Model model) {

        AccountSubject subject = new AccountSubject();
        subject.setBookId(bookId);
        int level = 1;

        model.addAttribute("opt", opt);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("parentId", subjectId);
        model.addAttribute("subjectId", subjectId);
        model.addAttribute("accountSubject", subject);

        if (subjectId != -1) {
            subject = accountSubjectService.getSubjectById(subjectId);
            Integer parentId = subject.getParentSubjectId();
            model.addAttribute("subjectId", subject.getId());

            if ("edit".equals(opt)) {
                level = subject.getLevel();
                model.addAttribute("accountSubject", subject);
                model.addAttribute("parentId", level == 1 ? -1 : parentId);
            } else {
                model.addAttribute("subjectId", -1);
                level = subject.getLevel() + 1;
            }
        }

        model.addAttribute("level", level);

        return view("accountSubject/edit");
    }


    /**
     * 会计小分类.
     *
     * @return
     */
    @RequestMapping("/category/detail")
    @ResponseBody
    public List<AccountSubject> getCategoryDetailByCategoryId(@RequestParam("category_id") Integer categoryId) {
        List<AccountSubject> categoryDetails = accountSubjectService.getCategoriesByCategoryId(categoryId);
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
    public List<Map<String, Object>> getSubject(@PathVariable("id") Integer categoryId) {

        return accountSubjectService.getSubjectsByCategoryId(categoryId, bookId);
    }


}
