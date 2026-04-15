package net.javaguides.springboot_search_rest_api.service.impl;


import net.javaguides.springboot_search_rest_api.dto.ProfileDto;
import net.javaguides.springboot_search_rest_api.entity.Profile;
import net.javaguides.springboot_search_rest_api.entity.Utilisateur;
import net.javaguides.springboot_search_rest_api.mapper.ProfileMapper;
import net.javaguides.springboot_search_rest_api.repository.ProfileRepository;
import net.javaguides.springboot_search_rest_api.service.ProfileService;
import net.javaguides.springboot_search_rest_api.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public ProfileDto createProfile(ProfileDto dto) {
        Profile p = ProfileMapper.mapToProfile(dto);
        Utilisateur user = new Utilisateur();
        user.setName(dto.getName());
        p.setUtilisateur(user);
        final Profile profileNew = profileRepository.save(p);

        return ProfileMapper.mapToProfile(profileNew);
    }

    @Override
    public ProfileDto getProfileById(Long id) {
        final Profile profile = profileRepository.findById(id).orElseThrow(
                () -> new RuntimeException(Util.ERROR_004)
        );
        return ProfileMapper.mapToProfile(profile);
    }

    @Override
    public ProfileDto modifyProfile(Long id, ProfileDto dto) {
        // trouver d'abord l'utiilisateur existant
        final Profile profile = profileRepository.findById(id).orElseThrow(
                () -> new RuntimeException(Util.ERROR_004)
        );
        // modifier
        final Profile profileNew = profileRepository.save(ProfileMapper.mapToProfile(dto));
        return ProfileMapper.mapToProfile(profileNew);

    }

    @Override
    public List<ProfileDto> getAllProfiles() {
        return profileRepository.findAll().stream().map(
                        ProfileMapper::mapToProfile).collect(Collectors.toList());
    }

}
