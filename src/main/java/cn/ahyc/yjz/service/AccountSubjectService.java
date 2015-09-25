package cn.ahyc.yjz.service;

import cn.ahyc.yjz.model.AccountSubject;
import cn.ahyc.yjz.model.AccountSubjectVo;

import java.util.List;
import java.util.Map;

/**
 * Created by Joey Yan on 15-9-24.
 */
public interface AccountSubjectService {


    List<AccountSubject> getCategoriesByCode(Integer subjectCode);

    List<Map<String,Object>> getSubjectsByCategoryId(Integer categoryId, Long bookId);
}
