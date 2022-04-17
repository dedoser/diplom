package ru.cs.msu.diplom.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import ru.cs.msu.diplom.entity.Solution;
import ru.cs.msu.diplom.entity.Task;
import ru.cs.msu.diplom.model.FileInfo;
import ru.cs.msu.diplom.model.ResponseMessage;
import ru.cs.msu.diplom.repository.SolutionRepository;
import ru.cs.msu.diplom.repository.TaskRepository;
import ru.cs.msu.diplom.service.FileStorageService;
import ru.cs.msu.diplom.service.SolutionService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class FileUploadController {

    private final SolutionService solutionService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("taskId") Long taskId,
                                                      @RequestParam("file") MultipartFile file) {
        if (solutionService.save(file, taskId) == null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseMessage("Solution is created"));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseMessage("Error during creating solution"));
        }
    }
}
