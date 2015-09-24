package cn.ahyc.yjz.service;/**
 * AccountBookService
 *
 * @author:sanlai_lee@qq.com
 * @date: 15/9/23
 */

import java.util.List;

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

}
