package ru.cs.msu.diplom.service;

import ru.cs.msu.diplom.entity.Quality;

public interface QualityLogReader {
    Quality readLog(String filename);
}
