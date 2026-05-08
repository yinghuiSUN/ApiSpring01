package net.javaguides.springboot_search_rest_api.controller;

import net.javaguides.springboot_search_rest_api.dto.ApiResponse;
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
    ResponseEntity<ApiResponse<ProfileDto>> createProfile(@RequestBody ProfileDto dto){
        ProfileDto dtoCreate = profileService.createProfile(dto);
        return ResponseEntity.ok(new ApiResponse<>(Util.PROFILE_POST_MSG, dtoCreate, HttpStatus.CREATED.value()));
    }

    @GetMapping("/{idProfile}")
    public ResponseEntity<ApiResponse<ProfileDto>> getProfileById(@PathVariable(name = "idProfile") final Long id) {
        ProfileDto dto = profileService.getProfileById(id);
        return ResponseEntity.ok(new ApiResponse<>(Util.PROFILE_MSG_001, dto, HttpStatus.OK.value()));
    }

    @GetMapping
    ResponseEntity<ApiResponse<List<ProfileDto>>> findAllProfiles() {
        List<ProfileDto> retour = profileService.getAllProfiles();
        return ResponseEntity.ok(new ApiResponse<>(Util.PROFILE_MSG_002 , retour, HttpStatus.OK.value()));
    }

    @PutMapping("/{idProfile}")
    ResponseEntity<ApiResponse<ProfileDto>> modifyProfile(@PathVariable(name = "idProfile") final Long id,
                                                     @RequestBody ProfileDto ProfileDto) {
        ProfileDto dto = profileService.modifyProfile(id, ProfileDto);
        return ResponseEntity.ok(new ApiResponse<>(Util.PROFILE_UPDATE_MSG, dto, HttpStatus.OK.value()));
    }

    @GetMapping("/idUser/{idUser}")
    public ResponseEntity <ApiResponse<ProfileDto>> getProfileByIdUser(@PathVariable(name = "idUser") final Long idUser) {
        ProfileDto dto = profileService.getProfileByIdUser(idUser);
        return ResponseEntity.ok(new ApiResponse<>(Util.PROFILE_MSG_001, dto, HttpStatus.OK.value()));
    }


}
