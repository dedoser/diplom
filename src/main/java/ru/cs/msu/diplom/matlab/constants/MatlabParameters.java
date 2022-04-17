package ru.cs.msu.diplom.matlab.constants;

import lombok.Getter;

@Getter
public enum MatlabParameters {

    /**
     * Файл модели
     */
    MODEL_FILE(":file"),
    /**
     * Название сгенерированного изображения системы
     */
    IMAGE_FILE(":image"),
    /**
     * Название сгенерированного изображения графика выходного сигнала
     */
    IMAGE_PLOT_FILE(":plotImage"),
    /**
     * Имя блока, в который будет идти сигнал от блока
     */
    IN_BLOCK(":inBlock"),
    /**
     * Имя блока, от которого будет идти сигнал от блока
     */
    OUT_BLOCK(":outBlock"),
    /**
     * Имя файла модели, который заменит блок Subsystem
     */
    SOLUTION(":solution"),
    /**
     * Имя системы загруженного файла
     */
    SOLUTION_NAME(":nameSolutionSystem"),
    /**
     * Имя системы(модели)
     */
    SYSTEM(":system"),
    /**
     * Файл лог
     */
    LOG(":log");

    private String name;

    MatlabParameters(String name) {
        this.name = name;
    }
}
