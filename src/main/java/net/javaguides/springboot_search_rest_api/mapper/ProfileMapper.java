package net.javaguides.springboot_search_rest_api.mapper;


import net.javaguides.springboot_search_rest_api.dto.ProfileDto;
import net.javaguides.springboot_search_rest_api.entity.Profile;
import net.javaguides.springboot_search_rest_api.entity.Utilisateur;
import net.javaguides.springboot_search_rest_api.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ProfileMapper {

    public static Profile mapToProfile(ProfileDto profileDto) {
        if (profileDto == null) return null;
        Profile result = new Profile();
        result.setId(profileDto.getId());
        result.setAge(profileDto.getAge());
        result.setNote(profileDto.getNote());
        result.setCompetences((profileDto.getCompetences()));
        result.setPoste(profileDto.getPoste());
        result.setImage(profileDto.getImage());
        result.setIsActif(profileDto.getIsActif());
        return result;
    }

    public static ProfileDto mapToProfile(Profile profile) {
        if (profile == null) return null;
        return new ProfileDto(
                profile.getId(),
                profile.getUtilisateur().getName(),
                profile.getAge(),
                profile.getPoste(),
                profile.getNote(),
                profile.getCompetences(),
                profile.getImage(),
                profile.getIsActif()

        );
    }
}
