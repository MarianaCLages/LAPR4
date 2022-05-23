package eapli.base.warehousemanagement.application.binservice;


import eapli.base.binmanagement.domain.Bin;
import eapli.base.binmanagement.repositories.BinRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

@Service
public class FindBinByIdService {

    BinRepository binRepository = PersistenceContext.repositories().bins();

    public Bin findBinByIdService(Long id){
        return binRepository.findById(id);
    }
}
