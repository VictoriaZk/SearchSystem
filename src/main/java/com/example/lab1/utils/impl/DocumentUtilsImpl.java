package com.example.lab1.utils.impl;

import com.example.lab1.utils.DocumentUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.textanalysis.tawt.ms.storage.OmoFormList;

import java.util.HashMap;

@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class DocumentUtilsImpl implements DocumentUtils {
    //private final JMorfSdk jMorfSdk;

    @Override
    public HashMap<String, Integer> getTermOccurrences(String text) {
        String[] words = text.split(" ");
        HashMap<String, Integer> initialForms = new HashMap<>();

        for (String word : words) {
            //OmoFormList formList = jMorfSdk.getAllCharacteristicsOfForm(word.toLowerCase());
            OmoFormList formList = new OmoFormList();
            if (!formList.isEmpty()) {
                word = formList.getFirst().getInitialFormString();

                if (initialForms.containsKey(word)) {
                    initialForms.put(word, initialForms.get(word) + 1);
                } else {
                    initialForms.put(word, 1);
                }
            }
        }

        return initialForms;
    }
}
