package green.controller;

import green.entity.Position;
import green.manager.PositionManager;
import green.model.request.CreatePositionRequest;
import green.model.request.UpdatePositionRequest;
import green.model.response.BaseResponse;
import green.model.response.ListResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PositionController {

    @Autowired
    PositionManager positionManager;

    @ApiOperation(value = "Get All Positions", consumes = "application/json")
    @GetMapping(value = "api/position/{token}")
    public ResponseEntity<ListResponse<Position>> getAllPositions(@PathVariable("token") String token) {
        return positionManager.getAllPositions(token);
    }

    @ApiOperation(value = "Get Position", consumes = "application/json")
    @GetMapping(value = "api/position/{token}/{id}")
    public ResponseEntity<Position> getPosition(@PathVariable("token") String token, @PathVariable("id") Integer id) {
        return positionManager.getPosition(token, id);
    }

    @ApiOperation(value = "Create Position", consumes = "application/json")
    @PostMapping(value = "api/position/{token}")
    public ResponseEntity<Position> createPosition(@PathVariable("token") String token, @RequestBody CreatePositionRequest request) {
        return positionManager.createPosition(token, request);
    }

    @ApiOperation(value = "Update Position", consumes = "application/json")
    @PutMapping(value = "api/position/{token}/{id}")
    public ResponseEntity<Position> updatePosition(@PathVariable("token") String token, @RequestBody UpdatePositionRequest request) {
        return positionManager.updatePosition(token, request);
    }

    @ApiOperation(value = "Delete Position", consumes = "application/json")
    @DeleteMapping(value = "api/position/{token}/{id}")
    public ResponseEntity<BaseResponse> deletePosition(@PathVariable("token") String token, @PathVariable("id") Integer id) {
        return positionManager.deletePosition(token, id);
    }
}
