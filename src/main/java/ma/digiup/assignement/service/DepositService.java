package ma.digiup.assignement.service;

import ma.digiup.assignement.domain.Compte;
import ma.digiup.assignement.repository.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DepositService {

    @Autowired
    public CompteRepository compteRepository ;


    public Compte deposit(String rib, Double amount) {
        Compte account = compteRepository.findByRib(rib)
                .orElseThrow(() -> new IllegalStateException("Account not found with Rib: " + rib));

        if (amount > 10000) {
            throw new IllegalStateException("Denied!");
        } else {
            BigDecimal newBalance = account.getSolde().add(BigDecimal.valueOf(amount));
            account.setSolde(newBalance);
        }

        return compteRepository.save(account);
    }

}
