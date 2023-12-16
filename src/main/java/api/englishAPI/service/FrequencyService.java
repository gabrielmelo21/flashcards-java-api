package api.englishAPI.service;



import api.englishAPI.dto.FrequencyDTO;
import api.englishAPI.model.Frequency;
import api.englishAPI.repositories.FrequencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FrequencyService {

    @Autowired
    private FrequencyRepository repository;

    public List<Frequency> listFrequency(){return repository.findAll(Sort.by(Sort.Order.asc("id")));}


    public ResponseEntity<String> updateFrequency() {
        List<Frequency> frequencies = repository.findAll();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM");
        String currentDate = dateFormat.format(new Date());

        for (Frequency frequency : frequencies) {
            if (currentDate.equals(frequency.getDataDia())) {
                frequency.setStatus(true);
                repository.save(frequency); // Atualiza no banco de dados
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body("Atualizado com sucesso");
    }

    public List<Frequency> saveAll(List<Frequency> frequencies) {
        return repository.saveAll(frequencies);
    }





}
