package net.javaguides.springboot_search_rest_api.service;

import net.javaguides.springboot_search_rest_api.dto.ProfileDto;

import java.util.List;

public interface ProfileService {
    ProfileDto createProfile (final ProfileDto ProfileDto);

    ProfileDto getProfileById (final Long id);

    ProfileDto modifyProfile(final Long id, final ProfileDto dto);

    List<ProfileDto> getAllProfiles();

}
