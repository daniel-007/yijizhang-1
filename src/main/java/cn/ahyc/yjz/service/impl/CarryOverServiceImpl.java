package cn.ahyc.yjz.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ahyc.yjz.model.Voucher;
import cn.ahyc.yjz.model.VoucherDetail;
import cn.ahyc.yjz.service.CarryOverService;
import cn.ahyc.yjz.service.VoucherService;

@Service
public class CarryOverServiceImpl implements  CarryOverService{
	
	@Autowired
	private VoucherService voucherService;
	 /**
	 * 结转损益.
	 *
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int CarryoverSubmit(String summary, String voucherWord) {
		Voucher voucher=new Voucher();
		   
		List<VoucherDetail> details=new ArrayList<VoucherDetail>();
		voucherService.save(voucher, details);
		return 1;
	}
	
	

}
