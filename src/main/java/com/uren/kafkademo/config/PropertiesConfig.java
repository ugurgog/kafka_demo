package com.uren.kafkademo.config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.env.OriginTrackedMapPropertySource;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class PropertiesConfig {

  private static final Map<String, String> properties = new HashMap<>();

  @EventListener(ApplicationReadyEvent.class)
  public void applicationReadyEventProcess(ApplicationReadyEvent event) {
    AbstractEnvironment environment = (AbstractEnvironment) event.getApplicationContext().getEnvironment();
    MutablePropertySources propertySources = environment.getPropertySources();

    for (org.springframework.core.env.PropertySource<?> propertySource : propertySources) {
      if (propertySource instanceof OriginTrackedMapPropertySource) {
        String[] propertyNames = ((OriginTrackedMapPropertySource) propertySource).getPropertyNames();
        for (String propertyName : propertyNames) {
          properties.put(propertyName, String.valueOf(propertySource.getProperty(propertyName)));
        }
      }
    }

    Map<String, Object> systemProperties = environment.getSystemProperties();
    if (!CollectionUtils.isEmpty(systemProperties)) {
      systemProperties.forEach((key, value) -> properties.put(key, String.valueOf(value)));
    }

    log.info("::applicationReadyEventProcess properties:{}", properties);
  }

  public static String getProperty(String propertyKey) {
    return properties.get(propertyKey);
  }
}