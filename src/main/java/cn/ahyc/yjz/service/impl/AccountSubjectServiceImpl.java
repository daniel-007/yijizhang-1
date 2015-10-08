package cn.ahyc.yjz.service.impl;

import cn.ahyc.yjz.mapper.extend.AccountSubjectExtendMapper;
import cn.ahyc.yjz.model.AccountSubject;
import cn.ahyc.yjz.model.AccountSubjectExample;
import cn.ahyc.yjz.model.AccountSubjectExtExample;
import cn.ahyc.yjz.service.AccountSubjectService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Joey Yan on 15-9-24.
 */
@Service
public class AccountSubjectServiceImpl implements AccountSubjectService {

    @Autowired
    private AccountSubjectExtendMapper accountSubjectMapper;

    private final int first_level_subject_len = 4;

    @Override
    public List<AccountSubject> getCategoriesByCode(Long subjectCode, Long bookId) {
        AccountSubjectExample example = new AccountSubjectExample();

        AccountSubjectExample.Criteria criteria = example.createCriteria();
        criteria.andParentSubjectCodeEqualTo(subjectCode);
        criteria.andBookIdEqualTo(bookId);

        return accountSubjectMapper.selectByExample(example);
    }

    @Override
    public List<Map<String, Object>> getSubjectsByCategoryId(Long categoryId, Long bookId) {

        //获取父级.
        AccountSubject parentSubject = accountSubjectMapper.selectByPrimaryKey(categoryId);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("parent_subject_code", parentSubject.getSubjectCode());
        map.put("bookId", bookId);

        List<Map<String, Object>> subjects = accountSubjectMapper.getSubjectsByCategoryId(map);
        subjects = this.setChildrenSubject(subjects, map, "", "");

        List<Map<String, Object>> new_subjects = new ArrayList<Map<String, Object>>();
        map.put("id", -1);
        map.put("text", "首级");
        map.put("next_level_length", first_level_subject_len - 1);
        map.put("subject_name", "首级");
        map.put("subject_code", subjects.get(0).get("subject_code").toString().substring(0, 1));
        new_subjects.add(map);
        new_subjects.addAll(subjects);

        return new_subjects;
    }

    @Override
    public AccountSubject getSubjectById(Long subjectId) {
        return accountSubjectMapper.selectByPrimaryKey(subjectId.longValue());
    }

    @Override
    public void editAccountSubject(AccountSubject accountSubject, Long parentSubjectCodeBack, Long parentSubjectCode) throws Exception {

        Long id = accountSubject.getId();

        if (parentSubjectCode == -1) {
            parentSubjectCode = parentSubjectCodeBack;
        }

        accountSubject.setParentSubjectCode(parentSubjectCode);
        accountSubject.setBaseFlag(1);
        Date now = new Date();
        accountSubject.setModifyTime(now);

        if (id == -1) {
            accountSubject.setId(null);
            accountSubjectMapper.insertSelective(accountSubject);
        } else {
            accountSubject.setCreateTime(now);
            accountSubjectMapper.updateByPrimaryKeySelective(accountSubject);
        }

    }

    @Override
    public List<AccountSubject> getCategoriesByCategoryId(Long categoryId, Long bookId) {

        AccountSubject parent_accountSubject = accountSubjectMapper.selectByPrimaryKey(categoryId);

        AccountSubjectExample example = new AccountSubjectExample();
        AccountSubjectExample.Criteria criteria = example.createCriteria();
        criteria.andParentSubjectCodeEqualTo(parent_accountSubject.getSubjectCode());
        criteria.andBookIdEqualTo(bookId);
        return accountSubjectMapper.selectByExample(example);
    }

    @Override
    public void delete(Long subjectId) throws Exception {
        this.accountSubjectMapper.deleteByPrimaryKey(subjectId);
    }

    @Override
    public Map allSubjectTreeData(Long bookId, String keyword) {

        AccountSubjectExtExample example = new AccountSubjectExtExample();
        AccountSubjectExtExample.Criteria criteria = example.createCriteria();

        criteria.andBookIdEqualTo(bookId);
        criteria.andSubjectCodeGreaterThan(0l);
        example.setOrderByClause("CAST(subject_code AS CHAR)");

        /**
         * 关键字查询.
         */
        if (!StringUtils.isEmpty(keyword)) {

            keyword = keyword.trim();

            try {
                Long kw = Long.parseLong(keyword);
                example.andSubjectCodeLike(keyword.concat("%"));
            } catch (Exception e) {
                criteria.andSubjectNameLike("%".concat(keyword).concat("%"));
            }
        }

        List<AccountSubject> accountSubjects = accountSubjectMapper.selectByExample(example);

        //损益类会计科目父级代码.
        Map map = new HashMap();
        map.put("bookId", bookId);
        Map creaseCode_map = accountSubjectMapper.getSubjectCodeByRoot(map);
        String code = creaseCode_map.getOrDefault("subject_code", "").toString();
        if (!StringUtils.isEmpty(code)) {
            code = code.substring(0, 1);
        }

        map.put("accountSubjects", accountSubjects);
        map.put("creaseCode", code);

        return map;
    }

    @Override
    public void initDataEdit(AccountSubject accountSubject) throws Exception {
        accountSubjectMapper.updateByPrimaryKey(accountSubject);
    }

    /**
     * 填充子级地址.
     *
     * @param subjects
     * @param map
     * @param category_detail_id
     * @param category_datail_parent_subject_code
     * @return
     */
    private List<Map<String, Object>> setChildrenSubject(
            List<Map<String, Object>> subjects
            , Map<String, Object> map
            , String category_detail_id
            , String category_datail_parent_subject_code) {

        for (Map<String, Object> sub : subjects) {

            category_detail_id = sub.getOrDefault("category_detail_id", category_detail_id).toString();
            category_datail_parent_subject_code = sub.getOrDefault("category_datail_parent_subject_code", category_datail_parent_subject_code).toString();

            Long subject_code = (Long) sub.get("subject_code");

            sub.put("id_back", sub.get("id").toString());
            sub.put("id", subject_code.toString());
            sub.put("state", "open");
            sub.put("text", sub.get("subject_name"));
            sub.put("category_detail_id", category_detail_id);
            sub.put("category_datail_parent_subject_code", category_datail_parent_subject_code);

            map.put("parent_subject_code", subject_code);
            List<Map<String, Object>> children = accountSubjectMapper.getChildrenSubjectsByCategoryId(map);
            if (!children.isEmpty()) {
                sub.put("state", "closed");
                sub.put("children", children);
                this.setChildrenSubject(children, map, category_detail_id, category_datail_parent_subject_code);
            }
        }

        return subjects;
    }

}
