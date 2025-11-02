"use client";
import Header from "@/components/Header";
import { StatusCard } from "@/components/StatusCard";
import TransacoesRecentes from "@/components/TransacoesRecentes";
import { ITransacao } from "@/interfaces/transacao";
import { TrendingDown, TrendingUp, Wallet } from "lucide-react";
import { useEffect, useState } from "react";

export default function Dashboard() {
  const [transacoes, setTransacoes] = useState<ITransacao[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string>("");

  const totalReceitas = transacoes
    .filter((t) => t.tp_transacao.toLowerCase() === "receita")
    .reduce((acc, t) => acc + t.vl_transacao, 0);

  const totalDespesas = transacoes
    .filter((t) => t.tp_transacao.toLowerCase() === "saída")
    .reduce((acc, t) => acc + Math.abs(t.vl_transacao), 0);

  const saldoFinal = totalReceitas - totalDespesas;

  const formatCurrency = (value: number) => {
    return new Intl.NumberFormat("pt-BR", {
      minimumFractionDigits: 2,
      maximumFractionDigits: 2,
    }).format(value);
  };

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
        setTransacoes(data);
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
          value={formatCurrency(totalReceitas)}
          prefix="R$"
          subtitle="Total de receitas"
          change={{
            value: `${
              transacoes.filter(
                (t) => t.tp_transacao.toLowerCase() === "receita"
              ).length
            } transações`,
            positive: true,
          }}
          icon={TrendingUp}
        />

        <StatusCard
          title="Despesas Total"
          value={formatCurrency(totalDespesas)}
          prefix="R$"
          subtitle="Total de despesas"
          change={{
            value: `${
              transacoes.filter((t) => t.tp_transacao.toLowerCase() === "saída")
                .length
            } transações`,
            positive: false,
          }}
          icon={TrendingDown}
        />

        <StatusCard
          title="Saldo Final"
          value={formatCurrency(Math.abs(saldoFinal))}
          prefix="R$"
          subtitle={saldoFinal >= 0 ? "Superávit" : "Déficit"}
          change={{
            value: saldoFinal >= 0 ? "Positivo" : "Negativo",
            positive: saldoFinal >= 0,
          }}
          icon={Wallet}
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
            {!transacoes || transacoes.length === 0 ? (
              <p className="text-center py-4 text-gray-500">
                Nenhuma transação cadastrada.
              </p>
            ) : (
              <div className="space-y-2">
                <section>
                  <TransacoesRecentes
                    transacoes={transacoes}
                    titulo="Transações Recentes"
                  />
                </section>
              </div>
            )}
          </div>
        )}
      </section>
    </main>
  );
}
