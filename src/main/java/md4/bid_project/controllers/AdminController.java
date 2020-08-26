package md4.bid_project.controllers;

import md4.bid_project.models.ApprovementStatus;
import md4.bid_project.models.Product;
import md4.bid_project.models.dto.ProductListDTO;
import md4.bid_project.models.dto.UserListDTO;
import md4.bid_project.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import md4.bid_project.models.User;
//import md4.bid_project.models.dto.UserDTO;
import md4.bid_project.services.UserService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class AdminController {

    @Autowired
    private UserService userService;

    //Creator: Lâm Quốc Tùng
    @Autowired
    private ProductService productService;

    //Thành Long
    //Hiển thị list sản phẩm
    @GetMapping("/admin/product-list")
    public ResponseEntity<Page<ProductListDTO>> getAllProduct(@RequestParam(name = "name", defaultValue = "") String name,
                                                              @RequestParam(name = "category", defaultValue = "") String category,
                                                              @RequestParam(name = "owner", defaultValue = "") String owner,
                                                              @RequestParam(name = "minPrice", defaultValue = "") String minPrice,
                                                              @RequestParam(name = "maxPrice", defaultValue = "") String maxPrice,
                                                              @RequestParam(name = "status", defaultValue = "") String status,
                                                              @RequestParam(name = "page") int page) {
        Specification<Product> specs = productService.getFilter(name, category, minPrice, maxPrice, owner, status);
        Page<ProductListDTO> products;
        if (specs != null) {
            products =  productService.findCustomerByCriteria(specs, page);
        } else {
            products = productService.findAllProduct(page);
        }

        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }


    //Thành Long
    //Hiển thị chi tiết sản phẩm để duyệt
    @GetMapping("/admin/approvement/{id}")
    public ResponseEntity<ProductListDTO> getProductById(@PathVariable Long id) {
        ProductListDTO product = productService.checkProduct(id);
        if (product == null) {
            return new ResponseEntity<ProductListDTO>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ProductListDTO>(product, HttpStatus.OK);
    }

    //Thành Long
    //Duyệt sản phẩm
    @GetMapping("admin/approvement/approve/{id}")
    public ResponseEntity<Product> approvementProduct(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
        if (product.getApprovementStatus().getId()==2) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
        ApprovementStatus approve = new ApprovementStatus();
        approve.setId(2L);
        productService.approvementProduct(product, approve);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Thành Long
    //Không duyệt sản phẩm
    @PutMapping("admin/approvement/unApprove")
    public ResponseEntity<Product> unApprovementProduct(@RequestBody Product requestBody) {
        Product product = productService.findById(requestBody.getId());
        if (product == null) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
        product.setBanned(requestBody.getBanned());
        ApprovementStatus unApprove = new ApprovementStatus();
        unApprove.setId(3L);
        productService.unApprovementProduct(product,unApprove);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Thành Long
    //Delete sản phẩm
    @PutMapping("admin/product-list/delete")
    public Map<String, Boolean> deleteProducts(@RequestBody Map<String, Long[]> requestBody) {
        Long[] ids = requestBody.get("ids").clone();
        Map<String, Boolean> response = new HashMap<>();
        for (Long id : ids) {
            Product product = productService.findById(id);
            productService.deleteProduct(product);
            response.put("deleted " + id, Boolean.FALSE);
        }
        return response;
    }

// Tùng
    @GetMapping("admin/user-list")
    public ResponseEntity<Page<UserListDTO>> getAllUser(
            @RequestParam(name = "id") String id,
            @RequestParam(name = "fullname", defaultValue = "") String fullname,
            @RequestParam(name = "email", defaultValue = "") String email,
            @RequestParam(name = "address", defaultValue = "") String address,
            @RequestParam(name = "rate", defaultValue = "") String rate,
            @RequestParam("page") int currentPage) {

        Page<UserListDTO> userListDTO;
        Specification<User> search = userService.getFilter(id, fullname, email, address, rate);
        if(search != null) {
            userListDTO = userService.findCustomerByCriteria(search, currentPage);
        }
        else {
            userListDTO = userService.findAll(currentPage);
        }
        return ResponseEntity.ok(userListDTO);
    }

    //Creator Hậu
    @PostMapping("admin/user-create")
    public void createUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        userService.saveUser(user);
    }
}
