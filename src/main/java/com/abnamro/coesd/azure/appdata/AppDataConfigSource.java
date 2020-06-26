package com.abnamro.coesd.azure.appdata;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.azure.core.http.rest.PagedIterable;
import com.azure.data.appconfiguration.ConfigurationClient;
import com.azure.data.appconfiguration.ConfigurationClientBuilder;
import com.azure.data.appconfiguration.models.ConfigurationSetting;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.spi.ConfigSource;

public class AppDataConfigSource implements ConfigSource {


    private String namespace = ".appconfig.featureflag";
    private String connString = "Endpoint=https://abn-app-configuration.azconfig.io;Id=KC6P-l9-s0:SGULGZfJWNQ2EB4pY/tU;Secret=GPqwYocM/PK7FbHU2th7uGocxoQzcPYMxUTEt1XQGuw=";

    private ConfigurationClient client;

    private Map<String, String> props = new HashMap<>();


    @PostConstruct
    public void init() {
        // Config config = ConfigProvider.getConfig();
        // String connectionString = config.getValue("azure.appdata.connection", String.class);

        if (client == null) {
            client = new ConfigurationClientBuilder()
                .connectionString(connString)
                .buildClient();
                
                PagedIterable<ConfigurationSetting> listConfigurationSettings = client.listConfigurationSettings(null);
                listConfigurationSettings.stream()
                .forEach(s -> props.put(s.getKey(), s.getValue()));
            }

    }


    @Override
    public Map<String, String> getProperties() {
        return props;
    }

    @Override
    public String getValue(String propertyName) {
        init();
        // getProperties();
        // TODO Auto-generated method stub
        return props.get(namespace + "/" + propertyName);
    }

    @Override
    public String getName() {
        return "XAzureAppConfig";
    }
    

}