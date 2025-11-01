"use client";

import { useSidebar } from "@/context/SidebarContext";
import { PanelLeft } from "lucide-react";

interface IHeader {
  title: string;
}

export default function Header({ title }: IHeader) {
  const { toggleSidebar, isCollapsed } = useSidebar();

  const data = new Date();

  const mesExtenso = data
    .toLocaleDateString("pt-BR", {
      month: "long",
    })
    .replace(/^\p{L}/u, (c) => c.toUpperCase());

  return (
    <header className="h-16 border-b border-border bg-card shadow-soft mb-4">
      <div className="flex items-center justify-between px-6 py-4">
        <div className="flex items-center space-x-4">
          <button
            onClick={toggleSidebar}
            className="p-2 hover:bg-gray-100 rounded-lg transition-colors"
            aria-label={isCollapsed ? "Expandir sidebar" : "Minimizar sidebar"}
          >
            <PanelLeft
              className={`transition-transform duration-300 ${
                isCollapsed ? "rotate-180" : ""
              }`}
            />
          </button>
          <h1 className="text-xl font-semibold text-foreground">{title}</h1>
        </div>
        <div className="flex items-center space-x-3">
          <span className="text-sm text-muted-foreground">
            {mesExtenso} de {data.getFullYear()}
          </span>
        </div>
      </div>
    </header>
  );
}
