package com.kjawank_jose.lds_scriptures_game.repository;

import com.kjawank_jose.lds_scriptures_game.model.ScriptureBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScriptureBookRepository extends JpaRepository<ScriptureBook, Long> {

    Optional<ScriptureBook> findByCode(String code);

    Optional<ScriptureBook> findByName(String name);
}
