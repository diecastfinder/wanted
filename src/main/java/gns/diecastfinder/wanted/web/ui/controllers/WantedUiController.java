package gns.diecastfinder.wanted.web.ui.controllers;

import gns.diecastfinder.wanted.domain.WantedModel;
import gns.diecastfinder.wanted.repositories.WantedModelRepository;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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

    @RequestMapping("/upload")
    public String uploadCSV(@RequestParam("file") MultipartFile file, Model model) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String line;

            List<WantedModel> wantedModels = new LinkedList<>();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                wantedModels.add(WantedModel.builder()
                        .name(data[0])
                        .producer(data[1])
                        .minPrice(Integer.parseInt(data[2]))
                        .maxPrice(Integer.parseInt(data[3]))
                        .currency(data[4])
                        .active(true)
                        .build());
            }
            wantedModelRepository.deleteAll();
            wantedModelRepository.saveAll(wantedModels);

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "An error occurred while processing the file.");
            return "wantedmodels/list";
        }
        model.addAttribute("message", "File uploaded successfully!");
        model.addAttribute("wantedModels", wantedModelRepository.findByActiveTrue());
        return "wantedmodels/list";
    }

}
