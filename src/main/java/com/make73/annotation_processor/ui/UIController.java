package com.make73.annotation_processor.ui;

import com.make73.annotation_processor.ParentController;
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

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("projects", ParentController.getControllersStatic());
        return "index";
    }

    @GetMapping("/project/{link}")
    public String projectMethods(@PathVariable String link, Model model) {
        // Simulate fetching method list based on the project link
        List<MethodInfoDTO> methods = ParentController.getMethodsForController(link);

        model.addAttribute("projectName", link);
        model.addAttribute("methods", methods);
        return "project";
    }

}
