package CopyProperties;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author sheyang
 * @date 2021/7/1 3:10 下午
 */
@Data
public class Account {

    private Long uid;

    private Integer age;

    private Byte gender;

    private List<String> sign;

    private Map<String, Object> pictures;

    private Long index;

    private LocalDateTime birthday;

    private Double latitude;

    private Double longitude;

    private String phone;

    private Boolean disable;

}
