package com.kjawank_jose.lds_scriptures_game.repository;

import com.kjawank_jose.lds_scriptures_game.model.Verse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VerseRepository extends JpaRepository<Verse, Long> {

    List<Verse> findByBookId(Long bookId);

    @Query("SELECT v FROM Verse v WHERE v.book.name = :bookName")
    List<Verse> findByBookName(@Param("bookName") String bookName);

    @Query("SELECT v FROM Verse v ORDER BY FUNCTION('RAND')")
    List<Verse> findRandomVerses();

    @Query("SELECT v FROM Verse v WHERE v.book.id = :bookId ORDER BY FUNCTION('RAND')")
    List<Verse> findRandomVersesByBookId(@Param("bookId") Long bookId);
}
