package springboard.board.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboard.board.domain.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
