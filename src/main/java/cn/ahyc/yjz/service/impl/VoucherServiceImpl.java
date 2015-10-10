package cn.ahyc.yjz.service.impl;

import java.util.HashMap;

/**
 * AccountBookServiceImpl
 *
 * @author:sanlai_lee@qq.com
 * @date: 15/9/23
 */

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ahyc.yjz.mapper.base.CompanyCommonValueMapper;
import cn.ahyc.yjz.mapper.extend.AccountSubjectExtendMapper;
import cn.ahyc.yjz.mapper.extend.SubjectBalanceExtendMapper;
import cn.ahyc.yjz.mapper.extend.VoucherDetailExtendMapper;
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
    private VoucherDetailExtendMapper voucherDetailExtendMapper;

    @Autowired
    private CompanyCommonValueMapper companyCommonValueMapper;

    @Autowired
    private AccountSubjectExtendMapper accountSubjectExtendMapper;

    @Autowired
    private SubjectBalanceExtendMapper subjectBalanceExtendMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String save(Voucher voucher, List<VoucherDetail> details) {
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
            voucherDetailExtendMapper.deleteByExample(example);
        } else {
            voucherNo = queryNextVoucherNo(voucher.getPeriodId());
            voucher.setVoucherNo(voucherNo);
            voucherExtendMapper.insertSelectiveReturnId(voucher);
            voucherId = voucher.getId();
        }
        /** 新增凭证明细 **/
        for (VoucherDetail detail : details) {
            detail.setVoucherId(voucherId);
            detail.setId(null);
            voucherDetailExtendMapper.insertSelective(detail);
        }

        updateSubjectSum(voucher.getPeriodId());
        updateSubjectBalance(voucher.getPeriodId());
        return voucher.getVoucherWord() + "字第" + voucherNo + "号";
    }

    /**
     * 更新科目余额表累计项
     * 
     * @param periodId
     */
    @Transactional(rollbackFor = Exception.class)
    private void updateSubjectSum(Long periodId) {
        subjectBalanceExtendMapper.insertOrUpdateSubjectSum(periodId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSubjectBalance(Long periodId) {
        // 更新科目余额表余额项
        subjectBalanceExtendMapper.insertOrUpdateSubjectBalance(periodId);
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
    public List<Map<String, Object>> queryVoucherDetailList(Long voucherId, Long bookId) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("voucherId", voucherId);
        param.put("bookId", bookId);
        return voucherDetailExtendMapper.selectDetailList(param);
    }

    @Override
    public Voucher queryVoucher(Long voucherId) {
        return voucherExtendMapper.selectByPrimaryKey(voucherId);
    }

    @Override
    public List<AccountSubject> queryAccountSubjectList(Long bookId) {
        return accountSubjectExtendMapper.selectLastChildSubject(bookId);
    }

    @Override
    public Map<String, Object> queryDetailTotal(Long voucherId) {
        return voucherDetailExtendMapper.selectDetailTotal(voucherId);
    }
}
