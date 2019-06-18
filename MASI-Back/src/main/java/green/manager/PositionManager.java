package green.manager;

import green.entity.Position;
import green.model.response.ListResponse;
import green.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PositionManager {

    @Autowired
    PositionRepository positionRepository;

    public ResponseEntity<ListResponse<Position>> getAllPositions() {
        ListResponse body = new ListResponse();
        body.setMessage("OK");
        body.setList(positionRepository.findAll());
        return new ResponseEntity(body, HttpStatus.OK);
    }

    public ResponseEntity<Position> createPosition(Position position) {
        position = positionRepository.save(position);
        return new ResponseEntity(position, HttpStatus.CREATED);
    }

    public ResponseEntity<Position> updatePosition(Position position) {
        Optional<Position> old = positionRepository.findById(position.getId());
        if (old.isPresent()) {
            return createPosition(position);
        } else {
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Position> getPosition(Integer id) {
        Optional<Position> position = positionRepository.findById(id);
        if (position.isPresent()) {
            return new ResponseEntity(position.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> deletePosition(Integer id) {
        Optional<Position> position = positionRepository.findById(id);
        if (position.isPresent()) {
            positionRepository.deleteById(id);
            return new ResponseEntity("OK", HttpStatus.OK);
        } else {
            return new ResponseEntity("This position does not exists!", HttpStatus.BAD_REQUEST);
        }
    }
}
