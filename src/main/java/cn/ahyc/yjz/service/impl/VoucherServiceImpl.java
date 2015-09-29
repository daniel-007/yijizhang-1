package cn.ahyc.yjz.service.impl;

/**
 * AccountBookServiceImpl
 *
 * @author:sanlai_lee@qq.com
 * @date: 15/9/23
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ahyc.yjz.mapper.base.CompanyCommonValueMapper;
import cn.ahyc.yjz.mapper.base.VoucherDetailMapper;
import cn.ahyc.yjz.mapper.extend.AccountSubjectExtendMapper;
import cn.ahyc.yjz.mapper.extend.VoucherExtendMapper;
import cn.ahyc.yjz.model.AccountSubject;
import cn.ahyc.yjz.model.CompanyCommonValue;
import cn.ahyc.yjz.model.CompanyCommonValueExample;
import cn.ahyc.yjz.model.CompanyCommonValueExample.Criteria;
import cn.ahyc.yjz.model.Voucher;
import cn.ahyc.yjz.model.VoucherDetail;
import cn.ahyc.yjz.model.VoucherDetailExample;
import cn.ahyc.yjz.service.VoucherService;

@Service
public class VoucherServiceImpl implements VoucherService {

    @Autowired
    private VoucherExtendMapper voucherExtendMapper;

    @Autowired
    private VoucherDetailMapper voucherDetailMapper;

    @Autowired
    private CompanyCommonValueMapper companyCommonValueMapper;

    @Autowired
    private AccountSubjectExtendMapper accountSubjectExtendMapper;

    @Override
    // @Transactional(rollbackFor = Exception.class)
    public void save(Voucher voucher, List<VoucherDetail> details) {
        long voucherId;
        int voucherNo;
        /** 新增、更新记账凭证 **/
        if (voucher != null && voucher.getId() != null) {
            voucherId = voucher.getId();
            voucherNo = voucher.getVoucherNo();
            voucherExtendMapper.updateByPrimaryKeySelective(voucher);
            /** 删除凭证明细 **/
            VoucherDetailExample example = new VoucherDetailExample();
            cn.ahyc.yjz.model.VoucherDetailExample.Criteria criteria = example.createCriteria();
            criteria.andVoucherIdEqualTo(voucherId);
            voucherDetailMapper.deleteByExample(example);
        } else {
            voucherNo = queryNextVoucherNo(voucher.getPeriodId());
            voucher.setVoucherNo(voucherNo);
            voucherExtendMapper.insertSelectiveReturnId(voucher);
            voucherId = voucher.getId();
        }
        /** 新增凭证明细 **/
        for (VoucherDetail detail : details) {
            detail.setVoucherId(voucherId);
            voucherDetailMapper.insertSelective(detail);
        }
    }

    @Override
    public int queryNextVoucherNo(Long periodId) {
        return voucherExtendMapper.selectMaxVoucherNo(periodId) + 1;
    }

    @Override
    public List<CompanyCommonValue> queryVoucherWordList() {
        CompanyCommonValueExample example = new CompanyCommonValueExample();
        Criteria criteria = example.createCriteria();
        criteria.andTypeIdEqualTo(1L);// TODO 1L:凭证字
        return companyCommonValueMapper.selectByExample(example);
    }

    @Override
    public List<VoucherDetail> queryVoucherDetailList(Long voucherId) {
        VoucherDetailExample example = new VoucherDetailExample();
        cn.ahyc.yjz.model.VoucherDetailExample.Criteria criteria = example.createCriteria();
        criteria.andVoucherIdEqualTo(voucherId);
        return voucherDetailMapper.selectByExample(example);
    }

    @Override
    public Voucher queryVoucher(Long voucherId) {
        return voucherExtendMapper.selectByPrimaryKey(voucherId);
    }

    @Override
    public List<AccountSubject> queryAccountSubjectList(Long bookId) {
        return accountSubjectExtendMapper.selectLastChildSubject(bookId);
    }
}
