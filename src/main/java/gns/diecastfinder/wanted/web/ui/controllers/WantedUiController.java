package gns.diecastfinder.wanted.web.ui.controllers;

import gns.diecastfinder.wanted.repositories.WantedModelRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WantedUiController {

    private final WantedModelRepository wantedModelRepository;

    public WantedUiController(WantedModelRepository wantedModelRepository) {
        this.wantedModelRepository = wantedModelRepository;
    }

    @RequestMapping("/wanted-models")
    public String getWantedModels(Model model) {
        model.addAttribute("wantedModels", wantedModelRepository.findByActiveTrue());
        return "wantedmodels/list";
    }

}
