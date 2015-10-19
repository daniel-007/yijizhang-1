package cn.ahyc.yjz.mapper.extend;

import java.util.List;
import java.util.Map;

import cn.ahyc.yjz.mapper.base.VoucherDetailMapper;
import cn.ahyc.yjz.model.VoucherDetail;

/**
 * @ClassName: VoucherDetailExtendMapper
 * @Description: TODO
 * @author chengjiarui 1256064203@qq.com
 * @date 2015年10月18日 上午10:13:24
 * 
 */
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
    Map<String, Object> selectDetailTotal(Map<String, Object> param);

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
