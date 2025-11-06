"use client";
import { useSidebar } from "@/context/SidebarContext";
import Image from "next/image";
import Link from "next/link";
import { useParams, usePathname, useRouter } from "next/navigation";
import { IoSettingsSharp } from "react-icons/io5";
import { MdOutlineDashboard, MdLogout } from "react-icons/md";

export default function Sidebar() {
  const { id } = useParams();
  const pathname = usePathname();
  const router = useRouter();
  const { isCollapsed } = useSidebar();

  const menuItems = [
    { icon: MdOutlineDashboard, label: "Dashboard", href: `/dashboard/${id}` },
    {
      icon: IoSettingsSharp,
      label: "Configurações",
      href: `${id}/configuracao`,
    },
  ];

  const handleLogout = () => {
    if (confirm("Deseja realmente sair?")) {
      localStorage.removeItem("usuario");
      router.push("/login");
    }
  };

  return (
    <aside
      className={`h-screen bg-white border-r border-gray-200 flex flex-col transition-all duration-300 ${
        isCollapsed ? "w-20" : "w-64"
      }`}
    >
      <div className="p-6 border-b border-gray-200">
        <div className="flex items-center gap-3">
          <Image
            src={"/assets/icon.png"}
            alt="icon fhai"
            width={64}
            height={64}
          />
          {!isCollapsed && (
            <div className="overflow-hidden">
              <h1 className="font-semibold text-gray-900 whitespace-nowrap">
                FHAI
              </h1>
              <p className="text-sm text-gray-500 ">
                Your Assistance Intelligence
              </p>
            </div>
          )}
        </div>
      </div>

      <nav className="flex-1 p-4">
        {!isCollapsed && (
          <p className="px-3 mb-3 text-xs font-medium text-gray-400 uppercase tracking-wider">
            Menu Principal
          </p>
        )}
        <ul className="space-y-1">
          {menuItems.map((item) => {
            const Icon = item.icon;
            const isActive = pathname === item.href;
            return (
              <li key={item.href}>
                <Link
                  href={item.href}
                  className={`flex items-center gap-3 px-3 py-2.5 rounded-lg transition-colors ${
                    isActive
                      ? "bg-gray-100 text-gray-900"
                      : "text-gray-600 hover:bg-gray-50 hover:text-gray-900"
                  } ${isCollapsed ? "justify-center" : ""}`}
                  title={isCollapsed ? item.label : undefined}
                >
                  <Icon size={20} className="shrink-0" />
                  {!isCollapsed && (
                    <span className="font-medium text-sm whitespace-nowrap">
                      {item.label}
                    </span>
                  )}
                </Link>
              </li>
            );
          })}
        </ul>
      </nav>

      <div className="p-4 border-t border-gray-200">
        <button
          onClick={handleLogout}
          className={`w-full flex items-center gap-3 px-3 py-2.5 rounded-lg transition-colors text-red-600 hover:bg-red-50 ${
            isCollapsed ? "justify-center" : ""
          }`}
          title={isCollapsed ? "Sair" : undefined}
        >
          <MdLogout size={20} className="shrink-0" />
          {!isCollapsed && (
            <span className="font-medium text-sm whitespace-nowrap">Sair</span>
          )}
        </button>
      </div>
    </aside>
  );
}
