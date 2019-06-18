package green.manager;

import green.entity.Test;
import green.model.response.ListResponse;
import green.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TestManager {

    @Autowired
    TestRepository testRepository;

    public ResponseEntity<ListResponse<Test>> getAllTests() {
        ListResponse body = new ListResponse();
        body.setMessage("OK");
        body.setList(testRepository.findAll());
        return new ResponseEntity(body, HttpStatus.OK);
    }

    public ResponseEntity<Test> createTest(Test test) {
        test = testRepository.save(test);
        return new ResponseEntity(test, HttpStatus.CREATED);
    }

    public ResponseEntity<Test> updateTest(Test test) {
        Optional<Test> old = testRepository.findById(test.getId());
        if (old.isPresent()) {
            return createTest(test);
        } else {
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Test> getTest(Integer id) {
        Optional<Test> test = testRepository.findById(id);
        if (test.isPresent()) {
            return new ResponseEntity(test.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> deleteTest(Integer id) {
        Optional<Test> test = testRepository.findById(id);
        if (test.isPresent()) {
            testRepository.deleteById(id);
            return new ResponseEntity("OK", HttpStatus.OK);
        } else {
            return new ResponseEntity("This position does not exists!", HttpStatus.BAD_REQUEST);
        }
    }
}
