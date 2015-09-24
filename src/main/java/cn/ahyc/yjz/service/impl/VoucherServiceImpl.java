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

import cn.ahyc.yjz.mapper.base.VoucherMapper;
import cn.ahyc.yjz.model.Voucher;
import cn.ahyc.yjz.model.VoucherDetail;
import cn.ahyc.yjz.service.VoucherService;

@Service
public class VoucherServiceImpl implements VoucherService {

    @Autowired
    private VoucherMapper voucherMapper;

    @Override
    public void save(Voucher voucher, List<VoucherDetail> details) {

    }
}
