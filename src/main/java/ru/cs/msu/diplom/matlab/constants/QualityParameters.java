package ru.cs.msu.diplom.matlab.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum QualityParameters {

    RISE_TIME("RiseTime"),
    SETTLING_TIME("SettlingTime"),
    SETTLING_MIN("SettlingMin"),
    SETTLING_MAX("SettlingMax"),
    OVERSHOOT("Overshoot"),
    UNDERSHOOT("Undershoot"),
    PEAK("Peak"),
    PEAK_TIME("PeakTime");

    private final String name;

    public static QualityParameters valueOfName(String name) {
        for (QualityParameters parameter : QualityParameters.values()) {
            if (parameter.getName().equals(name)) {
                return parameter;
            }
        }
        return null;
    }
}
