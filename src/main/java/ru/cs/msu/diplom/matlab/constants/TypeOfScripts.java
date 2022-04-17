package ru.cs.msu.diplom.matlab.constants;

import lombok.Getter;

@Getter
public enum TypeOfScripts {

    CREATE_IMAGE("matlab-scripts/create_image.m"),
    REPLACE_IMAGE( "matlab-scripts/replace_system.m");

    private final String fileName;

    TypeOfScripts(String fileName) {
        this.fileName = fileName;
    }
}
