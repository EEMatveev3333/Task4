package org.example.repository;

//import org.example.data.entity.Logins;
import org.example.model.Logins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface LoginRepository extends JpaRepository<Logins, Integer> {
}