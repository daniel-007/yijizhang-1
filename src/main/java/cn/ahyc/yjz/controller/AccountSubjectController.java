package cn.ahyc.yjz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
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

    /**
     * 会计科目管理页面入口.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = ("/main"))
    public String main(Model model) {

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("id", 1);
        map1.put("title", "资产");
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("id", 2);
        map2.put("title", "负债");
        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("id", 3);
        map3.put("title", "共同");
        Map<String, Object> map4 = new HashMap<String, Object>();
        map4.put("id", 4);
        map4.put("title", "权益");
        Map<String, Object> map5 = new HashMap<String, Object>();
        map5.put("id", 5);
        map5.put("title", "成本");
        Map<String, Object> map6 = new HashMap<String, Object>();
        map6.put("id", 6);
        map6.put("title", "损益");

        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        list.add(map5);
        list.add(map6);

        model.addAttribute("categories", list);
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

        List<Map<String, Object>> fakeData = this.fakeData(categoryId.toString());

        model.addAttribute("categories", fakeData);

        return view("accountSubject/edit");
    }


    /**
     * 会计小分类.
     *
     * @return
     */
    @RequestMapping("/category/detail")
    @ResponseBody
    public List<Map<String, Object>> getCategoryDetail() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("id", 1);
        map1.put("text", "流动资产");
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("id", 2);
        map2.put("text", "长期资产");

        list.add(map1);
        list.add(map2);

        return list;
    }


    /**
     * 会计大分类列表.
     *
     * @param categoryId
     * @return
     */
    @RequestMapping("/category/{id}/subjects")
    @ResponseBody
    public List<Map<String, Object>> getSubject(@PathVariable("id") Integer categoryId) {

        String prex = categoryId.toString();

        return this.fakeData(prex);
    }


    private List<Map<String, Object>> fakeData(String prex) {

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", prex.concat("001"));
        map.put("text", "现金");
        list.add(map);

        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("id", prex.concat("009"));
        map1.put("text", "其他货币资金");

        List<Map<String, Object>> list11 = new ArrayList<Map<String, Object>>();
        Map<String, Object> map11 = new HashMap<String, Object>();
        map11.put("id", prex.concat("00901"));
        map11.put("text", "外埠存款");
        list11.add(map11);

        map1.put("children", list11);
        list.add(map1);

        return list;
    }
}
