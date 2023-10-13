import com.example.application.repositories.ParticipantRepository;
import com.example.application.services.ParticipantService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ParticipantServiceTest {

    @InjectMocks
    private ParticipantService participantService;

    @Mock
    private ParticipantRepository participantRepository; // Assuming a repository is being used

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    public void testFindAll() {
//        Participant participant1 = new Participant("John", "java");
//        Participant participant2 = new Participant("Doe", "Python");
//
//        when(participantRepository.findAll()).thenReturn(Arrays.asList(participant1, participant2));
//
//        List<ParticipantDTO> result = participantService.findAll();
//
//        assertNotNull(result);
//        assertEquals(2, result.size());
//        assertEquals("John", result.get(0).getName());
//        assertEquals("Java", result.get(0).getTechnology());
//    }
}
