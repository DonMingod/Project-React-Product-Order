import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import Toolbar from "./components/dashboard/Toolbar";
import OrderList from "./components/pages/OrderList";
import OrderCreate from "./components/pages/CreateOrder";
import OrderEdit from "./components/pages/OrderEdit";

function App() {
  return (
    <Router>
      <div className="h-screen flex flex-col bg-white">

        {/* Top toolbar */}
        <header className="flex-shrink-0">
          <Toolbar />
        </header>

        {/* Body */}
        <div className="flex flex-1 overflow-hidden">

          {/* Sidebar */}
          <aside className="w-64 bg-[#003973] text-white p-6 space-y-6 shadow-inner">
            <h3 className="text-lg font-semibold">Control Panel</h3>

            <nav className="flex flex-col gap-3 text-sm">
              <Link to="/" className="text-white hover:text-gray-100">Orders</Link>
              <Link to="/create" className="text-white hover:text-gray-100">Create Order</Link>
              <span className="opacity-80">User Accounts</span>
              <span className="opacity-80">E-Commerce</span>
              <span className="opacity-80">Settings</span>
            </nav>
          </aside>

          {/* Main content */}
          <main className="flex-1 bg-white p-6 overflow-auto">
            <Routes>
              <Route path="/" element={<OrderList />} />
              <Route path="/create" element={<OrderCreate />} />
              <Route path="/edit/:id" element={<OrderEdit />} />
            </Routes>
          </main>

        </div>
      </div>
    </Router>
  );
}

export default App;
