import { PropTypes } from 'prop-types';
export const ProductDetail = ({
  handlerRemove,
  handlerProductSelected,
  product = {},
}) => {
  return (
    <tr>
      <td>{product.name}</td>
      <td>{product.description}</td>
      <td>{product.price}</td>
      <td>
        <button
          className='btn btn-danger btn-sm'
          onClick={() => handlerRemove(product.id)}
        >
          Remove
        </button>
      </td>
      <td>
        <button
          className='btn btn-secondary btn-sm'
          onClick={() => handlerProductSelected(product)}
        >
          Update
        </button>
      </td>
    </tr>
  );
};

ProductDetail.propTypes = {
  product: PropTypes.object.isRequired,
  handlerRemove: PropTypes.func.isRequired,
  handlerProductSelected: PropTypes.func.isRequired,
};
