package eapli.base.productmanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.domain.*;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.framework.application.ApplicationService;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Money;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@ApplicationService
public class RegisterProductService {

    private final ProductRepository productRepository = PersistenceContext.repositories().products();

    public Product registerProductWithoutProductionCode(final Code code, final Description shortDescription, final Description extendedDescription, final Description technicalDescription, final BrandName brandName, final Reference reference, final Barcode barcode, final Money price, final String photo) throws IOException {
        return productRepository.save(new Product(code, shortDescription, extendedDescription, technicalDescription, brandName, reference, barcode, price, Photo.valueOf(validatePhotoPath(photo))));
    }

    public Product registerProductWithProductionCode(final Code code, final Description shortDescription, final Description extendedDescription, final Description technicalDescription, final BrandName brandName, final Reference reference, final Barcode barcode, final Money price, final String photo, final ProductionCode productionCode) throws IOException {
        return productRepository.save(new Product(code, shortDescription, extendedDescription, technicalDescription, brandName, reference, barcode, price, Photo.valueOf(validatePhotoPath(photo)), productionCode));
    }

    public byte[] validatePhotoPath(String path) throws IOException {

        String extension = FilenameUtils.getExtension(path);

        if (extension.equals("png") || extension.equals("jpg")) {
            File file = new File(path);

            if (file.exists() && !file.isDirectory()) {
                byte[] picInBytes = new byte[(int) file.length()];
                FileInputStream fileInputStream = new FileInputStream(file);
                fileInputStream.read(picInBytes);
                fileInputStream.close();

                return picInBytes;

            } else {
                throw new IllegalStateException("Invalid path! The path introduced does not exist.");
            }
        } else {
            throw new IllegalStateException("Invalid file format! Please enter a .png or .jpg file.");
        }
    }
}
