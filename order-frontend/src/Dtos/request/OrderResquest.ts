import type { OrderItemRequest } from "./OrderItemRequest";

export interface OrderRequest {
  customerName: string;
  items: OrderItemRequest[];
}
