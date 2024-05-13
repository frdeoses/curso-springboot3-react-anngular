import { Injectable } from '@angular/core';
import { Product } from '../models/product';
import { Observable, map, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';

const products: Product[] = [
  {
    id: 1,
    name: 'Mesa comedor',
    description: 'Excelente mesa de comedor',
    price: 2550,
  },
  {
    id: 2,
    name: 'Silla de comedor',
    description: 'Excelente silla de comedor',
    price: 500,
  },
];

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  url: string = 'http://localhost:8080/products';

  constructor(private http: HttpClient) {}

  findAll(): Observable<Product[]> {
    // return of(products);
    return this.http
      .get<Product[]>(this.url)
      .pipe(map((response: any) => response._embedded.products as Product[]));
  }

  create(product: Product): Observable<Product> {
    // return of(products);
    return this.http.post<Product>(this.url, product);
  }
  update(product: Product): Observable<Product> {
    // return of(products);
    return this.http.put<Product>(`${this.url}/${product.id}`, product);
    // .pipe(map((response: any) => response._embedded.products as Product[]));
  }
  remove(id: number): Observable<void> {
    // return of(products);
    return this.http.delete<void>(`${this.url}/${id}`);
    // .pipe(map((response: any) => response._embedded.products as Product[]));
  }
}
