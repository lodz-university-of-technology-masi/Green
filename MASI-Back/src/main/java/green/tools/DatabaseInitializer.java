package green.tools;

import green.entity.Member;
import green.entity.Role;
import green.entity.Session;
import green.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@ConditionalOnProperty(name = "app.db-init", havingValue = "true")
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public void run(String... strings) throws Exception {
        membersInitialization();
        System.out.println(" -- Database has been initialized");
    }

    private void membersInitialization() {
        List<Session> sessions = Arrays.asList();
        Member member1 = new Member();
        member1.setLogin("Moderator");
        member1.setName("Moderator");
        member1.setPassword(Tools.generateMD5("Moderator"));
        member1.setActive(true);
        member1.setSessions(sessions);
        member1.setRole(Role.Moderator);
        memberRepository.save(member1);
        Member member2 = new Member();
        member2.setLogin("Redaktor");
        member2.setName("Redaktor");
        member2.setPassword(Tools.generateMD5("Redaktor"));
        member2.setActive(true);
        member2.setSessions(sessions);
        member2.setRole(Role.Editor);
        memberRepository.save(member2);
        Member member3 = new Member();
        member3.setLogin("Kandydat");
        member3.setName("Kandydat");
        member3.setPassword(Tools.generateMD5("Kandydat"));
        member3.setActive(true);
        member3.setSessions(sessions);
        member3.setRole(Role.Candidate);
        memberRepository.save(member3);
    }

}