package ru.cs.msu.diplom.matlab;

import ru.cs.msu.diplom.matlab.constants.TypeOfScripts;

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
     * Метод для генерации скрипта
     * @param params параметры, которые используется для подстановки необходимых параметров
     * @param type какой тип скрипта будет генерироваться
     * @return сгенерированный скрипт в виде строки
     */
    String generateScript(Map<String, String> params, TypeOfScripts type);
}
