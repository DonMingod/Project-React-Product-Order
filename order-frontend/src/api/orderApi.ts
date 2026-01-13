import axios from "axios";
import type { AxiosResponse } from "axios";

import type { OrderRequest } from "../Dtos/request/OrderResquest";
import type { OrderResponse } from "../Dtos/response/OrderResponse";

const API_URL = "http://localhost:8081/orders";

export const getOrders = (): Promise<AxiosResponse<OrderResponse[]>> =>
  axios.get(API_URL);

export const getOrderById = (
  id: number
): Promise<AxiosResponse<OrderResponse>> =>
  axios.get(`${API_URL}/${id}`);

export const createOrder = (
  order: OrderRequest
): Promise<AxiosResponse<OrderResponse>> =>
  axios.post(API_URL, order);

export const updateOrder = (
  id: number,
  order: OrderRequest
): Promise<AxiosResponse<OrderResponse>> =>
  axios.put(`${API_URL}/${id}`, order);

export const deleteOrder = (id: number): Promise<AxiosResponse<void>> =>
  axios.delete(`${API_URL}/${id}`);
