package leejuyeon.pacemaker.repository;

import leejuyeon.pacemaker.entity.CoursePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursePostRepository extends JpaRepository<CoursePost, Long> {

}
