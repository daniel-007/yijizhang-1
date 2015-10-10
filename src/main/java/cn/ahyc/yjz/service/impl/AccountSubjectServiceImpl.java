package cn.ahyc.yjz.service.impl;

import cn.ahyc.yjz.mapper.extend.AccountSubjectExtendMapper;
import cn.ahyc.yjz.model.AccountSubject;
import cn.ahyc.yjz.model.AccountSubjectExample;
import cn.ahyc.yjz.model.AccountSubjectExtExample;
import cn.ahyc.yjz.service.AccountSubjectService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
    public List<Map> getSubjectsByCategoryId(Long categoryId, Long bookId) {

        //获取父级.
        AccountSubject parentSubject = accountSubjectMapper.selectByPrimaryKey(categoryId);

        Map map = new HashMap();
        map.put("parent_subject_code", parentSubject.getSubjectCode());
        map.put("bookId", bookId);

        List<Map> subjects = accountSubjectMapper.getSubjectsByCategoryId(map);
        subjects = this.setChildrenSubject(subjects, map, "");

        map.put("id", -1);
        map.put("text", "无");
        map.put("level", -1);
        map.put("next_level_length", first_level_subject_len - 1);
        map.put("subject_name", "无");
        map.put("subject_code", subjects.get(0).get("subject_code").toString().substring(0, 1));

        //添加第一级
        List<Map> new_subjects = new ArrayList<Map>();
        new_subjects.add(map);
        new_subjects.addAll(subjects);

        return new_subjects;
    }

    private List<Map> setChildrenSubject(List<Map> subjects, Map map, String categoryDatailParentSubjectCode) {

        for (Map sub : subjects) {

            if (!StringUtils.isEmpty(categoryDatailParentSubjectCode)) {
                sub.put("category_datail_parent_subject_code", categoryDatailParentSubjectCode);
            } else {
                categoryDatailParentSubjectCode = sub.get("category_datail_parent_subject_code").toString();
            }

            String state = sub.get("state").toString();
            if ("closed".equals(state)) {
                map.put("parent_subject_code", sub.get("subject_code").toString());
                List<Map> children = accountSubjectMapper.getChildrenSubjectsByCategoryId(map);
                sub.put("children", children);
                setChildrenSubject(children, map, categoryDatailParentSubjectCode);
            }

        }

        return subjects;
    }


    @Override
    public AccountSubject getSubjectById(Long subjectId) {
        return accountSubjectMapper.selectByPrimaryKey(subjectId.longValue());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editAccountSubject(AccountSubject accountSubject, Long parentSubjectCodeBack, Long parentSubjectCode) throws Exception {

        if (parentSubjectCode == -1) {
            parentSubjectCode = parentSubjectCodeBack;
        }

        accountSubject.setParentSubjectCode(parentSubjectCode);
        accountSubject.setBaseFlag(1);
        Date now = new Date();
        accountSubject.setModifyTime(now);

        if (accountSubject.getId() == -1) {
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
    @Transactional(rollbackFor = Exception.class)
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
    @Transactional(rollbackFor = Exception.class)
    public void initDataEdit(AccountSubject accountSubject) throws Exception {
        accountSubjectMapper.updateByPrimaryKey(accountSubject);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void calculate(Long bookId, Long category_subject_code) throws Exception {

        List<Map> childSums = accountSubjectMapper.getLastChildSum(bookId);
        calculateChildren(childSums, bookId);

    }

    @Override
    public Map balance(Long bookId, Long category_subject_code) {

        Map result = new HashMap();
        result.put("isBalance", false);

        Map initLeftBalance = accountSubjectMapper.getInitLeftBalance(bookId);
        Map totalBalance = accountSubjectMapper.getTotalBalance(bookId);
        result.put("initLeftBalance", initLeftBalance);
        result.put("totalBalance", totalBalance);

        //期初余额借贷差值
        BigDecimal initLeftBalance_dValue = (BigDecimal) initLeftBalance.get("dValue");
        //本年累计借贷差值
        BigDecimal totalBalance_dValue = (BigDecimal) totalBalance.get("dValue");

        if (initLeftBalance_dValue.compareTo(BigDecimal.ZERO) == 0 && totalBalance_dValue.compareTo(BigDecimal.ZERO) == 0) {
            result.put("isBalance", true);
        }

        return result;
    }

    private void calculateChildren(List<Map> sums, Long bookId) {

        Set<Long> parent_code_set = new HashSet<Long>();

        for (Map map : sums) {

            AccountSubject accountSubject = new AccountSubject();
            boolean isUpdate = false;

            Long parent_subject_code = (Long) map.get("parent_subject_code");
            Long subject_code = (Long) map.get("subject_code");
            Object sum_total_debit = map.get("sum_total_debit");
            Object sum_total_credit = map.get("sum_total_credit");
            Object sum_initial_left = map.get("sum_initial_left");
            Object sum_year_occur_amount = map.get("sum_year_occur_amount");

            if (sum_total_debit != null) {
                isUpdate = true;
                accountSubject.setTotalDebit((BigDecimal) sum_total_debit);
            }
            if (sum_total_credit != null) {
                isUpdate = true;
                accountSubject.setTotalCredit((BigDecimal) sum_total_credit);
            }
            if (sum_initial_left != null) {
                isUpdate = true;
                accountSubject.setInitialLeft((BigDecimal) sum_initial_left);
            }
            if (sum_year_occur_amount != null) {
                isUpdate = true;
                accountSubject.setYearOccurAmount((BigDecimal) sum_year_occur_amount);
            }

            //是否需要更新数据库。
            if (isUpdate) {
                AccountSubjectExample example = new AccountSubjectExample();
                AccountSubjectExample.Criteria criteria = example.createCriteria();
                criteria.andBookIdEqualTo(bookId);
                criteria.andSubjectCodeEqualTo(subject_code);

                accountSubjectMapper.updateByExampleSelective(accountSubject, example);
            }

            /**
             * 是否是会计科目父级。如果是父级科目，则该科目父级是负数。
             */
            if (parent_subject_code > 0) {
                parent_code_set.add(parent_subject_code);
            }

        }

        //递归计算父级节点.
        List<Long> parent_code_list = new ArrayList<Long>(parent_code_set);
        if (!parent_code_list.isEmpty()) {
            List<Map> childSums = accountSubjectMapper.getParentSum(parent_code_list, bookId);
            calculateChildren(childSums, bookId);
        }
    }

}
