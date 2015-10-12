package cn.ahyc.yjz.service;

import java.util.List;
import java.util.Map;

public interface SubjectBalanceService {

    /**
     * 查询科目余额
     * 
     * @param subjectCode
     * @param periodId
     * @return
     */
    List<Map<String, Object>> querySubjectBalanceList(Long subjectCode, Long periodId);

}
