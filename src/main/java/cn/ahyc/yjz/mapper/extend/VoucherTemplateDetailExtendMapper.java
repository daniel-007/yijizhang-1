package cn.ahyc.yjz.mapper.extend;

import java.util.List;
import java.util.Map;

import cn.ahyc.yjz.mapper.base.VoucherTemplateDetailMapper;
import cn.ahyc.yjz.model.VoucherTemplateDetail;

public interface VoucherTemplateDetailExtendMapper extends VoucherTemplateDetailMapper {

    /**
     * 查询模式凭证详细
     * 
     * @param map
     * @return
     */
    List<VoucherTemplateDetail> selectTemplateDetailList(Map<String, Object> map);

}