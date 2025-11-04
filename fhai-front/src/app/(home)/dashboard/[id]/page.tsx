"use client";
import ModalAddTransacao from "@/components/(features)/ModalAddTransacao";
import Header from "@/components/Header";
import { StatusCard } from "@/components/StatusCard";
import Transacao from "@/components/Transacao";
import BoxTransacao from "@/components/TransacoesRecentes";
import { ITransacao } from "@/interfaces/transacao";
import { Plus, TrendingDown, TrendingUp, Wallet } from "lucide-react";
import { useEffect, useState } from "react";

export default function Dashboard() {
  const [usuario, setUsuario] = useState<{
    id: number;
    name: string;
    token: string;
  }>();
  const [transacoes, setTransacoes] = useState<ITransacao[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string>("");
  const [openAddModalTransacao, setOpenAddModalTransacao] = useState(false);

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

  const buscarTransacoes = async (idUsuario: number) => {
    try {
      const response = await fetch(
        `${process.env.NEXT_PUBLIC_API_URL}api/usuario/${idUsuario}/transacoes`,
        {
          method: "GET",
          headers: {
            Authorization: `Bearer ${usuario}`,
            "Content-Type": "application/json",
          },
        }
      );

      if (response.ok) {
        setLoading(false);
        return await response.json();
      } else {
        setError("Erro ao buscar transações");
        console.error("Erro ao buscar transações");
        return [];
      }
    } catch (error) {
      console.error("Erro:", error);
      return [];
    }
  };

  const handleDeletar = async (id: number) => {
    if (!confirm("Tem certeza que desaja excluir essa transação?")) return;

    try {
      const resp = await fetch(
        `${process.env.NEXT_PUBLIC_API_URL}api/transacao/${id}`,
        {
          method: "DELETE",
          headers: { "Content-Type": "application/json" },
        }
      );

      const data = await resp.json();

      if (!resp.ok) {
        alert(data.message || "Erro ao excluir a transação");
        return;
      }
    } catch (err) {
      const message =
        err instanceof Error ? err.message : "Erro ao executar comando";
      alert(message || "Erro ao excluir produto");
    }
  };

  useEffect(() => {
    const usuarioLocal = localStorage.getItem("usuario");
    if (usuarioLocal) {
      const data = JSON.parse(usuarioLocal);
      queueMicrotask(() => setUsuario(data));
    }
  }, []);

  useEffect(() => {
    if (!usuario?.id) return;

    const loadTransacoes = async () => {
      const data = await buscarTransacoes(usuario.id);
      setTransacoes(data);
      setLoading(false);
    };

    loadTransacoes();
  }, [usuario]);

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
                  <BoxTransacao titulo="Transações Recentes">
                    {transacoes.map((transacao) => (
                      <Transacao
                        key={transacao.id}
                        transacao={transacao}
                        onDeletar={() => handleDeletar}
                        onEditar={() => {}}
                      />
                    ))}
                  </BoxTransacao>
                </section>
              </div>
            )}
          </div>
        )}
      </section>

      <button
        onClick={() => setOpenAddModalTransacao(true)}
        className="fixed bottom-8 right-8 w-14 h-14 bg-teal-500 hover:bg-teal-600 text-white rounded-full shadow-lg hover:shadow-xl transition-all flex items-center justify-center group"
        title="Adicionar transação"
      >
        <Plus
          size={24}
          className="group-hover:rotate-90 transition-transform"
        />
      </button>

      <ModalAddTransacao
        isOpen={openAddModalTransacao}
        onClose={() => setOpenAddModalTransacao(false)}
      />
    </main>
  );
}
