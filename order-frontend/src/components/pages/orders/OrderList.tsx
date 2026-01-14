import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { getOrders, deleteOrder } from "../../../api/orderApi";
import type { OrderResponse } from "../../../Dtos/response/OrderResponse";

export default function OrderList() {
  const [orders, setOrders] = useState<OrderResponse[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    getOrders()
      .then(res => setOrders(res.data))
      .finally(() => setLoading(false));
  }, []);

  if (loading) return <div className="p-4">Loading...</div>;

  return (
    <div className="h-full flex flex-col">
      <div className="flex items-center justify-between mb-4">
        <h1 className="text-2xl font-semibold">Orders</h1>
        <Link to="/create">
          <button className="bg-[#003973] hover:bg-[#002a57] text-white px-4 py-2 rounded-md">
            + New Order
          </button>
        </Link>
      </div>

      {orders.length === 0 ? (
        <p className="text-gray-600">No orders found.</p>
      ) : (
        <div className="bg-white rounded-lg p-4 shadow-sm flex-1 min-h-0">
          <div className="overflow-auto h-full">
            <table className="w-full text-left table-auto">
              <thead className="bg-gray-50">
                <tr>
                  <th className="p-3">ID</th>
                  <th className="p-3">Cliente</th>
                  <th className="p-3">Total</th>
                  <th className="p-3">Items</th>
                  <th className="p-3">Acciones</th>
                </tr>
              </thead>
              <tbody>
                {orders.map(order => (
                  <tr key={order.id} className="border-b">
                    <td className="p-3">{order.id}</td>
                    <td className="p-3">{order.customerName}</td>
                    <td className="p-3">${order.total}</td>
                    <td className="p-3">{order.items.length}</td>
                    <td className="p-3">
                      <Link to={`/edit/${order.id}`}>
                        <button className="bg-[#17553e] text-white px-3 py-1 rounded-md mr-2">
                          Editar
                        </button>
                      </Link>
                      <button
                        onClick={() => {
                          if (!confirm("¿Eliminar pedido?")) return;
                          deleteOrder(order.id).then(() =>
                            setOrders(prev => prev.filter(o => o.id !== order.id))
                          );
                        }}
                        className="bg-[#3b1215] text-white px-3 py-1 rounded-md"
                      >
                        Delete
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
      )}
    </div>
  );
}
