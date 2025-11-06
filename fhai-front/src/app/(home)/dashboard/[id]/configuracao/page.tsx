"use client";
import Header from "@/components/Header";
import { TUsuarios } from "@/interfaces/usuario";
import { useParams } from "next/navigation";
import { useEffect, useState } from "react";
import { FaCamera, FaMapMarkerAlt, FaUser } from "react-icons/fa";

const OPTIONS = [
  {
    id: 1,
    value: "M",
    description: "Masculino",
  },
  {
    id: 2,
    value: "F",
    description: "Feminino",
  },
  {
    id: 3,
    value: "O",
    description: "Outros",
  },
];

export default function ConfiguracoesPage() {
  const { id } = useParams();
  const [abaAtiva, setAbaAtiva] = useState("perfil");
  const [loading, setLoading] = useState(false);
  const [usuario, setUsuario] = useState(undefined);

  const [formPerfilData, setFormPerfilData] = useState({
    nome: "",
    senha: "",
    sexo: "",
    email: "",
    contato: "",
  });

  const [formEnderecoData, setFormEnderecoData] = useState({
    id: "",
    logradouro: "",
    nr_residencia: "",
    complemento: "",
    cep: "",
    cidade: "",
    estado: "",
    referencia: "",
  });
  const abas = [
    { id: "perfil", label: "Perfil", icon: FaUser },
    { id: "endereco", label: "Endereço", icon: FaMapMarkerAlt },
  ];

  const handleSalvarPerfil = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);

    const dadosAtualizar = {
      id: Number(id),
      nome: formPerfilData.nome,
      senha: formPerfilData.senha,
      sexo: formPerfilData.sexo,
      email: formPerfilData.email,
      contato: formPerfilData.contato,
    };

    try {
      const response = await fetch(
        `${process.env.NEXT_PUBLIC_API_URL}api/usuario/${id}`,
        {
          method: "PUT",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(dadosAtualizar),
        }
      );

      if (!response.ok) {
        console.error("Erro ao realizar chamada.");
      }
      const updateStorage = { id: Number(id), nome: dadosAtualizar.nome };
      localStorage.setItem("usuario", JSON.stringify(updateStorage));
      setFormPerfilData(dadosAtualizar);
      alert("Perfil atualizado com sucesso!");
    } catch (error) {
      console.error("Erro ao salvar:", error);
      alert("Erro ao atualizar perfil");
    } finally {
      setLoading(false);
    }
  };

  const handleSalvarEndereco = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);

    if (formEnderecoData?.id) {
      try {
        const response = await fetch(
          `${process.env.NEXT_PUBLIC_API_URL}api/usuario/${id}/endereco/${formEnderecoData?.id}`,
          {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(formEnderecoData),
          }
        );
        if (!response.ok) {
          console.error("Erro na chamada");
        }

        setLoading(false);
        alert("Endereço atualizado com sucesso!");
      } catch (error) {
        console.error("Erro ao atualizado o Endereço", error);
      }
    } else {
      try {
        const response = await fetch(
          `${process.env.NEXT_PUBLIC_API_URL}api/usuario/${id}/endereco`,
          {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(formEnderecoData),
          }
        );
        if (!response.ok) {
          console.error("Erro na chamada");
        }

        setLoading(false);
        alert("Endereço criado com sucesso!");
      } catch (error) {
        console.error("Erro ao criar o Endereço", error);
      }
    }
  };

  const getInitials = (nome: string) => {
    if (!nome) return "?";
    return nome
      .split(" ")
      .map((n) => n[0])
      .join("")
      .toUpperCase()
      .slice(0, 2);
  };

  useEffect(() => {
    if (abaAtiva === "perfil") {
      const carregarUsuario = async () => {
        try {
          const response = await fetch(
            `${process.env.NEXT_PUBLIC_API_URL}api/usuario/${id}`,
            {
              method: "GET",
              headers: {
                "Content-Type": "application/json",
              },
            }
          );

          if (!response.ok) throw new Error("Erro ao buscar usuário");

          const data = await response.json();

          setFormPerfilData({
            nome: data.nome,
            email: data.email,
            contato: data.contato,
            sexo: data.sexo,
            senha: data.senha,
          });

          localStorage.setItem(
            "usuario",
            JSON.stringify({ id: Number(id), nome: data.nome })
          );
        } catch (error) {
          console.error("Erro ao carregar usuário:", error);
        }
      };

      carregarUsuario();
    }

    if (abaAtiva === "endereco") {
      const carregarEndereco = async () => {
        try {
          const response = await fetch(
            `${process.env.NEXT_PUBLIC_API_URL}api/usuario/${id}`,
            {
              method: "GET",
              headers: {
                "Content-Type": "application/json",
              },
            }
          );

          if (!response.ok) throw new Error("Erro ao buscar usuário");

          const data = await response.json();
          setFormEnderecoData({
            id: data.enderecos[0]?.id ?? "",
            logradouro: data.enderecos[0]?.logradouro ?? "",
            nr_residencia: data.enderecos[0]?.nr_residencia ?? "",
            complemento: data.enderecos[0]?.complemento ?? "",
            cep: data.enderecos[0]?.cep ?? "",
            cidade: data.enderecos[0]?.cidade ?? "",
            estado: data.enderecos[0]?.estado ?? "",
            referencia: data.enderecos[0]?.referencia ?? "",
          });
        } catch (error) {
          console.error("Erro ao carregar endereco:", error);
        }
      };

      carregarEndereco();
    }
  }, [abaAtiva, id]);

  useEffect(() => {
    try {
      const userString = localStorage.getItem("usuario");
      if (userString) {
        setUsuario(JSON.parse(userString).nome);
      }
    } catch (err) {
      console.error(err);
    }
  }, []);

  if (!usuario) return null;

  return (
    <div className="min-h-screen bg-gray-50 p-8">
      <Header title="Configurações" />

      <div className="bg-white rounded-lg shadow-sm">
        <div className="border-b border-gray-200">
          <nav className="flex -mb-px">
            {abas.map((aba) => {
              const Icon = aba.icon;
              const isActive = abaAtiva === aba.id;
              return (
                <button
                  key={aba.id}
                  onClick={() => setAbaAtiva(aba.id)}
                  className={`flex items-center gap-2 px-6 py-4 border-b-2 font-medium text-sm transition-colors ${
                    isActive
                      ? "border-teal-500 text-teal-600"
                      : "border-transparent text-gray-500 hover:text-gray-700 hover:border-gray-300"
                  }`}
                >
                  <Icon size={18} />
                  {aba.label}
                </button>
              );
            })}
          </nav>
        </div>

        <div className="p-6">
          {abaAtiva === "perfil" && (
            <div>
              <div className="mb-6">
                <h2 className="text-lg font-semibold text-gray-900">
                  Informações Pessoais
                </h2>
                <p className="text-sm text-gray-500 mt-1">
                  Atualize suas informações de perfil
                </p>
              </div>

              <div className="flex items-center gap-4 mb-8">
                <div className="relative">
                  {usuario && (
                    <div className="w-20 h-20 rounded-full bg-teal-500 flex items-center justify-center text-white text-2xl font-semibold">
                      {getInitials(usuario)}
                    </div>
                  )}

                  <button
                    type="button"
                    className="absolute bottom-0 right-0 w-8 h-8 bg-white border-2 border-gray-200 rounded-full flex items-center justify-center hover:bg-gray-50 transition-colors cursor-not-allowed"
                    title="Alterar foto"
                  >
                    <FaCamera className="text-gray-600" size={14} />
                  </button>
                </div>
                <div>
                  <button
                    type="button"
                    className="text-sm text-teal-600 font-medium"
                  >
                    Alterar foto
                  </button>
                  <p className="text-xs text-gray-500 mt-1">
                    JPG, PNG ou GIF. Máx 2MB.
                  </p>
                </div>
              </div>

              <form onSubmit={handleSalvarPerfil}>
                <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
                  <div className="md:col-span-2">
                    <label className="block text-sm font-medium text-gray-700 mb-2">
                      Nome completo
                    </label>
                    <input
                      type="text"
                      value={formPerfilData.nome}
                      onChange={(e) =>
                        setFormPerfilData({
                          ...formPerfilData,
                          nome: e.target.value,
                        })
                      }
                      className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-teal-500 focus:border-transparent outline-none transition-all"
                      required
                    />
                  </div>

                  <div>
                    <label className="block text-sm font-medium text-gray-700 mb-2">
                      Email
                    </label>
                    <input
                      type="email"
                      value={formPerfilData.email}
                      onChange={(e) =>
                        setFormPerfilData({
                          ...formPerfilData,
                          email: e.target.value,
                        })
                      }
                      className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-teal-500 focus:border-transparent outline-none transition-all"
                      required
                    />
                  </div>

                  <div>
                    <label className="block text-sm font-medium text-gray-700 mb-2">
                      Telefone
                    </label>
                    <input
                      type="tel"
                      value={formPerfilData.contato}
                      onChange={(e) =>
                        setFormPerfilData({
                          ...formPerfilData,
                          contato: e.target.value,
                        })
                      }
                      placeholder="(11) 99999-9999"
                      className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-teal-500 focus:border-transparent outline-none transition-all"
                      required
                    />
                  </div>

                  <div>
                    <label className="block text-sm font-medium text-gray-700 mb-2">
                      Sexo
                    </label>
                    <select
                      value={formPerfilData.sexo}
                      onChange={(e) =>
                        setFormPerfilData({
                          ...formPerfilData,
                          sexo: e.target.value,
                        })
                      }
                      className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-teal-500 focus:border-transparent outline-none transition-all bg-white"
                      required
                    >
                      <option value="">Selecione o sexo</option>
                      {OPTIONS.map((option) => (
                        <option key={option.id} value={option.value}>
                          {option.description}
                        </option>
                      ))}
                    </select>
                  </div>

                  <div>
                    <label className="block text-sm font-medium text-gray-700 mb-2">
                      Senha
                    </label>
                    <input
                      type="password"
                      value={formPerfilData.senha}
                      onChange={(e) =>
                        setFormPerfilData({
                          ...formPerfilData,
                          senha: e.target.value,
                        })
                      }
                      placeholder="••••••••"
                      className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-teal-500 focus:border-transparent outline-none transition-all"
                    />
                  </div>
                </div>

                <div className="flex justify-end gap-3 mt-8 pt-6">
                  <button
                    type="submit"
                    disabled={loading}
                    className="px-6 py-2.5 bg-teal-500 text-white rounded-lg hover:bg-teal-600 font-medium transition-colors disabled:opacity-50 disabled:cursor-not-allowed"
                  >
                    {loading ? "Salvando..." : "Salvar alterações"}
                  </button>
                </div>
              </form>
            </div>
          )}

          {abaAtiva === "endereco" && (
            <div>
              <div className="mb-6">
                <h2 className="text-lg font-semibold text-gray-900">
                  Endereço
                </h2>
                <p className="text-sm text-gray-500 mt-1">
                  Atualize suas informações de endereço
                </p>
              </div>

              <form onSubmit={handleSalvarEndereco}>
                <div className="grid grid-cols-1 gap-6">
                  <div>
                    <label className="block text-sm font-medium text-gray-700 mb-2">
                      Endereço (Logradouro)
                    </label>
                    <input
                      type="text"
                      value={formEnderecoData.logradouro}
                      onChange={(e) =>
                        setFormEnderecoData({
                          ...formEnderecoData,
                          logradouro: e.target.value,
                        })
                      }
                      placeholder="Rua Exemplo, Avenida etc."
                      className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-teal-500 focus:border-transparent outline-none transition-all"
                    />
                  </div>

                  <div className="grid grid-cols-2 md:grid-cols-4 gap-6">
                    <div>
                      <label className="block text-sm font-medium text-gray-700 mb-2">
                        Número
                      </label>
                      <input
                        type="text"
                        value={formEnderecoData.nr_residencia}
                        onChange={(e) =>
                          setFormEnderecoData({
                            ...formEnderecoData,
                            nr_residencia: e.target.value,
                          })
                        }
                        placeholder="Ex: 123"
                        className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-teal-500 focus:border-transparent outline-none transition-all"
                      />
                    </div>

                    <div className="md:col-span-2">
                      <label className="block text-sm font-medium text-gray-700 mb-2">
                        Complemento
                      </label>
                      <input
                        type="text"
                        value={formEnderecoData.complemento}
                        onChange={(e) =>
                          setFormEnderecoData({
                            ...formEnderecoData,
                            complemento: e.target.value,
                          })
                        }
                        placeholder="Apartamento, bloco..."
                        className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-teal-500 focus:border-transparent outline-none transition-all"
                      />
                    </div>
                  </div>

                  <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
                    <div>
                      <label className="block text-sm font-medium text-gray-700 mb-2">
                        Cidade
                      </label>
                      <input
                        type="text"
                        value={formEnderecoData.cidade}
                        onChange={(e) =>
                          setFormEnderecoData({
                            ...formEnderecoData,
                            cidade: e.target.value,
                          })
                        }
                        className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-teal-500 focus:border-transparent outline-none transition-all"
                      />
                    </div>

                    <div>
                      <label className="block text-sm font-medium text-gray-700 mb-2">
                        Estado
                      </label>
                      <select
                        value={formEnderecoData.estado}
                        onChange={(e) =>
                          setFormEnderecoData({
                            ...formEnderecoData,
                            estado: e.target.value,
                          })
                        }
                        className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-teal-500 focus:border-transparent outline-none transition-all bg-white"
                      >
                        <option value="SP">São Paulo</option>
                        <option value="RJ">Rio de Janeiro</option>
                        <option value="MG">Minas Gerais</option>
                        <option value="RS">Rio Grande do Sul</option>
                        <option value="BA">Bahia</option>
                        <option value="OT">Outros</option>
                      </select>
                    </div>

                    <div>
                      <label className="block text-sm font-medium text-gray-700 mb-2">
                        CEP
                      </label>
                      <input
                        type="text"
                        value={formEnderecoData.cep}
                        onChange={(e) =>
                          setFormEnderecoData({
                            ...formEnderecoData,
                            cep: e.target.value,
                          })
                        }
                        placeholder="00000-000"
                        className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-teal-500 focus:border-transparent outline-none transition-all"
                      />
                    </div>
                  </div>

                  <div>
                    <label className="block text-sm font-medium text-gray-700 mb-2">
                      Referência
                    </label>
                    <input
                      type="text"
                      value={formEnderecoData.referencia}
                      onChange={(e) =>
                        setFormEnderecoData({
                          ...formEnderecoData,
                          referencia: e.target.value,
                        })
                      }
                      placeholder="Ponto de referência"
                      className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-teal-500 focus:border-transparent outline-none transition-all"
                    />
                  </div>
                </div>

                <div className="flex justify-end gap-3 mt-8 pt-3">
                  <button
                    type="submit"
                    disabled={loading}
                    className="px-6 py-2.5 bg-teal-500 text-white rounded-lg hover:bg-teal-600 font-medium transition-colors disabled:opacity-50 disabled:cursor-not-allowed"
                  >
                    {loading ? "Salvando..." : "Salvar alterações"}
                  </button>
                </div>
              </form>
            </div>
          )}
        </div>
      </div>
    </div>
  );
}
