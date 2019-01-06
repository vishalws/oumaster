package com.gati.mdm.common.util;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class CustomBeanUtils {

    private static final List<String> IGNORE_PROPERTIES = new ArrayList<>();

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomBeanUtils.class);

    static {
        IGNORE_PROPERTIES.add("class");
        IGNORE_PROPERTIES.add("target");
        IGNORE_PROPERTIES.add("targetClass");
        IGNORE_PROPERTIES.add("decoratedClass");
    }

    private CustomBeanUtils() {

    }

    public static Map<String, Object> getPropertyValueMap(Object object) {

        if (object != null) {
            try {
                final BeanWrapper beanWrapper = new BeanWrapperImpl(object);
                PropertyDescriptor[] propertyDescriptors = beanWrapper.getPropertyDescriptors();
                Map<String, Object> beanMap = new HashMap<>();
                for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                    String fieldName = propertyDescriptor.getName();
                    Object fieldValue = beanWrapper.getPropertyValue(propertyDescriptor.getName());
                    if (IGNORE_PROPERTIES.indexOf(fieldName) < 0) {
                        beanMap.put(fieldName, fieldValue);
                    }
                }
                return beanMap;
            } catch (Exception exception) {
                LOGGER.error("Exception while fetching properties from bean", exception);
            }
        }
        return null;
    }
}
