package green.controller;

import green.manager.TestManager;
import green.model.request.CreateTestRequest;
import green.model.request.EditTestRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;


@RestController
public class TestController {

    @Autowired
    TestManager testManager;

    @PostMapping(value = "api/test/add")
    public String addTest(@RequestBody CreateTestRequest request) {
        return testManager.addTest(request).toString();
    }

    @GetMapping(value = "api/test/getAll")
    public String getAllTests() {
        return testManager.getAllTests().toString();
    }

    @PutMapping(value = "api/test/edit")
    public String editTest(@RequestBody EditTestRequest request) {
        return testManager.editTest(request).toString();
    }

}
