package com.make73.annotation_processor;

import com.make73.annotation_processor.dto.ControllerInfoDTO;
import com.make73.annotation_processor.dto.MethodInfoDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@RestController("/api")
public class ParentController {

    private static List<ControllerInfoDTO> controllers = null;
    private static Map<String, List<MethodInfoDTO>> methodsMap = null;

    public static void saveControllersToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                Files.newOutputStream(Paths.get("controllers.dat")))) {
            oos.writeObject(controllers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadControllersFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(
                Files.newInputStream(Paths.get("controllers.dat")))) {
            controllers = (List<ControllerInfoDTO>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        methodsMap = new java.util.HashMap<>();
        //populate methodsMap
        for (ControllerInfoDTO controller : controllers) {
            methodsMap.put(controller.getName(), controller.getMethods());
        }
    }

    public static void setControllers(List<ControllerInfoDTO> newControllers) {
        controllers = newControllers;
        saveControllersToFile(); // Save immediately when setting
    }


    @GetMapping("/listControllers")
    public List<ControllerInfoDTO> getControllers() {
        if (controllers == null) {
            ParentController.loadControllersFromFile(); // Load saved state
        }
        return controllers;
    }

    public static List<ControllerInfoDTO> getControllersStatic() {
        if (controllers == null) {
            ParentController.loadControllersFromFile(); // Load saved state
        }
        return controllers;
    }

    public static List<MethodInfoDTO> getMethodsForController(String controllerName) {
        if (controllers == null) {
            ParentController.loadControllersFromFile(); // Load saved state
        }
        return methodsMap.get(controllerName);
    }
}
