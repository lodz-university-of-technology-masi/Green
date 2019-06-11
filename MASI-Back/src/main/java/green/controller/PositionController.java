package green.controller;

import green.entity.Position;
import green.manager.PositionManager;
import green.model.response.GetAllResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PositionController {

    @Autowired
    PositionManager positionManager;

    @ApiOperation(value="Get All Positions", consumes = "application/json")
    @GetMapping(value = "api/position")
    public ResponseEntity<GetAllResponse<Position>> getAllPositions() {
        return positionManager.getAllPositions();
    }

    @ApiOperation(value="Get Position", consumes = "application/json")
    @GetMapping(value = "api/position/{id}")
    public ResponseEntity<Position> getPosition(@PathVariable("id") Integer id) {
        return positionManager.getPosition(id);
    }

    @ApiOperation(value="Create Position", consumes = "application/json")
    @PostMapping(value = "api/position")
    public ResponseEntity<Position> createPosition(@RequestBody Position position) {
        return positionManager.createPosition(position);
    }

    @ApiOperation(value="Update Positions", consumes = "application/json")
    @PutMapping(value = "api/position/{id}")
    public ResponseEntity<Position> updatePosition(@PathVariable("id") Integer id, @RequestBody Position position) {
        return positionManager.updatePosition(position);
    }

    @ApiOperation(value="Delete Positions", consumes = "application/json")
    @DeleteMapping(value = "api/position/{id}")
    public ResponseEntity<String> deletePosition(@PathVariable("id") Integer id) {
        return positionManager.deletePosition(id);
    }
}
