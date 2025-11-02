import { ITransacao } from "@/interfaces/transacao";
import { TrendingUp, TrendingDown } from "lucide-react";

interface TransacoesRecentesProps {
  transacoes: ITransacao[];
  titulo?: string;
  limite?: number;
  onVerTodas?: () => void;
}

export default function TransacoesRecentes({
  transacoes,
  titulo = "Transações Recentes",
  limite = 4,
  onVerTodas,
}: TransacoesRecentesProps) {
  const formatCurrency = (value: number) => {
    return new Intl.NumberFormat("pt-BR", {
      style: "currency",
      currency: "BRL",
    }).format(value);
  };

  const formatDate = (dateString: string) => {
    const date = new Date(dateString);
    const today = new Date();
    const yesterday = new Date(today);
    yesterday.setDate(yesterday.getDate() - 1);

    if (date.toDateString() === today.toDateString()) {
      return "Hoje";
    } else if (date.toDateString() === yesterday.toDateString()) {
      return "Ontem";
    } else {
      const diffTime = Math.abs(today.getTime() - date.getTime());
      const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));

      if (diffDays < 7) {
        return `${diffDays} dias atrás`;
      }

      return date.toLocaleDateString("pt-BR", {
        day: "2-digit",
        month: "2-digit",
        year: "numeric",
      });
    }
  };
  const transacoesLimitadas = transacoes.slice(0, limite);

  return (
    <div className="bg-white rounded-xl border border-gray-200">
      <div className="px-6 py-4 border-b border-gray-200 flex items-center justify-between">
        <h2 className="text-lg font-semibold text-gray-900">{titulo}</h2>
        {onVerTodas && transacoes.length > 0 && (
          <button
            onClick={onVerTodas}
            className="text-sm text-gray-600 hover:text-gray-900 transition-colors"
          >
            Ver todas
          </button>
        )}
      </div>

      <div className="divide-y divide-gray-100">
        {transacoesLimitadas.length === 0 ? (
          <div className="p-8 text-center text-gray-500">
            Nenhuma transação encontrada
          </div>
        ) : (
          transacoesLimitadas.map((transacao) => {
            const isReceita =
              transacao.tp_transacao.toLowerCase() === "receita";
            const Icon = isReceita ? TrendingUp : TrendingDown;
            const bgColor = isReceita ? "bg-teal-100" : "bg-red-100";
            const iconColor = isReceita ? "text-teal-600" : "text-red-600";
            const valorColor = isReceita ? "text-teal-600" : "text-red-600";

            return (
              <div
                key={transacao.id_transacao}
                className="px-6 py-4 flex items-center justify-between hover:bg-gray-50 transition-colors"
              >
                <div className="flex items-center gap-4">
                  <div
                    className={`w-10 h-10 rounded-full ${bgColor} flex items-center justify-center shrink-0`}
                  >
                    <Icon className={iconColor} size={18} />
                  </div>

                  <div>
                    <h3 className="font-medium text-gray-900">
                      {transacao.nm_transacao}
                    </h3>
                    <p className="text-sm text-gray-500 mt-0.5">
                      {formatDate(transacao.dt_transacao)}
                    </p>
                  </div>
                </div>

                <span className={`text-lg font-semibold ${valorColor}`}>
                  {isReceita ? "+" : "-"}{" "}
                  {formatCurrency(Math.abs(transacao.vl_transacao))}
                </span>
              </div>
            );
          })
        )}
      </div>
    </div>
  );
}
