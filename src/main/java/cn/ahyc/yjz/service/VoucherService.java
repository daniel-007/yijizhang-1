package cn.ahyc.yjz.service;/**
 * AccountBookService
 *
 * @author:sanlai_lee@qq.com
 * @date: 15/9/23
 */

import java.util.List;
import java.util.Map;

import cn.ahyc.yjz.model.AccountSubject;
import cn.ahyc.yjz.model.CompanyCommonValue;
import cn.ahyc.yjz.model.Voucher;
import cn.ahyc.yjz.model.VoucherDetail;
import cn.ahyc.yjz.model.VoucherTemplate;
import cn.ahyc.yjz.model.VoucherTemplateDetail;

public interface VoucherService {

    /**
     * 创建凭证及凭证明细
     * 
     * @param voucher
     * @param details
     */
    String save(Voucher voucher, List<VoucherDetail> details);

    /**
     * 获取凭证字列表
     * 
     * @param typeId
     * @return
     */
    List<CompanyCommonValue> queryVoucherWordList(Long typeId);

    /**
     * 获取凭证明细列表
     * 
     * @param voucherId
     * @param bookId
     * @return
     */
    List<Map<String, Object>> queryVoucherDetailList(Long voucherId, Long bookId);

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
     * @return
     */
    Map<String, Object> queryDetailTotal(Long voucherId);

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
}
