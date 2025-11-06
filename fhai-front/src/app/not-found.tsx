"use client";
import { useRouter } from "next/navigation";

export default function NotFound() {
  const router = useRouter();

  return (
    <div className="min-h-screen bg-linear-to-br from-green-50 to-emerald-50 flex items-center justify-center px-4">
      <div className="max-w-md w-full text-center space-y-8">
        <div className="space-y-4">
          <h1 className="text-8xl font-bold text-green-600">404</h1>
          <h2 className="text-2xl font-semibold text-gray-700">
            Página não encontrada
          </h2>
          <p className="text-gray-500">
            A página que você está procurando não existe.
          </p>
        </div>

        <div className="flex flex-col gap-3">
          <button
            onClick={() => router.push("/")}
            className="w-full px-6 py-3 bg-teal-500 text-white rounded-lg font-medium hover:bg-green-700 transition-colors"
          >
            Voltar para Home
          </button>

          <button
            onClick={() => router.back()}
            className="w-full px-6 py-3 bg-white text-teal-500 border border-teal-800 rounded-lg font-medium hover:bg-green-50 transition-colors"
          >
            Voltar para página anterior
          </button>
        </div>
      </div>
    </div>
  );
}
