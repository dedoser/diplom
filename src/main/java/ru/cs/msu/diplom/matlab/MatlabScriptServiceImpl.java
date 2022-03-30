package ru.cs.msu.diplom.service.impl;

import lombok.RequiredArgsConstructor;
import ru.cs.msu.diplom.service.MatlabScriptService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class MatlabScriptServiceImpl implements MatlabScriptService {

    private final List<String> scriptFiles = {"", ""};
    private Map<String, String> scripts = new HashMap<>();

    @Override
    public void init() {

    }

    @Override
    public String generateCreateImageScript(Map<String, String> params) {
        return null;
    }

    @Override
    public String generateReplaceSystemScript(Map<String, String> params) {
        return null;
    }
}
