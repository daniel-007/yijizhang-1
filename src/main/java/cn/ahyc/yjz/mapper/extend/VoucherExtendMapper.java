package cn.ahyc.yjz.mapper.extend;

import cn.ahyc.yjz.model.Voucher;

public interface VoucherExtendMapper {

    /**
     * 保存记账凭证，返回主键
     * 
     * @param record
     * @return
     */
    int insertSelectiveReturnId(Voucher record);

    /**
     * 根据期间id获取当前最大凭证号
     * 
     * @param periodId
     * @return
     */
    int selectMaxVoucherNo(Long periodId);
}