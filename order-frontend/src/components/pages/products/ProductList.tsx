import { useEffect, useState } from "react";
import { getProducts } from "../../../service/productApi";
import type { ProductResponse } from "../../../Dtos/productResponse/ProductResponse";

export default function ProductList() {
  const [products, setProducts] = useState<ProductResponse[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    getProducts()
      .then(res => setProducts(res.data))
      .finally(() => setLoading(false));
  }, []);

  if (loading) return <div className="p-4">Loading...</div>;

  return (
    <div className="h-full flex flex-col">
      <div className="mb-4">
        <h1 className="text-2xl font-semibold">Products</h1>
        <p className="text-sm text-gray-500">
          List of available products
        </p>
      </div>

      <div className="bg-white rounded-lg p-4 shadow-sm flex-1 min-h-0">
        <div className="overflow-auto h-full">
          <table className="w-full text-left table-auto">
            <thead className="bg-gray-50">
              <tr>
                <th className="p-3">ID</th>
                <th className="p-3">Name</th>
                <th className="p-3">Price</th>
              </tr>
            </thead>
            <tbody>
              {products.map(product => (
                <tr key={product.id} className="border-b">
                  <td className="p-3">{product.id}</td>
                  <td className="p-3">{product.name}</td>
                  <td className="p-3">${product.price}</td>
                </tr>
              ))}
            </tbody>
          </table>

          {products.length === 0 && (
            <p className="text-gray-600 p-4">No products found.</p>
          )}
        </div>
      </div>
    </div>
  );
}
