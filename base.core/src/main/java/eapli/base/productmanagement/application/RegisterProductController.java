package eapli.base.productmanagement.application;

import eapli.base.categorymanagement.dto.CategoryDTO;
import eapli.base.productmanagement.domain.*;
import eapli.base.productmanagement.dto.ProductDTO;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Money;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@UseCaseController
public class RegisterProductController {

    private Product product;
    private final AuthorizationService authorizationService = AuthzRegistry.authorizationService();
    private final RegisterProductService registerProductService = new RegisterProductService();
    private final ViewAllCategoriesService viewAllCategoriesService = new ViewAllCategoriesService();

    public Product registerProductWithoutProductionCode(final int option, final Code code, final Description shortDescription, final Description extendedDescription, final Description technicalDescription, final BrandName brandName, final Reference reference, final Barcode barcode, final Money price, final List<String> photo) throws IOException {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.SALES_CLERK, BaseRoles.POWER_USER);

        this.product = registerProductService.registerProductWithoutProductionCode(findCategoryId(option), code, shortDescription, extendedDescription, technicalDescription, brandName, reference, barcode, price, convertByteIntoPhoto(photo));

        return product;
    }

    public Product registerProductWithProductionCode(final int option, final Code code, final Description shortDescription, final Description extendedDescription, final Description technicalDescription, final BrandName brandName, final Reference reference, final Barcode barcode, final Money price, final List<String> photo, final ProductionCode productionCode) throws IOException {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.SALES_CLERK, BaseRoles.POWER_USER);

        this.product = registerProductService.registerProductWithProductionCode(findCategoryId(option), code, shortDescription, extendedDescription, technicalDescription, brandName, reference, barcode, price, convertByteIntoPhoto(photo), productionCode);

        return product;
    }

    public ProductDTO getProductDTO() {
        return product.toDTO();
    }

    public List<CategoryDTO> getCategoryDTOList() {
        return viewAllCategoriesService.getAllCategories();
    }

    private Long findCategoryId(int option) {
        CategoryDTO categoryDTO = getCategoryDTOList().get(option - 1);

        return viewAllCategoriesService.getCategoryId(categoryDTO);
    }

    public boolean validateAndVerifyPath(String path) {
        String extension = FilenameUtils.getExtension(path);

        if (extension.equals("png") || extension.equals("jpg") || extension.equals("svg")) {
            File file = new File(path);

            if (file.exists() && !file.isDirectory()) {
                return true;

            } else {
                throw new IllegalStateException("Invalid path! The path introduced does not exist.");
            }
        } else {
            throw new IllegalStateException("Invalid file format! Please enter a .png, .jpg or .svg file.");
        }
    }

    private List<Photo> convertByteIntoPhoto(List<String> sList) throws IOException {
        List<Photo> photoList = new ArrayList<>();

        for (byte[] aByte : registerProductService.validatePhotoPath(sList)) {
            Photo p = Photo.valueOf(aByte);
            photoList.add(p);
        }

        return photoList;
    }
}
