package com.example.lab1.utils;

import org.springframework.context.annotation.Configuration;
import ru.textanalysis.tawt.jmorfsdk.JMorfSdk;
import ru.textanalysis.tawt.jmorfsdk.loader.JMorfSdkFactory;

@Configuration(value = "getInstance")
public class MorphAnalyzer {
    private static JMorfSdk instance;

    public static synchronized JMorfSdk getInstance() {
        if (instance == null) {
            instance = JMorfSdkFactory.loadFullLibrary();
        }
        return instance;
    }
}
