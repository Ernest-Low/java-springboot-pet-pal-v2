package sg.com.petpal.petpal.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.com.petpal.petpal.exception.PetDataNotFoundException;
import sg.com.petpal.petpal.model.PetData;
import sg.com.petpal.petpal.service.PetDataService;;

@RestController
@RequestMapping("/petData")
public class PetDataController {

    private PetDataService petDataService;

    public PetDataController(PetDataService petDataService) {
        this.petDataService = petDataService;
    }

    @GetMapping
    public ResponseEntity<ArrayList<PetData>> getAll() {
        return new ResponseEntity<>(petDataService.getAllPetData(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetData> getPetData(@PathVariable("id") Long id) {
        PetData petData = petDataService.getPetData(id);
        if (petData == null) {
            throw new PetDataNotFoundException(id);
        }
        return new ResponseEntity<>(petData, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PetData> createPetData(@RequestBody PetData petData) {
        return new ResponseEntity<>(petDataService.createPetData(petData), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PetData> updatePetData(@PathVariable("id") Long id, @RequestBody PetData petData) {
        return new ResponseEntity<>(petDataService.updatePetData(petData), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePetData(@PathVariable("id") Long id) {
        petDataService.deletePetData(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
