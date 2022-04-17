package ru.cs.msu.diplom.service;

import org.springframework.web.multipart.MultipartFile;
import ru.cs.msu.diplom.entity.Solution;

public interface SolutionService {
    Solution save(MultipartFile file, Long taskId);

    void launchSolution(Solution solution);
}
