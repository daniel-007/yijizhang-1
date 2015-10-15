package cn.ahyc.yjz.mapper.extend;

import cn.ahyc.yjz.mapper.base.VoucherTemplateMapper;
import cn.ahyc.yjz.model.VoucherTemplate;

public interface VoucherTemplateExtendMapper extends VoucherTemplateMapper {

    /**
     * 保存模式凭证，返回主键
     * 
     * @param record
     * @return
     */
    int insertSelectiveReturnId(VoucherTemplate record);

}