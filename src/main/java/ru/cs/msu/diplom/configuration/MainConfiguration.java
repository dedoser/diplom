package ru.cs.msu.diplom.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.cs.msu.diplom.matlab.MatlabScriptService;
import ru.cs.msu.diplom.matlab.MatlabScriptServiceImpl;
import ru.cs.msu.diplom.repository.QualityRepository;
import ru.cs.msu.diplom.repository.SolutionRepository;
import ru.cs.msu.diplom.repository.TaskRepository;
import ru.cs.msu.diplom.service.FileStorageService;
import ru.cs.msu.diplom.service.QualityLogReader;
import ru.cs.msu.diplom.service.SolutionInitializer;
import ru.cs.msu.diplom.service.SolutionService;
import ru.cs.msu.diplom.service.impl.FileStorageServiceImpl;
import ru.cs.msu.diplom.service.impl.QualityLogReaderImpl;
import ru.cs.msu.diplom.service.impl.SolutionInitializerImpl;
import ru.cs.msu.diplom.service.impl.SolutionServiceImpl;

import javax.annotation.PostConstruct;

@Configuration
public class MainConfiguration {

    @Value("${diplom.filePath}")
    private String fileDir;
    @Value("${diplom.imageDir}")
    private String imageDir;

    @Bean
    public FileStorageService fileStorageService() {
        return new FileStorageServiceImpl();
    }

    @Bean(destroyMethod = "exitProcess")
    public SolutionInitializer solutionInitializer(MatlabScriptService matlabScriptService) {
        return new SolutionInitializerImpl(fileDir, imageDir, matlabScriptService);
    }

    @Bean(initMethod = "init")
    public MatlabScriptService matlabScriptService() {
        return new MatlabScriptServiceImpl();
    }

    @Bean
    public QualityLogReader qualityLogReader() {
        return new QualityLogReaderImpl();
    }

    @Bean
    public SolutionService solutionService(SolutionRepository solutionRepository,
                                           TaskRepository taskRepository,
                                           SolutionInitializer solutionInitializer,
                                           FileStorageService fileStorageService,
                                           QualityLogReader qualityLogReader,
                                           QualityRepository qualityRepository) {
        return new SolutionServiceImpl(imageDir,
                fileDir,
                solutionRepository,
                solutionInitializer,
                taskRepository,
                qualityLogReader,
                fileStorageService,
                qualityRepository);
    }
}
