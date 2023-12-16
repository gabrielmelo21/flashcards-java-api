package api.englishAPI.controller;

import api.englishAPI.model.Frequency;
import api.englishAPI.service.FrequencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/frequency")
public class FrequencyController {
    @Autowired
    private FrequencyService frequencyService;

    @GetMapping
    public ResponseEntity<List<Frequency>> listAllFrequency(){
        List<Frequency> list = frequencyService.listFrequency();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @PostMapping("/add")
    public List<Frequency> addFrequenciesInBatch(@RequestBody List<Frequency> frequencies) {
        return frequencyService.saveAll(frequencies);
    }

    @PutMapping
    public ResponseEntity<String> update(){
        return frequencyService.updateFrequency();
    }






}
