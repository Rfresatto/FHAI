interface BoxTransacao {
  children: React.ReactNode;
  titulo?: string;
}

export default function BoxTransacao({ titulo, children }: BoxTransacao) {
  return (
    <div className="bg-white rounded-xl border border-gray-200">
      <div className="px-6 py-4 border-b border-gray-200 flex items-center justify-between">
        <h2 className="text-lg font-semibold text-gray-900">{titulo}</h2>
      </div>
      {children}
    </div>
  );
}
