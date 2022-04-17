package ru.cs.msu.diplom.matlab;

import org.apache.commons.io.FileUtils;
import ru.cs.msu.diplom.matlab.constants.TypeOfScripts;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MatlabScriptServiceImpl implements MatlabScriptService {

    private Map<TypeOfScripts, String> scripts = new HashMap<>();

    @Override
    public void init() {
        Arrays.asList(TypeOfScripts.values()).forEach(type -> {
            try {
                File file = new File(this.getClass().getClassLoader().getResource(type.getFileName()).getFile());
                String script = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
                scripts.put(type, script.replaceAll("\n", ""));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public String generateScript(Map<String, String> params, TypeOfScripts type) {
        String script = scripts.get(type);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            script = script.replaceAll(entry.getKey(), entry.getValue());
        }
        return script;
    }
}
