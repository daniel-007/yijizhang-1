package cn.ahyc.yjz.service;

import cn.ahyc.yjz.model.AccountSubject;

import java.util.List;
import java.util.Map;

/**
 * Created by Joey Yan on 15-9-24.
 */
public interface AccountSubjectService {


    List<AccountSubject> getCategoriesByCode(Integer subjectCode);

    List<Map<String, Object>> getSubjectsByCategoryId(Integer categoryId, Long bookId);

    AccountSubject getSubjectById(Integer subjectId);

    void editAccountSubject(AccountSubject accountSubject, Integer parentSubjectIdBack) throws Exception;

    List<AccountSubject> getCategoriesByCategoryId(Integer categoryId);

    void delete(Long subjectId) throws Exception;
}
