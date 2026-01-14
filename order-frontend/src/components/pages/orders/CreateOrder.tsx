import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { createOrder } from "../../../api/orderApi";
import type { OrderRequest } from "../../../Dtos/request/OrderResquest";

type ItemForm = {
  productId: string;
  quantity: number;
};

export default function OrderCreate() {
  const navigate = useNavigate();

  const [customerName, setCustomerName] = useState("");
  const [items, setItems] = useState<ItemForm[]>([{ productId: "", quantity: 1 }]);
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState("");

  const handleItemChange = (
    index: number,
    field: "productId" | "quantity",
    value: string | number
  ) => {
    setItems(prev => {
      const copy = [...prev];
      copy[index] = {
        ...copy[index],
        [field]: field === "quantity" ? Number(value) : String(value),
      };
      return copy;
    });
  };

  const addItem = () =>
    setItems(prev => [...prev, { productId: "", quantity: 1 }]);

  const removeItem = (index: number) =>
    setItems(prev => prev.filter((_, i) => i !== index));

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);

    const payload: OrderRequest = {
      customerName: customerName.trim(),
      items: items.map(it => ({
        productId: Number(it.productId),
        quantity: Number(it.quantity),
      })),
    };

    try {
      await createOrder(payload);
      navigate("/");
    } catch (err) {
      console.error(err);
      setMessage("Error creating order");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="w-full h-full flex items-start justify-center p-6">
      <div className="max-w-3xl w-full bg-white rounded-lg shadow-lg p-6">
        <div className="flex items-center justify-between mb-4">
          <h2 className="text-2xl font-semibold text-[#003973]">Create Order</h2>
          <Link to="/" className="text-sm text-[#003973] hover:underline">
            Back to list
          </Link>
        </div>

        {message && (
          <div className="mb-4 text-sm px-4 py-2 rounded bg-[#f3f6fb] text-[#003973]">
            {message}
          </div>
        )}

        <form onSubmit={handleSubmit} className="space-y-4">
          <div>
            <label className="block text-sm font-medium mb-1 text-gray-700">
              Customer Name
            </label>
            <input
              className="w-full border border-gray-300 rounded-md px-3 py-2 focus:ring-2 focus:ring-[#003973]"
              value={customerName}
              onChange={e => setCustomerName(e.target.value)}
              required
            />
          </div>

          <div>
            <div className="flex items-center justify-between mb-2">
              <h3 className="text-lg font-medium text-gray-800">Items</h3>
              <button
                type="button"
                onClick={addItem}
                className="bg-[#003973] hover:bg-[#002a57] text-white px-3 py-1 rounded-md text-sm"
              >
                + Add Item
              </button>
            </div>

            <div className="space-y-3">
              {items.map((item, idx) => (
                <div key={idx} className="grid grid-cols-12 gap-3 items-center">
                  <input
                    className="col-span-6 border border-gray-300 rounded-md px-3 py-2 focus:ring-2 focus:ring-[#003973]"
                    placeholder="Product ID"
                    value={item.productId}
                    onChange={e =>
                      handleItemChange(idx, "productId", e.target.value)
                    }
                    required
                  />
                  <input
                    className="col-span-4 border border-gray-300 rounded-md px-3 py-2 focus:ring-2 focus:ring-[#003973]"
                    type="number"
                    min={1}
                    value={item.quantity}
                    onChange={e =>
                      handleItemChange(idx, "quantity", Number(e.target.value))
                    }
                    required
                  />
                  <div className="col-span-2 flex justify-end">
                    {items.length > 1 && (
                      <button
                        type="button"
                        onClick={() => removeItem(idx)}
                        className="border border-[#003973] text-[#003973] hover:bg-[#003973] hover:text-white px-3 py-1 rounded-md text-sm"
                      >
                        Remove
                      </button>
                    )}
                  </div>
                </div>
              ))}
            </div>
          </div>

          <div className="pt-4 border-t flex justify-end gap-3">
            <Link to="/" className="px-3 py-2 border rounded-md text-sm">
              Cancel
            </Link>
            <button
              type="submit"
              disabled={loading}
              className="bg-[#003973] hover:bg-[#002a57] text-white px-4 py-2 rounded-md"
            >
              {loading ? "Saving..." : "Create Order"}
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}
