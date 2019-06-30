package green.manager;


import green.dto.QuestionDto;
import green.dto.TestDto;
import green.dto.VersionDto;
import green.entity.Question;
import green.entity.Test;
import green.entity.Version;
import green.model.request.CreateTestRequest;
import green.model.request.EditTestRequest;
import green.model.response.BaseArrayResponse;
import green.model.response.BaseObjectResponse;
import green.repository.QuestionRepository;
import green.repository.TestRepository;
import green.repository.VersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class TestManager {

    @Autowired
    TestRepository testRepository;

    @Autowired
    VersionRepository versionRepository;

    @Autowired
    QuestionRepository questionRepository;

    @SuppressWarnings("rawtypes")
    public BaseObjectResponse addTest(CreateTestRequest request) {
        final Test test = new Test();
        test.setName(request.getName());
        if (request.getCandidate() != null) ;
        test.setCandidate(request.getCandidate());
        if (request.getRedactor() != null) ;
        test.setRedactor(request.getRedactor());
        test.setPosition(request.getPosition());
        List<Version> versionList = new ArrayList<>();
        test.setVersions(versionList);

        final Test newTest = testRepository.save(test);

        if (request.getVersions() != null) {
            for (VersionDto version : request.getVersions()) {
                List<Question> questions = new ArrayList<>();
                Version v = versionRepository.save(new Version(version.getLanguage(), newTest, questions));
                if (version.getQuestions() != null) {
                    for (QuestionDto questionDto : version.getQuestions()) {
                        Question question = new Question();
                        question.setType(questionDto.getType());
                        question.setDescription(questionDto.getDescription());
                        question.setVersion(v);
                        if (questionDto.getMaxVal() != null)
                            question.setMaxVal(questionDto.getMaxVal());
                        if (questionDto.getMinVal() != null)
                            question.setMinVal(questionDto.getMinVal());
                        if (questionDto.getOption1() != null)
                            question.setOption1(questionDto.getOption1());
                        if (questionDto.getOption2() != null)
                            question.setOption2(questionDto.getOption2());
                        if (questionDto.getOption3() != null)
                            question.setOption3(questionDto.getOption3());
                        questionRepository.save(question);
                    }


                }
            }
        }
        final BaseObjectResponse response = new BaseObjectResponse();
        response.setCode(1);
        response.setMessage("OK");
        return response;
    }

    @SuppressWarnings("rawtypes")
    public BaseArrayResponse<TestDto> getAllTests() {

        final BaseArrayResponse<TestDto> response = new BaseArrayResponse<TestDto>();

        final List<Test> tests = testRepository.findAll();
        final List<TestDto> dto = new ArrayList<>();
        for (Test test : tests) {
            TestDto testDto = new TestDto();
            testDto.setName(test.getName());
            testDto.setId(test.getId());
            if (test.getCandidate() != null) ;
            testDto.setCandidate(test.getCandidate());
            if (test.getPosition() != null) ;
            testDto.setPosition(test.getPosition());
            if (test.getRedactor() != null) ;
            testDto.setRedactor(test.getRedactor());

            List<VersionDto> versionDtoList = new ArrayList<>();

            if (test.getVersions() != null) {
                for (Version version : test.getVersions()) {
                    VersionDto versionDto = new VersionDto();
                    versionDto.setLanguage(version.getLanguage());
                    versionDto.setId(version.getId());
                    List<QuestionDto> questionDtoList = new ArrayList<>();
                    if (version.getQuestions() != null) {
                        List<Question> questions = version.getQuestions();
                        for (Question question : questions) {
                            QuestionDto questionDto = new QuestionDto();
                            questionDto.setType(question.getType());
                            questionDto.setId(question.getId());
                            questionDto.setDescription(question.getDescription());
                            if (question.getMaxVal() != null)
                                questionDto.setMaxVal(question.getMaxVal());
                            if (question.getMinVal() != null)
                                questionDto.setMinVal(question.getMinVal());
                            if (question.getOption1() != null)
                                questionDto.setOption1(question.getOption1());
                            if (question.getOption2() != null)
                                questionDto.setOption2(question.getOption2());
                            if (question.getOption3() != null)
                                questionDto.setOption3(question.getOption3());
                            questionDtoList.add(questionDto);
                        }
                        versionDto.setQuestions(questionDtoList);
                    }
                    versionDtoList.add(versionDto);
                }
            }
            testDto.setVesions(versionDtoList);
            dto.add(testDto);
        }
        response.setResponse(dto);
        response.setCode(1);
        response.setMessage("OK");
        return response;
    }

    @SuppressWarnings("Duplicates")
    public BaseObjectResponse<TestDto> editTest(EditTestRequest request) {
        final Test test = testRepository.findOne(request.getId());
        test.setName(request.getName());
        if (request.getCandidate() != null) ;
        test.setCandidate(request.getCandidate());
        if (request.getRedactor() != null) ;
        test.setRedactor(request.getRedactor());
        test.setPosition(request.getPosition());


        if (test.getVersions() != null) {
            for (Version oldV : test.getVersions()) {
                if (oldV.getQuestions() != null) {
                    for (Question oldQ : oldV.getQuestions()) {
                        questionRepository.delete(oldQ);
                    }
                }
                versionRepository.delete(oldV);
            }
        }

        List<Version> versionList = new ArrayList<>();
        test.setVersions(versionList);

        if (request.getVersions() != null) {
            for (VersionDto version : request.getVersions()) {
                List<Question> questions = new ArrayList<>();
                Version v = versionRepository.save(new Version(version.getLanguage(), test, questions));
                if (version.getQuestions() != null) {
                    for (QuestionDto questionDto : version.getQuestions()) {
                        Question question = new Question();
                        question.setType(questionDto.getType());
                        question.setDescription(questionDto.getDescription());
                        question.setVersion(v);
                        if (questionDto.getMaxVal() != null)
                            question.setMaxVal(questionDto.getMaxVal());
                        if (questionDto.getMinVal() != null)
                            question.setMinVal(questionDto.getMinVal());
                        if (questionDto.getOption1() != null)
                            question.setOption1(questionDto.getOption1());
                        if (questionDto.getOption2() != null)
                            question.setOption2(questionDto.getOption2());
                        if (questionDto.getOption3() != null)
                            question.setOption3(questionDto.getOption3());
                        questionRepository.save(question);
                    }
                }
            }
        }
        final BaseObjectResponse response = new BaseObjectResponse();
        response.setCode(1);
        response.setMessage("OK");
        return response;
    }
}
