import type { OrderItemResponse } from "./OrderItemResponse";

export interface OrderResponse {
  id: number;
  customerName: string;
  total: number;
  items: OrderItemResponse[];
}
