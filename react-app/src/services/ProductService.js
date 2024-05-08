import axios from 'axios';

const initProduct = [
  {
    id: 1,
    name: 'Monitor Samsung',
    price: 500,
    description: ' El monitor es increíble',
  },
  {
    id: 2,
    name: 'IPhone 15 PRO',
    price: 1500,
    description: ' El movil es increíble',
  },
];

const baseUrl = 'http://localhost:8080/products';

export const listProduct = () => {
  return initProduct;
};

export const findAll = async () => {
  try {
    const response = await axios.get(baseUrl);

    return response;
  } catch (err) {
    console.error(err);
  }

  return null;
};

export const create = async ({ name, description, price }) => {
  try {
    const response = await axios.post(baseUrl, {
      name,
      description,
      price,
    });

    return response;
  } catch (err) {
    console.error(err);
  }

  return null;
};

export const update = async ({ id, name, description, price }) => {
  try {
    const response = await axios.put(baseUrl + '/' + id, {
      name,
      description,
      price,
    });

    return response;
  } catch (err) {
    console.error(err);
  }

  return null;
};

export const remove = async (id) => {
  try {
    await axios.delete(`${baseUrl}/${id}`);
  } catch (err) {
    console.error(err);
  }
};
