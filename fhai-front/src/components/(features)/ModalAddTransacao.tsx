import { ITransacao } from "@/interfaces/transacao";
import { X } from "lucide-react";
import { useState } from "react";

interface ModalAdicionarTransacaoProps {
  isOpen: boolean;
  onClose: () => void;
}

export default function ModalAddTransacao({
  isOpen,
  onClose,
}: ModalAdicionarTransacaoProps) {
  const [formData, setFormData] = useState({
    nm_transacao: "",
    ds_transacao: "",
    vl_transacao: "",
    tp_transacao: "receita",
    dt_transacao: new Date().toISOString().split("T")[0],
    cartao: null,
  });

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      const novaTransacao: ITransacao = {
        nm_transacao: formData.nm_transacao,
        ds_transacao: formData.ds_transacao,
        vl_transacao: parseFloat(formData.vl_transacao),
        tp_transacao: formData.tp_transacao,
        dt_transacao: new Date(formData.dt_transacao).toISOString(),
      };

      const resp = await fetch(`${process.env.NEXT_PUBLIC_API_URL}transacao`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(novaTransacao),
      });

      if (!resp.ok) {
        throw new Error("Erro ao criar transação");
      }

      setFormData({
        nm_transacao: "",
        ds_transacao: "",
        vl_transacao: "",
        tp_transacao: "receita",
        dt_transacao: new Date().toISOString().split("T")[0],
        cartao: null,
      });

      onClose();
    } catch (error) {
      console.error("Erro ao adicionar:", error);
    }
  };

  const handleClose = () => {
    setFormData({
      nm_transacao: "",
      ds_transacao: "",
      vl_transacao: "",
      tp_transacao: "receita",
      dt_transacao: new Date().toISOString().split("T")[0],
      cartao: null,
    });
    onClose();
  };

  if (!isOpen) return null;

  return (
    <div className="fixed inset-0 bg-black/40 flex items-center justify-center z-50 p-4">
      <div className="bg-white rounded-2xl shadow-2xl w-full max-w-md max-h-[90vh] overflow-y-auto">
        <div className="flex items-center justify-between p-6 border-b border-gray-200">
          <h2 className="text-xl font-semibold text-gray-900 flex items-center gap-2">
            <span className="text-teal-500">+</span>
            Adicionar Transação
          </h2>
          <button
            onClick={handleClose}
            className="p-2 hover:bg-gray-100 rounded-lg transition-colors"
          >
            <X size={20} className="text-gray-500" />
          </button>
        </div>

        <form onSubmit={handleSubmit} className="p-6 space-y-5">
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-2">
              Tipo
            </label>
            <select
              value={formData.tp_transacao}
              onChange={(e) =>
                setFormData({ ...formData, tp_transacao: e.target.value })
              }
              className="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-teal-500 focus:border-transparent outline-none transition"
              required
            >
              <option value="receita">💰 Receita</option>
              <option value="despesa">💸 Despesa</option>
            </select>
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700 mb-2">
              Título da{" "}
              {formData.tp_transacao === "receita" ? "Receita" : "Despesa"}
            </label>
            <input
              type="text"
              value={formData.nm_transacao}
              onChange={(e) =>
                setFormData({ ...formData, nm_transacao: e.target.value })
              }
              placeholder="Ex: Salário, Freelance, Aluguel..."
              className="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-teal-500 focus:border-transparent outline-none transition"
              required
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700 mb-2">
              Descrição{" "}
              <span className="text-gray-400 font-normal">(opcional)</span>
            </label>
            <textarea
              value={formData.ds_transacao}
              onChange={(e) =>
                setFormData({ ...formData, ds_transacao: e.target.value })
              }
              placeholder="Adicione detalhes sobre esta transação..."
              rows={3}
              className="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-teal-500 focus:border-transparent outline-none transition resize-none"
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700 mb-2">
              Valor (R$)
            </label>
            <div className="relative">
              <span className="absolute left-4 top-1/2 -translate-y-1/2 text-gray-500 font-medium">
                R$
              </span>
              <input
                type="number"
                step="0.01"
                min="0"
                value={formData.vl_transacao}
                onChange={(e) =>
                  setFormData({ ...formData, vl_transacao: e.target.value })
                }
                placeholder="0,00"
                className="w-full pl-12 pr-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-teal-500 focus:border-transparent outline-none transition"
                required
              />
            </div>
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700 mb-2">
              Data
            </label>
            <input
              type="date"
              value={formData.dt_transacao}
              onChange={(e) =>
                setFormData({ ...formData, dt_transacao: e.target.value })
              }
              className="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-teal-500 focus:border-transparent outline-none transition"
              required
            />
          </div>

          <div className="flex gap-3 pt-4">
            <button
              type="button"
              onClick={handleClose}
              className="flex-1 px-4 py-3 border border-gray-300 text-gray-700 rounded-lg hover:bg-gray-50 transition-colors font-medium disabled:opacity-50"
            >
              Cancelar
            </button>
            <button
              type="submit"
              className="flex-1 px-4 py-3 bg-teal-500 text-white rounded-lg hover:bg-teal-600 transition-colors font-medium disabled:opacity-50 flex items-center justify-center gap-2"
            >
              Adicionar
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}
