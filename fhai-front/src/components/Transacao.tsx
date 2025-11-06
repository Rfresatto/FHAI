import { ITransacao } from "@/interfaces/transacao";
import { FaTrash } from "react-icons/fa";
import { IoIosTrendingDown, IoIosTrendingUp } from "react-icons/io";
import { MdEdit } from "react-icons/md";

interface TransacoesProps {
  transacao: ITransacao;
  onEditar?: () => void;
  onDeletar?: () => void;
}

const Transacao = ({ transacao, onDeletar, onEditar }: TransacoesProps) => {
  const formatCurrency = (value: number) => {
    return new Intl.NumberFormat("pt-BR", {
      style: "currency",
      currency: "BRL",
    }).format(value);
  };

  const formatDate = (dateString: string) => {
    const normalizarData = (data: Date) => {
      return new Date(data.getFullYear(), data.getMonth(), data.getDate() + 1);
    };

    const date = normalizarData(new Date(dateString));

    return date.toLocaleDateString("pt-BR", {
      day: "2-digit",
      month: "2-digit",
      year: "numeric",
    });
  };

  const isReceita = transacao.tp_transacao.toLowerCase() === "receita";
  const Icon = isReceita ? IoIosTrendingUp : IoIosTrendingDown;
  const bgColor = isReceita ? "bg-teal-100" : "bg-red-100";
  const iconColor = isReceita ? "text-teal-600" : "text-red-600";
  const valorColor = isReceita ? "text-teal-600" : "text-red-600";

  return (
    <div className="divide-y divide-gray-100">
      {!transacao ? (
        <div className="p-8 text-center text-gray-500">
          Nenhuma transação encontrada
        </div>
      ) : (
        <div className="px-6 py-4 flex items-center justify-between hover:bg-gray-50 transition-colors">
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

          <div className="flex gap-8">
            <span className={`text-lg font-semibold ${valorColor}`}>
              {isReceita ? "+" : "-"}{" "}
              {formatCurrency(Math.abs(transacao.vl_transacao))}
            </span>
            <span className="cursor-pointer text-blue-500" onClick={() => {}}>
              <MdEdit onClick={onEditar} />
            </span>
            <span className="cursor-pointer text-red-400" onClick={() => {}}>
              <FaTrash onClick={onDeletar} />
            </span>
          </div>
        </div>
      )}
    </div>
  );
};

export default Transacao;
