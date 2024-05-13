import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../services/product.service';
import { Product } from '../../models/product';
import { FormComponent } from '../form/form.component';

@Component({
  selector: 'app-product',
  standalone: true,
  imports: [FormComponent],
  templateUrl: './product.component.html',
  styleUrl: './product.component.css',
})
export class ProductComponent implements OnInit {
  products: Product[] = [];

  productSelected: Product = {
    id: 0,
    name: ' ',
    description: ' ',
    price: 0,
  };

  constructor(private service: ProductService) {}

  ngOnInit(): void {
    this.service.findAll().subscribe((products) => (this.products = products));
  }

  addProduct(product: Product) {
    // this.products.push(product);
    if (product.id > 0) {
      this.service.update(product).subscribe((productUpdated) => {
        this.products = this.products.map((prod) => {
          if (product.id == prod.id) {
            return { ...productUpdated };
          }

          return prod;
        });
      });
    } else {
      this.service.create(product).subscribe((prod) => {
        this.products = [...this.products, { ...prod }];
      });
    }

    this.productSelected = new Product();
  }

  onUpdateProduct(productRow: Product) {
    this.productSelected = productRow;
  }

  onRemoveProduct(id: number) {
    this.service.remove(id).subscribe(() => {
      this.products = this.products.filter((p) => p.id != id);
    });
  }
}
