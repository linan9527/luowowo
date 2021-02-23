package cn.wolfcode.luowowo.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Setter
@Getter
public class BaseDomain implements Serializable {
    @Id
    protected String id;
}
