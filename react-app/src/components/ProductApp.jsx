import { PropTypes } from 'prop-types';
import { useEffect, useState } from 'react';
import { create, findAll, remove, update } from '../services/ProductService';
import { ProductForm } from './ProductForm';
import { ProductGrid } from './ProductGrid';

export const ProductApp = ({ title }) => {
  const [products, setProducts] = useState([]);
  const [productSelected, setProductSelected] = useState({
    id: 0,
    name: '',
    description: '',
    price: '',
  });

  const getProducts = async () => {
    const result = await findAll();
    setProducts(result.data._embedded.products);
  };

  useEffect(() => {
    getProducts();
  }, []);

  const handlerAddProduct = async (product) => {
    console.log(product);

    if (product.id > 0) {
      const response = await update(product);
      setProducts(
        products.map((prod) => {
          if (prod.id == response.data.id) {
            return { ...response.data };
          }
          return prod;
        })
      );
    } else {
      const response = await create(product);
      setProducts([...products, { ...response.data }]);
    }
  };

  const handlerRemoveProduct = (id) => {
    console.log(id);

    remove(id);
    setProducts(products.filter((product) => product.id != id));
  };

  const handlerProductSelected = (product) => {
    console.log(product);
    setProductSelected({ ...product });
  };

  return (
    <div className='container my-4'>
      <h2>{title}</h2>
      <div className='row'>
        <div className='col'>
          <ProductForm
            handlerAdd={handlerAddProduct}
            productSelected={productSelected}
          />
        </div>
        <div className='col'>
          {products.length > 0 ? (
            <ProductGrid
              products={products}
              handlerRemove={handlerRemoveProduct}
              handlerProductSelected={handlerProductSelected}
            />
          ) : (
            <div className='alert alert-warning'>
              No hay productos en el sistema...
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

ProductApp.propTypes = {
  title: PropTypes.string.isRequired,
};
