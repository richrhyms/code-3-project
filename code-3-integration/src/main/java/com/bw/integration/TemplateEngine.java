package com.bw.integration;

import java.util.Map;

/**
 * @author Atanda Rasheed <ratanda@byteworks.com.ng>
 */
public interface TemplateEngine {

    String getAsString(String templatePath, Map<String, Object> bindings);

    byte[] getBytes(String templatePath, Map<String, Object> bindings);
}
