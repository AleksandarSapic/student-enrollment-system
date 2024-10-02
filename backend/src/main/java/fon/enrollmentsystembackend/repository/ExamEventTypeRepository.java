package fon.enrollmentsystembackend.repository;

import fon.enrollmentsystembackend.model.ExamEventType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamEventTypeRepository extends JpaRepository<ExamEventType, Integer> {
}
