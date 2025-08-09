package com.sb.sblib.util;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "prop")
public class PropUtil {
    private Map<String, String> s3;
}
