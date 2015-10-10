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
     * 更新科目余额表累计项
     * 
     * @param periodId
     */
    int insertOrUpdateSubjectSum(Long periodId);

    /**
     * 更新科目余额表累计项
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
}
