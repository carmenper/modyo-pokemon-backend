package com.cemp.modyo.pokemon.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { BasicConfiguration.class })
class BasicConfigurationTest {

    @Autowired
    ApplicationContext context;

    @Test
    void objectMapperTest() {
        Assertions.assertTrue(context.containsBean("objectMapper"));
        Assertions.assertNotNull(context.getBean(ObjectMapper.class));
    }
}
