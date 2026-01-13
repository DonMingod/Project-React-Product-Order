// ...existing code...
import { Link } from "react-router-dom";

export default function Toolbar() {
  return (
    <div className="bg-white border-b border-gray-100">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex h-16 items-center justify-between">
          <div className="flex items-center gap-4">
            <div className="text-xl font-bold text-[#003973]">OrderApp</div>
            <nav className="hidden md:flex gap-3 text-sm">
              {/* placeholder links */}
            </nav>
          </div>
          <div>
          <Link to="/" className="text-sm text-[#003973] hover:underline">
            Back to list
          </Link>
          </div>
        </div>
      </div>
    </div>
  );
}