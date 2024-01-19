package ma.digiup.assignement.DepositTest;

import ma.digiup.assignement.domain.Compte;
import ma.digiup.assignement.service.DepositService;
import ma.digiup.assignement.repository.CompteRepository;
import ma.digiup.assignement.web.DepositController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class DepositControllerTest {

    private MockMvc mockMvc;

    @Mock
    private DepositService depositService;

    @InjectMocks
    private DepositController depositController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(depositController).build();
    }

    @Test
    void testDeposit() throws Exception {
        // Mocking the service response
        Compte mockCompte = new Compte(); // create a mock Compte as needed
        ResponseEntity<Compte> mockResponseEntity = new ResponseEntity<>(mockCompte, HttpStatus.OK);
        when(depositService.deposit(any(), any())).thenReturn(mockResponseEntity.getBody());

        // Perform the request and assert the response
        mockMvc.perform(patch("/Montant")
                        .param("Rib", "mockRib")
                        .param("Amount", "5000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.solde").value(mockCompte.getSolde()))
                // Add more assertions as needed
                .andReturn();
    }
}
