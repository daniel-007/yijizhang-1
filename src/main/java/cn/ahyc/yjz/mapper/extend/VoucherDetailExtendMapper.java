package cn.ahyc.yjz.mapper.extend;

import java.util.List;
import java.util.Map;

import cn.ahyc.yjz.mapper.base.VoucherDetailMapper;
import cn.ahyc.yjz.model.VoucherDetail;

public interface VoucherDetailExtendMapper extends VoucherDetailMapper{
	

	/**
	 * 查询损益类记账凭证详情
	 * @param username
	 * @return
	 */
	List<VoucherDetail> selectProfitAndLoss(Map<String, Object> param);

    /**
     * 查询凭证分录合计
     * 
     * @param voucherId
     * @return
     */
    Map<String, Object> selectDetailTotal(Long voucherId);

    /**
     * 查询凭证分录列表
     * 
     * @param voucherId
     * @param bookId
     * @return
     */
    List<Map<String, Object>> selectDetailList(Map<String, Object> param);
}
