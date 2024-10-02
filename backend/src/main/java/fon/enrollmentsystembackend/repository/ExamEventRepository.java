package fon.enrollmentsystembackend.repository;

import fon.enrollmentsystembackend.model.ExamEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamEventRepository extends JpaRepository<ExamEvent, Integer> {
}
