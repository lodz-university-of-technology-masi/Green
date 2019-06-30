package green.controller;

import green.manager.PositionManager;
import green.model.request.ChangeStatusRequest;
import green.model.request.CreatePositionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
public class PositionController {

    @Autowired
    PositionManager positionManager;

    @PostMapping(value = "api/position/add")
    public String registerMember(@RequestBody CreatePositionRequest request) {
        return positionManager.addPosition(request).toString();
    }

    @GetMapping(value = "api/position/getAll")
    public String getAllMembers() {
        return positionManager.getAllPositions().toString();
    }

    @PutMapping(value = "api/position/mod")
    public String changeLanguage(@RequestBody ChangeStatusRequest request) {
        return positionManager.changeStatus(request).toString();
    }
}
