package ru.cs.msu.diplom.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.cs.msu.diplom.entity.Task;
import ru.cs.msu.diplom.repository.TaskRepository;
import ru.cs.msu.diplom.service.FileStorageService;
import ru.cs.msu.diplom.service.SolutionInitializer;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
@Slf4j
public class TaskCreateController {

    @Value("${diplom.filePath}")
    private String fileDir;

    private final FileStorageService fileStorageService;
    private final TaskRepository taskRepository;
    private final SolutionInitializer solutionInitializer;

    @PostMapping("/uploadTask")
    public ResponseEntity<String> createTask(@RequestParam("name") String name,
                                             @RequestParam("description") String description,
                                             @RequestParam("inputBlock") String inputBlock,
                                             @RequestParam("outputBlock") String outputBLock,
                                             @RequestParam("file") MultipartFile file) {
        log.debug("start saving task");
        try {
            fileStorageService.save(file);
            String imageLoc = solutionInitializer.createImage(file.getOriginalFilename());
            Task task = new Task();
            task.setName(name);
            task.setDescription(description);
            task.setInputBlock(inputBlock);
            task.setOutputBlock(outputBLock);
            task.setTaskFileLoc(fileDir + file.getOriginalFilename());
            task.setImagesLoc(imageLoc);
            taskRepository.save(task);
            return ResponseEntity.status(HttpStatus.OK).body("Success");
        } catch (Exception e) {
            log.error("Cannot upload file");
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Error while saving file");
        }
    }
}
