package cn.ahyc.yjz.mapper.extend;

import java.util.List;
import java.util.Map;

import cn.ahyc.yjz.mapper.base.AccountSubjectMapper;
import cn.ahyc.yjz.model.AccountSubject;

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

    /**
     * 查询所有末节点
     * 
     * @param bookId
     * @return
     */
    List<AccountSubject> selectLastChildSubject(Long bookId);
    /**
     * 根据账套id查询出损益类其中之一会计科目代码
     * 
     * @param bookId
     * @return 
     * @return
     */
    Map<String, Object> getSubjectCodeByRoot(Map<String, Object> param);
}