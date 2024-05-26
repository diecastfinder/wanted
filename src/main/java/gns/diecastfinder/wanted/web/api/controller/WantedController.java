package gns.diecastfinder.wanted.web.api.controller;

import gns.diecastfinder.wanted.services.WantedService;
import gns.diecastfinder.wanted.web.model.WantedModelDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/v1/wanted")
@RestController
public class WantedController {

    private final WantedService wantedService;

    public WantedController(WantedService wantedService) {
        this.wantedService = wantedService;
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<WantedModelDto> getWantedModel(@PathVariable UUID id) {
        return new ResponseEntity<>(wantedService.getWantedModelById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveWantedModel(@RequestBody WantedModelDto wanted) {
        WantedModelDto savedModel = wantedService.saveNewModel(wanted);
        HttpHeaders headers = new HttpHeaders();

        // todo update to real host name
        headers.add("Location", "http://localhost:8080/api/v1/wanted" + savedModel.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity updateWantedModel(@PathVariable UUID id, @RequestBody WantedModelDto wanted) {
        wantedService.updateModel(id, wanted);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity deleteWantedModel(@PathVariable UUID id) {
        wantedService.deleteModel(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
