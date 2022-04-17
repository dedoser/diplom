package ru.cs.msu.diplom.service;

import ru.cs.msu.diplom.entity.Solution;
import ru.cs.msu.diplom.entity.Task;

/**
 * Интерфейс для создания изображения схемы модели перед загрузкой ее в базу данных
 */
public interface SolutionInitializer {

    void exitProcess();

    void initialize(String fileName, Solution solution);

    String createImage(String filename);

    String combineSystem(Task task, Solution solution);
}
