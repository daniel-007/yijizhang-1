package cn.ahyc.yjz.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import cn.ahyc.yjz.service.SearchDetailService;
@Service
public class SearchDetailServiceImpl implements SearchDetailService{
	/**
	 * 查询明细账now.
	 * @param bookId
	 * @return
	 */
	@Override
	public List<Map> submitNow(String startPeriod, String endPeriod, String startSubjectCode, String endSubjectCode,
			HttpSession session) {
		// TODO Auto-generated method stub
		return null;
	}

}
