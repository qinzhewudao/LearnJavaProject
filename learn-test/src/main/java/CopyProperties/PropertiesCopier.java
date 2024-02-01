package CopyProperties;

import cn.hutool.core.bean.BeanUtil;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.cglib.beans.BeanCopier;

/**
 * @author sheyang
 * @date 2021/7/1 3:05 下午
 */
public interface PropertiesCopier {
    void copyProperties(Object source, Object target) throws Exception;
}

class CglibBeanCopierPropertiesCopier implements PropertiesCopier {
    @Override
    public void copyProperties(Object source, Object target) throws Exception {
        BeanCopier copier = BeanCopier.create(source.getClass(), target.getClass(), false);
        copier.copy(source, target, null);
    }
}

/**
 * 全局静态 BeanCopier，避免每次都生成新的对象
 */
class StaticCglibBeanCopierPropertiesCopier implements PropertiesCopier {
    private static final BeanCopier COPIER = BeanCopier.create(Account.class, Account.class, false);

    @Override
    public void copyProperties(Object source, Object target) throws Exception {
        COPIER.copy(source, target, null);
    }
}

class SpringBeanUtilsPropertiesCopier implements PropertiesCopier {
    @Override
    public void copyProperties(Object source, Object target) throws Exception {
        org.springframework.beans.BeanUtils.copyProperties(source, target);
    }
}

class CommonsBeanUtilsPropertiesCopier implements PropertiesCopier {
    @Override
    public void copyProperties(Object source, Object target) throws Exception {
        org.apache.commons.beanutils.BeanUtils.copyProperties(target, source);
    }
}

class CommonsPropertyUtilsPropertiesCopier implements PropertiesCopier {
    @Override
    public void copyProperties(Object source, Object target) throws Exception {
        org.apache.commons.beanutils.PropertyUtils.copyProperties(target, source);
    }
}


class HutoolPropertiesCopier implements PropertiesCopier {
    @Override
    public void copyProperties(Object source, Object target) throws Exception {
        BeanUtil.copyProperties(source, target);
    }
}

class MapStructPropertiesCopier implements PropertiesCopier {
    @Override
    public void copyProperties(Object source, Object target) throws Exception {
        ConvertMapper mapper = Mappers.getMapper(ConvertMapper.class);
        target = mapper.po2Dto((Account) source);
    }
}

@Mapper
interface ConvertMapper {
    Account po2Dto(Account orderPO);
}