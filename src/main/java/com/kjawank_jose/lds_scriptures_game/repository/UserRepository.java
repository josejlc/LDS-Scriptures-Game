package com.kjawank_jose.lds_scriptures_game.repository;

import com.kjawank_jose.lds_scriptures_game.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Busca un usuario por su nombre de usuario.
     * Spring Data JPA automáticamente genera la implementación de este método.
     *
     * @param username El nombre de usuario a buscar.
     * @return El objeto User si se encuentra, de lo contrario, null.
     */
    User findByUsername(String username);
}
