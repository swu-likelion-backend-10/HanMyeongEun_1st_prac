package likelion_backend.RegisterApi.post.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import likelion_backend.RegisterApi.post.domain.Register;

public interface RegisterRepository extends JpaRepository<Register, Long> {
}
