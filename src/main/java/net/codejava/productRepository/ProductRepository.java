package net.codejava.productRepository;

import net.codejava.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository <Product,Long> {

}
