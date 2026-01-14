import axios, { type AxiosResponse } from "axios";
import type { ProductResponse } from "../Dtos/productResponse/ProductResponse";

const API_URL = "http://localhost:8082/products";

export const getProducts = () =>
  axios.get<ProductResponse[]>(API_URL);

export const getProductById = (
  id: number
): Promise<AxiosResponse<ProductResponse>> =>
  axios.get(`${API_URL}/${id}`);




