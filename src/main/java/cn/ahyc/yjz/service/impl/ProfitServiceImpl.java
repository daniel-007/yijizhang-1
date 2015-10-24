package cn.ahyc.yjz.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.ahyc.yjz.service.ProfitService;

/**
 * @ClassName: ProfitServiceImpl
 * @Description: TODO
 * @author chengjiarui 1256064203@qq.com
 * @date 2015年10月23日 下午5:06:31
 * 
 */
@Service
public class ProfitServiceImpl implements ProfitService {

    private Map<String, String> getCellValue(String expStr) {
        Map<String, String> map = new HashMap<String, String>();
        expStr = expStr.replaceAll("[\\\" \\\"=\\\\+\\-/*/]", "&");
        String[] exps = expStr.split("&");
        for (String exp : exps) {
        }
        return map;
    }
}
