package green.controller;

import green.entity.Test;
import green.manager.TestManager;
import green.model.response.GetAllResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @Autowired
    TestManager testManager;

    @ApiOperation(value="Get All Tests", consumes = "application/json")
    @GetMapping(value = "api/test")
    public ResponseEntity<GetAllResponse<Test>> getAllTests() {
        return testManager.getAllTests();
    }

    @ApiOperation(value="Get Test", consumes = "application/json")
    @GetMapping(value = "api/test/{id}")
    public ResponseEntity<Test> getTest(@PathVariable("id") Integer id) {
        return testManager.getTest(id);
    }

    @ApiOperation(value="Create Test", consumes = "application/json")
    @PostMapping(value = "api/test")
    public ResponseEntity<Test> createTest(@RequestBody Test test) {
        return testManager.createTest(test);
    }

    @ApiOperation(value="Update Test", consumes = "application/json")
    @PutMapping(value = "api/test/{id}")
    public ResponseEntity<Test> updateTest(@PathVariable("id") Integer id, @RequestBody Test test) {
        return testManager.updateTest(test);
    }

    @ApiOperation(value="Delete Test", consumes = "application/json")
    @DeleteMapping(value = "api/test/{id}")
    public ResponseEntity<String> deleteTest(@PathVariable("id") Integer id) {
        return testManager.deleteTest(id);
    }
}
