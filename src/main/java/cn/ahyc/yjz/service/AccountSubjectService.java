package cn.ahyc.yjz.service;

import cn.ahyc.yjz.model.AccountSubject;

import java.util.List;
import java.util.Map;

/**
 * Created by Joey Yan on 15-9-24.
 */
public interface AccountSubjectService {


    List<AccountSubject> getCategoriesByCode(Long subjectCode, Long bookId);

    List<Map> getSubjectsByCategoryId(Long categoryId, Long bookId);

    AccountSubject getSubjectById(Long subjectId);

    void editAccountSubject(AccountSubject accountSubject, Long parentSubjectCodeBack, Long parentSubjectCode) throws Exception;

    List<AccountSubject> getCategoriesByCategoryId(Long categoryId, Long bookId);

    void delete(Long subjectId) throws Exception;

    Map allSubjectTreeData(Long bookId, String keyword);

    void initDataEdit(AccountSubject accountSubject) throws Exception;

    void calculate(Long bookId, Long category_subject_code) throws Exception;

    Map balance(Long bookId, Long category_subject_code);
}
