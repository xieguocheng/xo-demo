package com.system.utils;


import com.system.enums.CodeEnum;
import org.springframework.util.StringUtils;

public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }


}
