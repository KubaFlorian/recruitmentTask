package com.recruitmenttask.repository;

import com.recruitmenttask.model.Nauczyciel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NauczycielRepository extends PagingAndSortingRepository<Nauczyciel, Long> {

    List<Nauczyciel> findNauczycielByImieAndNazwisko(String name, String lastName);

}
