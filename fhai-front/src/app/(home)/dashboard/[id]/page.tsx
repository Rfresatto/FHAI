"use client";
import ModalAddTransacao from "@/components/(features)/ModalAddTransacao";
import ModalEditarTransacao from "@/components/(features)/ModalEditTransacao";
import Header from "@/components/Header";
import { StatusCard } from "@/components/StatusCard";
import Transacao from "@/components/Transacao";
import BoxTransacao from "@/components/TransacoesRecentes";
import { ITransacao } from "@/interfaces/transacao";
import { useParams } from "next/navigation";
import { useCallback, useEffect, useMemo, useState } from "react";
import { FaPlus } from "react-icons/fa";
import { LuTrendingDown, LuTrendingUp, LuWallet } from "react-icons/lu";

export default function Dashboard() {
  const [transacoes, setTransacoes] = useState<ITransacao[]>([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string>("");
  const [openAddModalTransacao, setOpenAddModalTransacao] = useState(false);
  const [modalEditOpen, setModalEditOpen] = useState(false);
  const [transacaoSelecionada, setTransacaoSelecionada] =
    useState<ITransacao | null>(null);
  const { id } = useParams();

  const formatCurrency = useCallback((value: number) => {
    return new Intl.NumberFormat("pt-BR", {
      minimumFractionDigits: 2,
      maximumFractionDigits: 2,
    }).format(value);
  }, []);

  const transacoesPorTipo = useMemo(() => {
    const receitas = transacoes.filter(
      (t) => t.tp_transacao.toLowerCase() === "receita"
    );
    const despesas = transacoes.filter(
      (t) => t.tp_transacao.toLowerCase() === "despesa"
    );
    return { receitas, despesas };
  }, [transacoes]);

  const totais = useMemo(() => {
    const totalReceitas = transacoesPorTipo.receitas.reduce(
      (acc, t) => acc + t.vl_transacao,
      0
    );
    const totalDespesas = transacoesPorTipo.despesas.reduce(
      (acc, t) => acc + Math.abs(t.vl_transacao),
      0
    );
    const saldoFinal = totalReceitas - totalDespesas;
    return { totalReceitas, totalDespesas, saldoFinal };
  }, [transacoesPorTipo]);

  const contadores = useMemo(
    () => ({
      receitas: transacoesPorTipo.receitas.length,
      despesas: transacoesPorTipo.despesas.length,
    }),
    [transacoesPorTipo]
  );

  const buscarTransacoes = useCallback(async (idUsuario: number) => {
    try {
      const response = await fetch(
        `${process.env.NEXT_PUBLIC_API_URL}api/usuario/${idUsuario}/transacoes`,
        {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
          },
        }
      );

      if (response.ok) {
        const data = await response.json();
        return data;
      } else {
        throw new Error("Erro ao buscar transações");
      }
    } catch (error) {
      console.error("Erro:", error);
      throw error;
    }
  }, []);

  const deletarTransacao = async (idTransacao: number) => {
    if (!confirm("Deseja realmente excluir esta transação?")) return;

    try {
      const response = await fetch(
        `${process.env.NEXT_PUBLIC_API_URL}api/usuario/${id}/transacoes/${idTransacao}`,
        {
          method: "DELETE",
        }
      );

      if (response.ok) {
        alert("Transação excluída com sucesso!");

        if (id) {
          setLoading(true);
          setError("");
          try {
            const data = await buscarTransacoes(Number(id));
            setTransacoes(data);
          } catch (error) {
            console.error(error);

            setError("Erro ao recarregar transações");
          } finally {
            setLoading(false);
          }
        }
      } else {
        throw new Error("Erro ao deletar transação");
      }
    } catch (error) {
      console.error("Erro ao deletar:", error);
      alert("Erro ao deletar transação");
    }
  };

  useEffect(() => {
    if (!id) return;

    let isMounted = true;

    const loadTransacoes = async () => {
      setLoading(true);
      setError("");

      try {
        const data = await buscarTransacoes(Number(id));
        if (isMounted) {
          setTransacoes(data);
        }
      } catch (error) {
        if (isMounted) {
          console.error(error);
          setError("Erro ao buscar transações");
        }
      } finally {
        if (isMounted) {
          setLoading(false);
        }
      }
    };

    loadTransacoes();

    return () => {
      isMounted = false;
    };
  }, [id, buscarTransacoes]);

  return (
    <main className="container mx-auto p-4">
      <Header title="Dashboard" />

      <section className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
        <StatusCard
          title="Receita Total"
          value={formatCurrency(totais.totalReceitas)}
          prefix="R$"
          subtitle="Total de receitas"
          change={{
            value: `${contadores.receitas} transações`,
            positive: true,
          }}
          icon={<LuTrendingUp className="text-teal-500" />}
        />

        <StatusCard
          title="Despesas Total"
          value={formatCurrency(totais.totalDespesas)}
          prefix="R$"
          subtitle="Total de despesas"
          change={{
            value: `${contadores.despesas} transações`,
            positive: false,
          }}
          icon={<LuTrendingDown className="text-red-500" />}
        />

        <StatusCard
          title="Saldo Final"
          value={formatCurrency(Math.abs(totais.saldoFinal))}
          prefix="R$"
          subtitle={totais.saldoFinal >= 0 ? "Superávit" : "Déficit"}
          change={{
            value: totais.saldoFinal >= 0 ? "Positivo" : "Negativo",
            positive: totais.saldoFinal >= 0,
          }}
          icon={<LuWallet className="text-teal-500" />}
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
                        onDeletar={() => deletarTransacao(transacao.id!)}
                        onEditar={() => {
                          setModalEditOpen(true);
                          setTransacaoSelecionada(transacao);
                        }}
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
        <FaPlus
          size={24}
          className="group-hover:rotate-90 transition-transform"
        />
      </button>

      <ModalAddTransacao
        isOpen={openAddModalTransacao}
        onClose={() => setOpenAddModalTransacao(false)}
        onSuccess={async () => {
          if (id) {
            setLoading(true);
            setError("");
            try {
              const data = await buscarTransacoes(Number(id));
              setTransacoes(data);
            } catch (error) {
              console.error(error);
              setError("Erro ao recarregar transações");
            } finally {
              setLoading(false);
            }
          }
        }}
      />

      <ModalEditarTransacao
        isOpen={modalEditOpen}
        onClose={() => {
          setModalEditOpen(false);
          setTransacaoSelecionada(null);
        }}
        transacao={transacaoSelecionada}
        onSuccess={async () => {
          if (id) {
            setLoading(true);
            setError("");
            try {
              const data = await buscarTransacoes(Number(id));
              setTransacoes(data);
            } catch (error) {
              console.error(error);
              setError("Erro ao recarregar transações");
            } finally {
              setLoading(false);
            }
          }
        }}
      />
    </main>
  );
}
