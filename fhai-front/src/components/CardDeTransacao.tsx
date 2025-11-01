interface ICardeDeTransacaoProps {
  title: string;
  subtitulo: string;
  icon: string;
  value: number;
  tipo: string;
}

const CardeDeTransacao = ({
  icon,
  subtitulo,
  tipo,
  title,
  value,
}: ICardeDeTransacaoProps) => {
  return (
    <div>
      <div className="bg-amber-50! p-4 display flex gap-4 ">
        {icon}
        <div className="display flex flex-column">
          {title}
          {subtitulo}
        </div>
        {tipo}
        R$
        {value}
      </div>
    </div>
  );
};

export { CardeDeTransacao };
