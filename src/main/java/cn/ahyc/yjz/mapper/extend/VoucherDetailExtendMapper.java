package cn.ahyc.yjz.mapper.extend;

import cn.ahyc.yjz.mapper.base.VoucherDetailMapper;
import cn.ahyc.yjz.model.VoucherDetail;

import java.util.List;
import java.util.Map;

public interface VoucherDetailExtendMapper extends VoucherDetailMapper {


    /**
     * 查询损益类记账凭证详情
     *
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


    /**
     * 凭证查询.
     *
     * @param param
     * @return
     */
    List<Map> selectVoucherDetailForSearch(Map param);

    /**
     * 根据关键字凭证查询.
     *
     * @param param
     * @return
     */
    List<Map> selectVoucherDetailForSearchByKeyWord(Map param);

    /**
     * 整理凭证字号.
     *
     * @param periodId
     */
    void resetVoucherNo(Long periodId);
}
