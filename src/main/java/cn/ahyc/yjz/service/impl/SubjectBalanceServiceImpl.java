package cn.ahyc.yjz.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ahyc.yjz.mapper.extend.SubjectBalanceExtendMapper;
import cn.ahyc.yjz.service.SubjectBalanceService;

/**
 * @ClassName: SubjectBalanceServiceImpl
 * @Description: TODO
 * @author chengjiarui 1256064203@qq.com
 * @date 2015年10月18日 上午10:11:52
 * 
 */
@Service
public class SubjectBalanceServiceImpl implements SubjectBalanceService {

    @Autowired
    private SubjectBalanceExtendMapper subjectBalanceExtendMapper;

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.ahyc.yjz.service.SubjectBalanceService#querySubjectBalanceList(java.
     * lang.Long, java.lang.Long)
     */
    @Override
    public List<Map<String, Object>> querySubjectBalanceList(Long subjectCode, Long periodId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("subjectCode", subjectCode);
        map.put("periodId", periodId);
        return subjectBalanceExtendMapper.selectSubjectBalanceList(map);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.ahyc.yjz.service.SubjectBalanceService#querySubjectBalanceList(java.
     * lang.Long, java.lang.Integer, java.lang.Integer, java.lang.Long,
     * java.lang.Long, java.lang.Long, java.lang.Long)
     */
    @Override
    public List<Map<String, Object>> querySubjectBalanceList(Long bookId, Integer periodFrom, Integer periodTo,
            Long level, Long subjectCodeFrom, Long subjectCodeTo, Long valueNotNull) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("bookId", bookId);
        map.put("periodFrom", periodFrom);
        map.put("periodTo", periodTo);
        map.put("level", level);
        map.put("subjectCodeFrom", subjectCodeFrom);
        map.put("subjectCodeFrom", subjectCodeFrom);
        map.put("valueNotNull", valueNotNull);
        return subjectBalanceExtendMapper.selectBalanceList(map);
    }

}
