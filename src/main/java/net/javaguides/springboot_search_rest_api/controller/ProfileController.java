package net.javaguides.springboot_search_rest_api.controller;

import net.javaguides.springboot_search_rest_api.dto.ProfileDto;
import net.javaguides.springboot_search_rest_api.service.ProfileService;
import net.javaguides.springboot_search_rest_api.utils.Util;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = Util.URL_BASE)
@RequestMapping("/api/profiles")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("/createProfile")
    ResponseEntity<ProfileDto> createProfile(@RequestBody ProfileDto dto){
        ProfileDto dtoCreate = profileService.createProfile(dto);
        return new ResponseEntity<>(dtoCreate, HttpStatus.CREATED);
    }

    @GetMapping("/{idProfile}")
    public ResponseEntity<ProfileDto> getProfileById(@PathVariable(name = "idProfile") final Long id) {
        ProfileDto dto = profileService.getProfileById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<List<ProfileDto>> findAllProfiles() {
        List<ProfileDto> retour = profileService.getAllProfiles();
        return new ResponseEntity<>(retour, HttpStatus.OK);
    }

    @PutMapping("/{idProfile}")
    ResponseEntity<ProfileDto> modifyProfile(@PathVariable(name = "idProfile") final Long id,
                                                     @RequestBody ProfileDto ProfileDto) {
        ProfileDto dto = profileService.modifyProfile(id, ProfileDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


}
