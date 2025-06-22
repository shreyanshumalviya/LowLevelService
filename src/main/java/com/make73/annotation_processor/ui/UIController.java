package com.make73.annotation_processor.ui;

import com.make73.annotation_processor.ParentController;
import com.make73.annotation_processor.dto.ControllerInfoDTO;
import com.make73.annotation_processor.dto.MethodInfoDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class UIController {
    public static final String PROJECT_BASE_PATH = "project";

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("projects", ParentController.getControllersStatic());
        return "index";
    }

    @GetMapping("/" + PROJECT_BASE_PATH + "/{link}")
    public String projectMethods(@PathVariable String link, Model model) {
        // Simulate fetching method list based on the project link
        ControllerInfoDTO controller = ParentController.getMethodsForController(link);

        model.addAttribute("project", controller);
        model.addAttribute("methods", controller.getMethods());
        return "project";
    }

}
