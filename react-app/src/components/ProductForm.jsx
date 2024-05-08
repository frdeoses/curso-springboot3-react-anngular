import { useEffect, useState } from 'react';

const initialForm = {
  id: 0,
  name: '',
  description: '',
  price: '',
};

// eslint-disable-next-line react/prop-types
export const ProductForm = ({ handlerAdd, productSelected }) => {
  const [form, setForm] = useState(initialForm);

  const { id, name, description, price } = form;

  useEffect(() => {
    setForm(productSelected);
  }, [productSelected]);

  return (
    <form
      onSubmit={(event) => {
        event.preventDefault();

        if (!name || !description || !price) {
          alert('Debe de completar los datos del formulario..');
          return;
        }

        // console.log(form);
        handlerAdd(form);
        setForm(initialForm);
      }}
    >
      <div>
        <input
          placeholder='Name'
          // style={{ marginBottom: '4px' }}
          className='form-control my-3 w-75'
          name='name'
          value={name}
          onChange={(event) =>
            setForm({
              ...form,
              name: event.target.value,
            })
          }
        />
      </div>

      <div>
        <input
          placeholder='Description'
          className='form-control my-3 w-75'
          // style={{ marginBottom: '4px' }}
          name='description'
          value={description}
          onChange={(event) =>
            setForm({
              ...form,
              description: event.target.value,
            })
          }
        />
      </div>

      <div>
        <input
          placeholder='Price'
          className='form-control my-3 w-75'
          // style={{ marginBottom: '4px' }}
          name='price'
          value={price}
          onChange={(event) =>
            setForm({
              ...form,
              price: event.target.value,
            })
          }
        />
      </div>

      <div>
        <button className='btn btn-primary' type='submit'>
          {id > 0 ? 'Update' : 'Create'}
        </button>
      </div>
    </form>
  );
};
