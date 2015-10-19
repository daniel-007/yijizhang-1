package cn.ahyc.yjz.service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: VoucherTemplateService
 * @Description: TODO
 * @author chengjiarui 1256064203@qq.com
 * @date 2015年10月18日 下午5:06:25
 * 
 */
public interface VoucherTemplateService {

    /**
     * 查询明细列表
     * 
     * @param voucherTemplateId
     * @param bookId
     * @return
     */
    List<Map<String, Object>> queryDetailList(Long voucherTemplateId, long bookId);

    /**
     * 统计明细金额
     * 
     * @param voucherTemplateId
     * @return
     */
    Map<String, Object> queryDetailTotal(Long voucherTemplateId);

}
