package eapli.base.productmanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.domain.*;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.framework.application.ApplicationService;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Money;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@ApplicationService
public class RegisterProductService {

    private final ProductRepository productRepository = PersistenceContext.repositories().products();

    public Product registerProductWithoutProductionCode(final Long categoryId, final Code code, final Description shortDescription, final Description extendedDescription, final Description technicalDescription, final BrandName brandName, final Reference reference, final Barcode barcode, final Money price, final List<Photo> photoList) {
        return productRepository.save(new Product(categoryId, code, shortDescription, extendedDescription, technicalDescription, brandName, reference, barcode, price, photoList));
    }

    public Product registerProductWithProductionCode(final Long categoryId, final Code code, final Description shortDescription, final Description extendedDescription, final Description technicalDescription, final BrandName brandName, final Reference reference, final Barcode barcode, final Money price, final List<Photo> photoList, final ProductionCode productionCode) {
        return productRepository.save(new Product(categoryId, code, shortDescription, extendedDescription, technicalDescription, brandName, reference, barcode, price, photoList, productionCode));
    }

    public byte[][] validatePhotoPath(List<String> pathPhotoList) throws IOException {

        byte[][] byteMatrix = new byte[pathPhotoList.size()][];
        int index = 0;

        for (String s : pathPhotoList) {
            File file = new File(s);
            byte[] picInBytes = new byte[(int) file.length()];
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(picInBytes);
            fileInputStream.close();

            byteMatrix[index] = picInBytes;
            index++;
        }

        return byteMatrix;
    }
}
