package ma.digiup.assignement.DepositTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Optional;

import ma.digiup.assignement.domain.Compte;
import ma.digiup.assignement.repository.CompteRepository;
import ma.digiup.assignement.service.DepositService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DepositServiceTest {

    @Mock
    private CompteRepository compteRepository;

    @InjectMocks
    private DepositService depositService;

    @Test
    void testDepositValidAmount() {

        String rib = "Rib1";
        Double amount = 3000.0;
        Compte compte = new Compte();
        compte.setRib(rib);
        compte.setSolde(BigDecimal.valueOf(5000));

        when(compteRepository.findByRib(rib)).thenReturn(Optional.of(compte));
        when(compteRepository.save(any(Compte.class))).thenReturn(compte);


        Compte result = depositService.deposit(rib, amount);


        assertNotNull(result);
        assertEquals(rib, result.getRib());
        assertEquals(BigDecimal.valueOf(8000.0), result.getSolde());
    }

    @Test
    void testDepositInvalidAmount() {

        String rib = "Rib2";
        Double amount = 20000.0;
        Compte compte = new Compte();
        compte.setRib(rib);
        compte.setSolde(BigDecimal.valueOf(10000));

        when(compteRepository.findByRib(rib)).thenReturn(Optional.of(compte));


        assertThrows(IllegalStateException.class, () -> depositService.deposit(rib, amount));
    }

    @Test
    void testDepositNonExistingCompte() {

        String rib = "Rib3";
        Double montant = 2000.0;

        when(compteRepository.findByRib(rib)).thenReturn(Optional.empty());


        assertThrows(IllegalStateException.class, () -> depositService.deposit(rib, montant));
    }
}
