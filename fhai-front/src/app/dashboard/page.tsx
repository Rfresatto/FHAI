"use client";
import Header from "@/components/Header";
import { StatusCard } from "@/components/StatusCard";
import { TUsuarios } from "@/interfaces/usuario";
import { TrendingUp } from "lucide-react";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

export default function Dashboard() {
  const [transacao, setTransacao] = useState<TUsuarios[]>([]);
  const [loading, setLoading] = useState(true);
  const router = useRouter();

  useEffect(() => {
    const token = localStorage.getItem("admin_token");
    if (!token) {
      router.push("/login");
      return;
    }

    async function fetchTransacao() {
      try {
        const res = await fetch(`/api/transacao`);
        let data = [];
        if (res.ok) {
          data = await res.json();
        }
        setTransacao(data);
      } catch (err) {
        console.error(err);
      } finally {
        setLoading(false);
      }
    }
    fetchTransacao();
  }, [router]);

  return (
    <main className="container mx-auto p-8">
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
          <p className="mt-6">Carregando...</p>
        ) : (
          <div className="mt-6 overflow-x-auto">
            {transacao.length === 0 && (
              <p className="text-center py-4">Nenhuma transação cadastrada.</p>
            )}
            {transacao.map((usuario) => (
              <>{usuario}</>
            ))}
          </div>
        )}
      </section>
    </main>
  );
}
