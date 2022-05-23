package eapli.base.warehousemanagement.application.binservice;

import eapli.base.binmanagement.domain.Bin;
import eapli.base.binmanagement.repositories.BinRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;

public class MoveProductToAnotherBinService {

    private FindBinByIdService binByIdService = new FindBinByIdService();
    BinRepository binRepository = PersistenceContext.repositories().bins();

    public boolean moveProductToAnotherBinService(long binId,long productId){
        Bin bin = binByIdService.findBinByIdService(binId);
        bin.update(bin.getBinLocation(),productId);
        binRepository.updateBin(bin);

        return true;
    }
}
