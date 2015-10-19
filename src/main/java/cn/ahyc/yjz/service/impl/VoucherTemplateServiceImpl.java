package cn.ahyc.yjz.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ahyc.yjz.mapper.extend.VoucherTemplateDetailExtendMapper;
import cn.ahyc.yjz.mapper.extend.VoucherTemplateExtendMapper;
import cn.ahyc.yjz.service.VoucherTemplateService;

/**
 * @ClassName: VoucherTemplateServiceImpl
 * @Description: TODO
 * @author chengjiarui 1256064203@qq.com
 * @date 2015年10月18日 下午5:07:06
 * 
 */
@Service
public class VoucherTemplateServiceImpl implements VoucherTemplateService {

    @Autowired
    private VoucherTemplateExtendMapper voucherTemplateExtendMapper;
    @Autowired
    private VoucherTemplateDetailExtendMapper voucherTemplateDetailExtendMapper;

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.ahyc.yjz.service.VoucherTemplateService#queryDetailList(java.lang.
     * Long, long)
     */
    @Override
    public List<Map<String, Object>> queryDetailList(Long voucherTemplateId, long bookId) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("voucherTemplateId", voucherTemplateId);
        param.put("bookId", bookId);
        return voucherTemplateDetailExtendMapper.selectDetailList(param);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.ahyc.yjz.service.VoucherTemplateService#queryDetailTotal(java.lang.
     * Long)
     */
    @Override
    public Map<String, Object> queryDetailTotal(Long voucherTemplateId) {
        return voucherTemplateDetailExtendMapper.selectDetailTotal(voucherTemplateId);
    }
}
