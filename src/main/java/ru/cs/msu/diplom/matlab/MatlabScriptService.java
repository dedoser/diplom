package ru.cs.msu.diplom.service;

import java.util.Map;

/**
 * Интерфейс для работы со скриптами матлаба
 */
public interface MatlabScriptService {

    /**
     * Метод, вызывающийся при создании бина. Считывает шаблоны скриптов матлаба
     */
    void init();

    /**
     * Метод для генерации скрипта для генерации изображения системы
     * @param params параметры, которые используется для подстановки необходимых параметров
     * @return сгенерированный скрипт в виде строки
     */
    String generateCreateImageScript(Map<String, String> params);

    /**
     * Метод для генерации скрипта для замены блока на необходимую подсистему
     * @param params параметры, которые используется для подстановки необходимых параметров
     * @return сгенерированный скрипт в виде строки
     */
    String generateReplaceSystemScript(Map<String, String> params);
}
