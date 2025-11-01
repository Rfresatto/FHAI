"use client";
import Header from "@/components/Header";
import { StatusCard } from "@/components/StatusCard";
import { TUsuarios } from "@/interfaces/usuario";
import { TrendingUp } from "lucide-react";
import { useEffect, useState } from "react";

export default function Dashboard() {
  const [transacao, setTransacao] = useState<TUsuarios[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string>("");

  useEffect(() => {
    async function fetchTransacao() {
      try {
        const res = await fetch(
          `${process.env.NEXT_PUBLIC_API_URL}api/transacao`,
          {
            method: "GET",
            headers: {
              "Content-Type": "application/json",
            },
          }
        );

        if (!res.ok) {
          throw new Error(`Erro na API: ${res.status} - ${res.statusText}`);
        }

        const data = await res.json();
        console.log("Dados recebidos:", data);
        setTransacao(data);
      } catch (err) {
        console.error("Erro ao buscar transações:", err);
        setError(err instanceof Error ? err.message : "Erro desconhecido");
      } finally {
        setLoading(false);
      }
    }

    fetchTransacao();
  }, []);

  return (
    <main className="container mx-auto p-4">
      <Header title="Dashboard" />

      <section className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
        <StatusCard
          title="Receita Total"
          value="24.850,00"
          prefix="R$"
          subtitle="Este mês"
          change={{ value: "+12,5%", positive: true }}
          icon={TrendingUp}
        />
      </section>

      <section>
        {loading ? (
          <div className="mt-6 text-center">
            <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-teal-500 mx-auto"></div>
            <p className="mt-4 text-gray-600">Carregando transações...</p>
          </div>
        ) : error ? (
          <div className="mt-6 p-4 bg-red-50 border border-red-200 rounded-lg">
            <p className="text-red-800 font-medium">Erro ao carregar dados:</p>
            <p className="text-red-600 text-sm mt-1">{error}</p>
          </div>
        ) : (
          <div className="mt-6 overflow-x-auto">
            {transacao.length === 0 ? (
              <p className="text-center py-4 text-gray-500">
                Nenhuma transação cadastrada.
              </p>
            ) : (
              <div className="space-y-2">
                {transacao.map((usuario, index) => (
                  <div
                    key={usuario.id || index}
                    className="p-4 bg-white rounded-lg border border-gray-200"
                  >
                    <pre className="text-sm">
                      {JSON.stringify(usuario, null, 2)}
                    </pre>
                  </div>
                ))}
              </div>
            )}
          </div>
        )}
      </section>
    </main>
  );
}
