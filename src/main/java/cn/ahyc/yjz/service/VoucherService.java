package cn.ahyc.yjz.service;/**
 * AccountBookService
 *
 * @author:sanlai_lee@qq.com
 * @date: 15/9/23
 */

import java.util.List;

import cn.ahyc.yjz.model.AccountSubject;
import cn.ahyc.yjz.model.CompanyCommonValue;
import cn.ahyc.yjz.model.Voucher;
import cn.ahyc.yjz.model.VoucherDetail;

public interface VoucherService {

    /**
     * 创建凭证及凭证明细
     * 
     * @param voucher
     * @param details
     */
    void save(Voucher voucher, List<VoucherDetail> details);

    /**
     * 获取凭证字列表
     * 
     * @return
     */
    List<CompanyCommonValue> queryVoucherWordList();

    /**
     * 获取凭证明细列表
     * 
     * @param voucherId
     * @return
     */
    List<VoucherDetail> queryVoucherDetailList(Long voucherId);

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

}
