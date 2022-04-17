package ru.cs.msu.diplom.service.impl;

import lombok.RequiredArgsConstructor;
import ru.cs.msu.diplom.entity.Solution;
import ru.cs.msu.diplom.entity.Task;
import ru.cs.msu.diplom.matlab.MatlabScriptService;
import ru.cs.msu.diplom.matlab.constants.TypeOfScripts;
import ru.cs.msu.diplom.service.SolutionInitializer;

import java.io.*;
import java.util.Map;

import static ru.cs.msu.diplom.matlab.constants.MatlabParameters.*;

@RequiredArgsConstructor
public class SolutionInitializerImpl implements SolutionInitializer {

    private Process process;

    private final String fileDir;
    private final String imageDir;
    private final MatlabScriptService scriptService;

    private static final String BASE_COMMAND = "/usr/local/MATLAB/R2018a/bin/matlab";
    private static final String[] ARGS = {
            "-nosplash",
            "-nodesktop",
            "-nojvm",
            "-r"
    };
    private static final String CREATE_IMAGE_TEMPL = "load_system('%s'); print('-s%s', '-djpeg', '%s'); disp('success'); exit";

    @Override
    public void exitProcess() {
        process.destroy();
    }

    @Override
    public void initialize(String fileName, Solution solution) {

    }

    @Override
    public String createImage(String filename) {
        String systemName = filename.substring(0, filename.length() - 4);
        String absolutePathFile = fileDir + filename;
        String absolutePathImage = imageDir + systemName + ".jpg";
        String command = scriptService.generateScript(
                getParamsForCreateImage(absolutePathFile, absolutePathImage, systemName),
                TypeOfScripts.CREATE_IMAGE
        );
//        String command = String.format(CREATE_IMAGE_TEMPL, absolutePathFile, systemName, absolutePathImage);
        System.out.println(command);
        System.out.println(absolutePathFile);
        System.out.println(absolutePathImage);

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(BASE_COMMAND, ARGS[0], ARGS[1], ARGS[2], ARGS[3], command);
            processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            process = processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("Process started");
            process.waitFor();
            System.out.println("Process stopped");
            process.destroy();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return absolutePathImage;
    }

    @Override
    public String combineSystem(Task task, Solution solution) {
        String command = scriptService.generateScript(getParamsForReplaceSystem(task, solution), TypeOfScripts.REPLACE_IMAGE);
        System.out.println(command);
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(BASE_COMMAND, ARGS[0], ARGS[1], ARGS[3], command);
            processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            process = processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("Process started");
            process.waitFor();
            System.out.println("Process stopped");
            process.destroy();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "Fail";
        }
        return "Success";
    }

    private Map<String, String> getParamsForCreateImage(String filePath, String imagePath, String systemName) {
        return Map.of(
                MODEL_FILE.getName(), filePath,
                IMAGE_FILE.getName(), imagePath,
                SYSTEM.getName(), systemName
        );
    }

    private Map<String, String> getParamsForReplaceSystem(Task task, Solution solution) {
        String taskFile = task.getTaskFileLoc();
        String systemName = taskFile.substring(taskFile.lastIndexOf('/') + 1, taskFile.length() - 4);
        String solutionFile = solution.getSolFileLoc();
        String solutionModel = solution.getSolFileLoc().substring(solutionFile.lastIndexOf('/') + 1, solutionFile.length() - 4);
        return Map.of(
                MODEL_FILE.getName(), taskFile,
                SYSTEM.getName(), systemName,
                SOLUTION.getName(), solution.getSolFileLoc(),
                IN_BLOCK.getName(), task.getInputBlock(),
                OUT_BLOCK.getName(), task.getOutputBlock(),
                IMAGE_FILE.getName(), solution.getImageSystem(),
                IMAGE_PLOT_FILE.getName(), solution.getImagePlot(),
                SOLUTION_NAME.getName(), solutionModel,
                LOG.getName(), solution.getLogFile()
        );
    }
}
