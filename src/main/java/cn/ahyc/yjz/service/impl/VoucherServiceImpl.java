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
import org.springframework.transaction.annotation.Transactional;

import cn.ahyc.yjz.mapper.base.CompanyCommonValueMapper;
import cn.ahyc.yjz.mapper.base.VoucherDetailMapper;
import cn.ahyc.yjz.mapper.base.VoucherMapper;
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
    private VoucherMapper voucherMapper;

    @Autowired
    private VoucherDetailMapper voucherDetailMapper;

    @Autowired
    private CompanyCommonValueMapper companyCommonValueMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(Voucher voucher, List<VoucherDetail> details) {
        if (voucher != null && voucher.getId() != null) {
            voucherMapper.updateByPrimaryKeySelective(voucher);
        } else {
            voucherMapper.insertSelective(voucher);
        }
        for (VoucherDetail detail : details) {
            if (voucher != null && voucher.getId() != null) {
                voucherDetailMapper.updateByPrimaryKeySelective(detail);
            } else {
                voucherDetailMapper.insertSelective(detail);
            }
        }
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
}
