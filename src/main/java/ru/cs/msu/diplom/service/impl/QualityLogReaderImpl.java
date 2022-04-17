package ru.cs.msu.diplom.service.impl;

import org.apache.commons.io.FileUtils;
import ru.cs.msu.diplom.entity.Quality;
import ru.cs.msu.diplom.matlab.constants.QualityParameters;
import ru.cs.msu.diplom.service.QualityLogReader;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class QualityLogReaderImpl implements QualityLogReader {
    @Override
    public Quality readLog(String filename) {
        Map<QualityParameters, Float> params = new EnumMap<>(QualityParameters.class);
        String[] logs = new String[0];
        try {
            File file = new File(filename);
            logs = FileUtils.readFileToString(file, StandardCharsets.UTF_8).split("\n");
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
        for (String log : logs) {
            String[] values = log.split(":");
            List<String> keyValue = Arrays.stream(values).map(String::trim).collect(Collectors.toList());
            QualityParameters key = QualityParameters.valueOfName(keyValue.get(0));
            if (key != null) {
                params.put(key, Float.parseFloat(keyValue.get(1)));
            }
        }
        return initQuality(params);
    }

    private Quality initQuality(Map<QualityParameters, Float> params) {
        return Quality.builder()
                .riseTime(params.get(QualityParameters.RISE_TIME))
                .settlingTime(params.get(QualityParameters.SETTLING_TIME))
                .settlingMax(params.get(QualityParameters.SETTLING_MAX))
                .settlingMin(params.get(QualityParameters.SETTLING_MIN))
                .overshoot(params.get(QualityParameters.OVERSHOOT))
                .undershoot(params.get(QualityParameters.UNDERSHOOT))
                .peak(params.get(QualityParameters.PEAK))
                .peakTime(params.get(QualityParameters.PEAK_TIME))
                .build();
    }
}
