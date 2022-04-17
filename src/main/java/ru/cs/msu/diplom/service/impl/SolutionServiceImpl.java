package ru.cs.msu.diplom.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import ru.cs.msu.diplom.entity.Quality;
import ru.cs.msu.diplom.entity.Solution;
import ru.cs.msu.diplom.entity.Task;
import ru.cs.msu.diplom.matlab.constants.QualityParameters;
import ru.cs.msu.diplom.repository.QualityRepository;
import ru.cs.msu.diplom.repository.SolutionRepository;
import ru.cs.msu.diplom.repository.TaskRepository;
import ru.cs.msu.diplom.service.FileStorageService;
import ru.cs.msu.diplom.service.QualityLogReader;
import ru.cs.msu.diplom.service.SolutionInitializer;
import ru.cs.msu.diplom.service.SolutionService;

@RequiredArgsConstructor
public class SolutionServiceImpl implements SolutionService {

    private final String imageDir;
    private final String baseDir;
    private final SolutionRepository solutionRepository;
    private final SolutionInitializer solutionInitializer;
    private final TaskRepository taskRepository;
    private final QualityLogReader qualityLogReader;
    private final FileStorageService fileStorageService;
    private final QualityRepository qualityRepository;

    @Override
    public Solution save(MultipartFile file, Long taskId) {
        Task task = taskRepository.findById(taskId).orElse(null);
        if (task == null) {
            throw new RuntimeException("Bad task id");
        }
        fileStorageService.save(file);
        Solution solution = initSolution(task, file.getOriginalFilename());
        task.addSolution(solution);
        launchSolution(solution);
        System.out.println(solution.getLogFile());
        Quality quality = qualityLogReader.readLog(solution.getLogFile());
        qualityRepository.save(quality);
        solution.setQuality(quality);
        System.out.println(solution.getQuality());
        solutionRepository.save(solution);
        return solution;
    }

    @Override
    public void launchSolution(Solution solution) {
        solutionInitializer.combineSystem(solution.getTask(), solution);
    }

    private Solution initSolution(Task task, String filename) {
        String systemName = filename.substring(0, filename.lastIndexOf('.'));
        return Solution.builder()
                .solFileLoc(baseDir + filename)
                .imagePlot(imageDir + systemName + "_plot.jpg")
                .imageSystem(imageDir + systemName + ".jpg")
                .logFile(baseDir + systemName + "_log")
                .build();
    }
}
