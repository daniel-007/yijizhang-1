package cn.ahyc.yjz.mapper.extend;

import cn.ahyc.yjz.mapper.base.AccountSubjectMapper;

import java.util.List;
import java.util.Map;

public interface AccountSubjectExtendMapper extends AccountSubjectMapper{

    /**
     * 根据大分类获取所有会计科目.
     *
     * @param param
     * @return
     */
    List<Map<String, Object>> getSubjectsByCategoryId(Map<String, Object> param);

    /**
     * 根据父级id获取所有子级.
     *
     * @param param
     * @return
     */
    List<Map<String, Object>> getChildrenSubjectsByCategoryId(Map<String, Object> param);
}