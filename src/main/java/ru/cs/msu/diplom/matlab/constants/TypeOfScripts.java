package ru.cs.msu.diplom.matlab.files;

import lombok.Getter;

@Getter
public enum TypeOfScripts {

    CREATE_IMAGE("matlab-scripts/create-image.m"),
    REPLACE_IMAGE( "matlab-scripts/replace-system.m");

    private String fileName;

    TypeOfScripts(String fileName) {
        this.fileName = fileName;
    }
}
