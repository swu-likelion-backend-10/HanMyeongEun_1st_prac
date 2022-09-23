package likelion_backend.RegisterApi.post2.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import likelion_backend.RegisterApi.post2.domain.Information;

public interface RegisterRepository extends JpaRepository<Information, Long> {
}
