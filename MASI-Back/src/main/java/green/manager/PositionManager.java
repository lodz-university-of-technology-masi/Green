package green.manager;

import green.entity.Member;
import green.entity.Position;
import green.entity.Role;
import green.entity.Session;
import green.model.request.CreatePositionRequest;
import green.model.request.UpdatePositionRequest;
import green.model.response.BaseResponse;
import green.model.response.ListResponse;
import green.repository.PositionRepository;
import green.repository.SessionRepository;
import green.tools.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PositionManager {

    @Autowired
    PositionRepository positionRepository;

    @Autowired
    SessionRepository sessionRepository;

    public ResponseEntity<ListResponse<Position>> getAllPositions(String token) {
        Session session = sessionRepository.findByToken(token);
        ResponseEntity<ListResponse<Position>> response = new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        if (session != null && Tools.isSessionValid(session)) {
            ListResponse<Position> body = new ListResponse();
            body.setMessage("OK");
            body.setList(positionRepository.findAll());
            response = new ResponseEntity(body, HttpStatus.OK);
        }
        return response;
    }

    public ResponseEntity<Position> getPosition(String token, Integer id) {
        Session session = sessionRepository.findByToken(token);
        ResponseEntity<Position> response = new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        if (session != null && Tools.isSessionValid(session)) {
            Position position = positionRepository.getOne(id);
            if (position != null) {
                response = new ResponseEntity(position, HttpStatus.OK);
            }
        }
        return response;
    }

    public ResponseEntity<Position> createPosition(String token, CreatePositionRequest request) {
        Session session = sessionRepository.findByToken(token);
        ResponseEntity<Position> response = new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        if (session != null && Tools.isSessionValid(session) &&
                ((session.getMember().getRole() == Role.Editor) || (session.getMember().getRole() == Role.Moderator))) {
            Position newPosition = new Position();
            newPosition.setId(null);
            newPosition.setActive(request.isActive());
            newPosition.setName(request.getName());
            newPosition.setLanguage(request.getLanguage());
            newPosition.setDescription(request.getDescription());
            newPosition = positionRepository.save(newPosition);
            response = new ResponseEntity(newPosition, HttpStatus.OK);
        }
        return response;
    }

    public ResponseEntity<Position> updatePosition(String token, UpdatePositionRequest request) {
        Session session = sessionRepository.findByToken(token);
        ResponseEntity<Position> response = new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        if (session != null && Tools.isSessionValid(session) && positionRepository.getOne(request.getId()) != null &&
                ((session.getMember().getRole() == Role.Editor) || (session.getMember().getRole() == Role.Moderator))) {
            Position updatedPosition = new Position();
            updatedPosition.setId(request.getId());
            updatedPosition.setActive(request.isActive());
            updatedPosition.setName(request.getName());
            updatedPosition.setLanguage(request.getLanguage());
            updatedPosition.setDescription(request.getDescription());
            updatedPosition = positionRepository.save(updatedPosition);
            response = new ResponseEntity(updatedPosition, HttpStatus.OK);
        }
        return response;
    }

    public ResponseEntity<BaseResponse> deletePosition(String token, Integer id) {
        Session session = sessionRepository.findByToken(token);
        ResponseEntity<BaseResponse> response = new ResponseEntity(new BaseResponse("Bad request!"), HttpStatus.BAD_REQUEST);
        if (session != null && Tools.isSessionValid(session) && positionRepository.getOne(id) != null &&
                ((session.getMember().getRole() == Role.Editor) || (session.getMember().getRole() == Role.Moderator))) {
            positionRepository.delete(positionRepository.getOne(id));
            response = new ResponseEntity("Deleted.", HttpStatus.OK);
        }
        return response;
    }
}
