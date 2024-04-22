package org.example.databaseFunctional;

//import org.example.data.entity.LogErrors;
import org.example.hybernateEntites.LogErrors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogErrorRepository extends JpaRepository<LogErrors, Integer> {
}

