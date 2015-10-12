package cn.ahyc.yjz.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ahyc.yjz.mapper.extend.SubjectBalanceExtendMapper;
import cn.ahyc.yjz.service.SubjectBalanceService;

@Service
public class SubjectBalanceServiceImpl implements SubjectBalanceService {

    @Autowired
    private SubjectBalanceExtendMapper subjectBalanceExtendMapper;

    @Override
    public List<Map<String, Object>> querySubjectBalanceList(Long subjectCode, Long periodId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("subjectCode", subjectCode);
        map.put("periodId", periodId);
        return subjectBalanceExtendMapper.selectSubjectBalanceList(map);
    }

}
