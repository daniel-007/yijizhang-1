package cn.ahyc.yjz.mapper.extend;

import java.util.List;
import java.util.Map;

import cn.ahyc.yjz.dto.SubjectBalanceDto;
import cn.ahyc.yjz.mapper.base.SubjectBalanceMapper;

public interface SubjectBalanceExtendMapper extends SubjectBalanceMapper{

	/**
	 * 查询所有损益类科目余额
	 * @param username
	 * @return
	 */
	List<SubjectBalanceDto> selectProfitAndLoss(Map<String, Object> param);

    /**
     * 新增或更新科目余额表累计项、余额项
     * 
     * @param periodId
     */
    int insertOrUpdateSubjectBalance(Long periodId);

    /**
     * 初始化下一期科目余额
     * 
     * @param param
     * @return
     */
    int insertNextPeriodSubjectBalance(Map<String, Object> param);

    /**
     * 汇总科目余额表
     * 
     * @param periodId
     * @return
     */
    int collectSubjectBalance(Long periodId);

    /**
     * 查询科目余额
     * 
     * @param map
     * @return
     */
    List<Map<String, Object>> selectSubjectBalanceList(Map<String, Object> map);
}
