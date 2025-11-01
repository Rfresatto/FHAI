"use client";

import { useSidebar } from "@/context/SidebarContext";
import { LayoutDashboard, Settings } from "lucide-react";
import Image from "next/image";
import Link from "next/link";
import { usePathname } from "next/navigation";

const menuItems = [
  { icon: LayoutDashboard, label: "Dashboard", href: "/dashboard" },
  { icon: Settings, label: "Configurações", href: "/configuracao" },
];

export default function Sidebar() {
  const pathname = usePathname();
  const { isCollapsed } = useSidebar();

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
    </aside>
  );
}
