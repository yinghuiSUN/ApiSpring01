package net.javaguides.springboot_search_rest_api.specification;

import net.javaguides.springboot_search_rest_api.entity.Profile;
import org.springframework.data.jpa.domain.Specification;

public class ProfileSpecification {
    public static Specification<Profile> hasUserId(Long userId) {
        return (root, query, cb) ->
                userId == null ? null : cb.equal(root.get("utilisateur").get("id"), userId);
    }
}
