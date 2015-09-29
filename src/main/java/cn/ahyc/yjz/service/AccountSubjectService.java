package cn.ahyc.yjz.service;

import cn.ahyc.yjz.model.AccountSubject;

import java.util.List;
import java.util.Map;

/**
 * Created by Joey Yan on 15-9-24.
 */
public interface AccountSubjectService {


    List<AccountSubject> getCategoriesByCode(Long subjectCode, Long bookId);

    List<Map<String, Object>> getSubjectsByCategoryId(Long categoryId, Long bookId);

    AccountSubject getSubjectById(Long subjectId);

    void editAccountSubject(AccountSubject accountSubject, Long parentSubjectCodeBack, Long parentSubjectCode) throws Exception;

    List<AccountSubject> getCategoriesByCategoryId(Long categoryId, Long bookId);

    void delete(Long subjectId) throws Exception;
}
