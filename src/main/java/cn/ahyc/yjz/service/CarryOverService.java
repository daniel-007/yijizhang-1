package cn.ahyc.yjz.service;

/**
 * Created by john Hu on 15-9-28.
 */
public interface  CarryOverService {

	 /**
     * 结转损益
     * 
     * @param voucher
     * @param details
     */
	int CarryoverSubmit(String summary,String voucherWord);
}
