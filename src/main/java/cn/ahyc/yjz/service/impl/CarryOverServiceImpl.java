package cn.ahyc.yjz.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ahyc.yjz.mapper.extend.AccountSubjectExtendMapper;
import cn.ahyc.yjz.mapper.extend.VoucherDetailExtendMapper;
import cn.ahyc.yjz.model.Voucher;
import cn.ahyc.yjz.model.VoucherDetail;
import cn.ahyc.yjz.service.CarryOverService;
import cn.ahyc.yjz.service.VoucherService;

@Service
public class CarryOverServiceImpl implements  CarryOverService{
	
	@Autowired
	private VoucherService voucherService;
	@Autowired
	private VoucherDetailExtendMapper voucherDetailExtendMapper;
	@Autowired
	private AccountSubjectExtendMapper accountSubjectExtendMapper;
	 /**
	 * 结转损益.
	 *
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int CarryoverSubmit(String summary, String voucherWord) {
		Map<String, Object> param1 = new HashMap<String, Object>();
		param1.put("bookId", 32);
		Map<String, Object> ss=accountSubjectExtendMapper.getSubjectCodeByRoot(param1);
		String tmp=ss.get("subject_code").toString();
		//设置记账凭证主表数据
		Voucher voucher=new Voucher();
		voucher.setVoucherWord(voucherWord);
		Date voucherTime=new Date();
		voucher.setVoucherTime(voucherTime);
		voucher.setBillNum(0);
		voucher.setPeriodId(28L);
		//设置记账凭证详细表数据
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("bookId", 32);
		param.put("currentPeriod", 9);
		List<VoucherDetail> datas=voucherDetailExtendMapper.selectProfitAndLoss(param);
		if(datas.size()==0){
			return 0;
		}
		List<VoucherDetail> details=generateDetails(datas);
		//保存凭证
		voucherService.save(voucher, details);
		return 1;
	}
	public List<VoucherDetail> generateDetails(List<VoucherDetail> datas){
		List<VoucherDetail> details=new ArrayList<VoucherDetail>();
		for(VoucherDetail data:datas){
			
		}
		return details;
	}
	

}
