package CopyProperties;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author sheyang
 * @date 2021/7/1 3:07 下午
 */
@RunWith(Parameterized.class)
public class PropertiesCopierTest {

    @Parameterized.Parameter(0)
    public PropertiesCopier propertiesCopier;

    /**
     * 测试次数
     */
    private static final List<Integer> TEST_TIMES = Arrays.asList(100, 1000, 10_000, 100_000, 1_000_000);
//    private static final List<Integer> TEST_TIMES = Arrays.asList(1, 2, 3, 4, 5);

    /**
     * 测试结果以 markdown 表格的形式输出
     */
    private static final StringBuilder RESULT_BUILDER = new StringBuilder("|实现|100|1,000|10,000|100,000|1,000,000|\n").append("|------------------------|----------|----------|----------|----------|----------|\n");

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Collection<Object[]> params = new ArrayList<>();
        params.add(new Object[]{new StaticCglibBeanCopierPropertiesCopier()});
        params.add(new Object[]{new CglibBeanCopierPropertiesCopier()});
        params.add(new Object[]{new SpringBeanUtilsPropertiesCopier()});
        params.add(new Object[]{new CommonsPropertyUtilsPropertiesCopier()});
        params.add(new Object[]{new CommonsBeanUtilsPropertiesCopier()});
        params.add(new Object[]{new HutoolPropertiesCopier()});
        params.add(new Object[]{new MapStructPropertiesCopier()});
        return params;
    }

    @Before
    public void setUp() throws Exception {
        String name = propertiesCopier.getClass().getSimpleName().replace("PropertiesCopier", "");
        RESULT_BUILDER.append("|").append(ljust(25, name)).append("|");
    }

    @Test
    public void copyProperties() throws Exception {
        Account source = new Account();
        source.setUid(0L);
        source.setAge(0);
        source.setGender((byte) 0);
        source.setSign(Lists.newArrayList());
        source.setPictures(Maps.newHashMap());
        source.setIndex(0L);
        source.setBirthday(LocalDateTime.now());
        source.setLatitude(123.0D);
        source.setLongitude(314.0D);
        source.setPhone("16621181248");
        source.setDisable(false);

        Account target = new Account();
        // 预热一次
        propertiesCopier.copyProperties(source, target);
        for (Integer time : TEST_TIMES) {
            long start = System.nanoTime();
            for (int i = 0; i < time; i++) {
                propertiesCopier.copyProperties(source, target);
            }
            String a = String.valueOf((System.nanoTime() - start) / 1_000_000D);
            RESULT_BUILDER.append(ljust(10, a)).append("|");
        }
        RESULT_BUILDER.append("\n");
    }

    @AfterClass
    public static void tearDown() throws Exception {
        System.out.println("测试结果：");
        System.out.println(RESULT_BUILDER);
    }

    String ljust(int width, String source) {
        int diff = width - source.length();
        StringBuilder sourceBuilder = new StringBuilder(source);
        for (int i = 0; i < diff; i++) {
            sourceBuilder.append(" ");
        }
        source = sourceBuilder.toString();
        return source;
    }

}

