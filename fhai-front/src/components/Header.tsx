"use client";
import { useSidebar } from "@/context/SidebarContext";
import { LuPanelLeft } from "react-icons/lu";

interface IHeader {
  title: string;
}

export default function Header({ title }: IHeader) {
  const { toggleSidebar, isCollapsed } = useSidebar();

  return (
    <header className="h-16 border-b border-border bg-card shadow-soft mb-4">
      <div className="flex items-center justify-between px-6 py-4">
        <div className="flex items-center space-x-4">
          <button
            onClick={toggleSidebar}
            className="p-2 hover:bg-gray-100 rounded-lg transition-colors"
            aria-label={isCollapsed ? "Expandir sidebar" : "Minimizar sidebar"}
          >
            <LuPanelLeft
              className={`transition-transform duration-300 ${
                isCollapsed ? "rotate-180" : ""
              }`}
            />
          </button>
          <h1 className="text-xl font-semibold text-foreground">{title}</h1>
        </div>
      </div>
    </header>
  );
}
