package cn.ahyc.yjz.service;
import java.util.List;
import java.util.Map;

import cn.ahyc.yjz.model.AccountSubject;
import cn.ahyc.yjz.model.Voucher;
import cn.ahyc.yjz.model.VoucherDetail;
import cn.ahyc.yjz.model.VoucherTemplate;
import cn.ahyc.yjz.model.VoucherTemplateDetail;

/**
 * @ClassName: VoucherService
 * @Description: TODO
 * @author chengjiarui 1256064203@qq.com
 * @date 2015年10月18日 上午10:12:35
 * 
 */
public interface VoucherService {

    /**
     * 创建凭证及凭证明细
     * 
     * @param voucher
     * @param details
     */
    String save(Voucher voucher, List<VoucherDetail> details);

    /**
     * 获取凭证明细列表
     * 
     * @param voucherId
     * @param bookId
     * @param isreversal
     * @return
     */
    List<Map<String, Object>> queryVoucherDetailList(Long voucherId, Long bookId, Long isreversal);

    /**
     * 查询记账凭证
     * 
     * @param voucherId
     * @return
     */
    Voucher queryVoucher(Long voucherId);

    /**
     * 获取下一个凭证号
     * 
     * @param periodId
     * @return
     */
    int queryNextVoucherNo(Long periodId);

    /**
     * 查询所有会计科目
     * 
     * @param bookId
     * 
     * @return
     */
    List<AccountSubject> queryAccountSubjectList(Long bookId);

    /**
     * 查询凭证分录合计
     * 
     * @param voucherId
     * @param isreversal
     * @return
     */
    Map<String, Object> queryDetailTotal(Long voucherId, Long isreversal);

    /**
     * 查询模式凭证列表
     * 
     * @return
     */
    List<VoucherTemplate> queryVoucherTemplateList();

    /**
     * 查询模式凭证
     * 
     * @param voucherTemplateId
     * @return
     */
    VoucherTemplate queryVoucherTemplate(Long voucherTemplateId);

    /**
     * 查询模式凭证详细
     * 
     * @param voucherTemplateId
     * @param long1
     * @return
     */
    List<VoucherTemplateDetail> queryVoucherTemplateDetailList(Long voucherTemplateId, Long long1);

    /**
     * 保存模式凭证
     * 
     * @param voucherTemplate
     * @param details
     */
    void saveTemplate(VoucherTemplate voucherTemplate, List<VoucherTemplateDetail> details);

    /**
     * 检查模式凭证名称是否重复
     * 
     * @param name
     * @param id
     * @return
     */
    boolean checkTemplateName(String name, Long id);

    /**
     * 删除模式凭证
     * 
     * @param id
     */
    void deleteTemplate(Long id);

    /**
     * 检查凭证号
     * 
     * @param no
     * @param periodId
     * @param id
     * @return
     */
    int checkNo(Integer no, Long periodId, Long id);

    /**
     * 删除凭证
     * 
     * @param voucherId
     * @param periodId
     */
    void delete(Long voucherId, Long periodId);

    /**
     * 查询最新的7条凭证.
     * @param map
     * @return
     */
    List<Map> latestVouchers(Map map);
}
