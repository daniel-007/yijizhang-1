package cn.ahyc.yjz.service.impl;

import cn.ahyc.yjz.mapper.base.AccountSubjectMapper;
import cn.ahyc.yjz.mapper.base.AccountSubjectTemplateMapper;
import cn.ahyc.yjz.model.*;
import cn.ahyc.yjz.service.AccountSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Joey Yan on 15-9-24.
 */
@Service
public class AccountSubjectServiceImpl implements AccountSubjectService {

    @Autowired
    private AccountSubjectMapper accountSubjectMapper;

    @Override
    public List<AccountSubject> getCategoriesByCode(Integer subjectCode) {
        AccountSubjectExample example = new AccountSubjectExample();

        AccountSubjectExample.Criteria criteria = example.createCriteria();
        criteria.andSubjectCodeEqualTo(subjectCode);

        return accountSubjectMapper.selectByExample(example);
    }

    @Override
    public List<Map<String, Object>> getSubjectsByCategoryId(Integer categoryId, Long bookId) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("categoryId", categoryId);
        map.put("bookId", bookId);

        List<Map<String, Object>> subjects = accountSubjectMapper.getSubjectsByCategoryId(map);
        subjects = this.setChildrenSubject(subjects, map, "", "");

        return subjects;
    }

    /**
     * 填充子级地址.
     *
     * @param subjects
     * @param map
     * @param category_detail_id
     * @param category_datail_parent_subject_id
     * @return
     */
    private List<Map<String, Object>> setChildrenSubject(
            List<Map<String, Object>> subjects
            , Map<String, Object> map
            , String category_detail_id
            , String category_datail_parent_subject_id) {

        for (Map<String, Object> sub : subjects) {

            category_detail_id = sub.getOrDefault("category_detail_id", category_detail_id).toString();
            category_datail_parent_subject_id = sub.getOrDefault("category_datail_parent_subject_id", category_datail_parent_subject_id).toString();

            sub.put("state", "open");
            sub.put("text", sub.get("subject_name"));
            sub.put("category_detail_id", category_detail_id);
            sub.put("category_datail_parent_subject_id", category_datail_parent_subject_id);


            Long id = (Long) sub.get("id");
            map.put("parentSubjectId", id);
            List<Map<String, Object>> children = accountSubjectMapper.getChildrenSubjectsByCategoryId(map);
            if (!children.isEmpty()) {
                sub.put("state", "closed");
                sub.put("children", children);
                this.setChildrenSubject(children, map, category_detail_id, category_datail_parent_subject_id);
            }
        }

        return subjects;
    }

}
