package cn.ahyc.yjz.mapper.extend;

import cn.ahyc.yjz.mapper.base.VoucherMapper;
import cn.ahyc.yjz.model.Voucher;

/**
 * @ClassName: VoucherExtendMapper
 * @Description: TODO
 * @author chengjiarui 1256064203@qq.com
 * @date 2015年10月18日 上午10:13:18
 * 
 */
public interface VoucherExtendMapper extends VoucherMapper{

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