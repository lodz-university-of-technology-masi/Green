package green.manager;


import green.dto.PositionDto;
import green.entity.Position;
import green.model.request.ChangeStatusRequest;
import green.model.request.CreatePositionRequest;
import green.model.response.BaseArrayResponse;
import green.model.response.BaseObjectResponse;
import green.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PositionManager {

    @Autowired
    PositionRepository positionRepository;

    @SuppressWarnings("rawtypes")
    public BaseObjectResponse addPosition(CreatePositionRequest request) {
        final Position position = new Position();
        position.setName(request.getName());
        position.setDescription(request.getDescription());
        position.setActive(request.isActive());
        positionRepository.save(position);
        final BaseObjectResponse response = new BaseObjectResponse();
        response.setCode(1);
        response.setMessage("OK");
        return response;
    }

    public BaseArrayResponse<PositionDto> getAllPositions() {
        final BaseArrayResponse<PositionDto> response = new BaseArrayResponse<PositionDto>();
        final List<Position> positions = positionRepository.findAll();
        final List<PositionDto> dto = new ArrayList<>();
        for(Position p : positions) {
            dto.add(new PositionDto(p.getName(),p.getDescription(),p.isActive(),p.getId()));
        }

        response.setResponse(dto);
        response.setCode(1);
        response.setMessage("OK");
        return response;
    }

    @SuppressWarnings("rawtypes")
    public BaseObjectResponse changeStatus(ChangeStatusRequest request) {
        final Position position = positionRepository.findOne(request.getId());
        final BaseObjectResponse response = new BaseObjectResponse();
        position.setActive(request.isActive());
        positionRepository.save(position);

        response.setCode(1);
        response.setMessage("OK");

        return response;
    }
}
